package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.entities.PostOffice;
import com.intranet.onlineshop.domain.entities.PostOfficeConnection;
import com.intranet.onlineshop.domain.models.service.*;
import com.intranet.onlineshop.repository.PostOfficeConnectionRepository;
import com.intranet.onlineshop.repository.PostOfficeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostOfficesMapServiceImpl implements PostOfficesMapService {

    private final PostOfficeConnectionRepository postOfficeConnectionRepository;
    private final PostOfficeRepository postOfficeRepository;
    private final ModelMapper modelMapper;
    private final int MAX_DISTANCE = 100000;


    @Autowired
    public PostOfficesMapServiceImpl(PostOfficeConnectionRepository postOfficeConnectionRepository, PostOfficeRepository postOfficeRepository, ModelMapper modelMapper) {
        this.postOfficeConnectionRepository = postOfficeConnectionRepository;
        this.postOfficeRepository = postOfficeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Map<String, NodeStateModel> findShortestPathsFrom(String postOfficeName) {
        Map<String, List<GraphNodeModel>> graph = buildPostOfficesGraph();
        Map<String, NodeStateModel> nodesInfo = new HashMap<>();


        for(String key : graph.keySet()) {
            nodesInfo.put(key, new NodeStateModel(MAX_DISTANCE, false, ""));
        }
        nodesInfo.get(postOfficeName).setUsed(true);

        PriorityQueue<GraphNodeModel> qnodes =
                new PriorityQueue<>(graph.keySet().size(), Comparator.comparing(GraphNodeModel::getDistance));

        for(GraphNodeModel node : graph.get(postOfficeName)) {
            nodesInfo.get(node.getName()).setPathDistance(node.getDistance());
            nodesInfo.get(node.getName()).setPredecessor(postOfficeName);
            qnodes.add(node);

        }

        while(!qnodes.isEmpty()) {
            GraphNodeModel currentNode = qnodes.poll();
            NodeStateModel currentNodeState = nodesInfo.get(currentNode.getName());

            if(currentNodeState.isUsed()) continue;

            currentNodeState.setUsed(true);

            for (GraphNodeModel successor : graph.get(currentNode.getName())) {
                NodeStateModel successorState = nodesInfo.get(successor.getName());
                if(!successorState.isUsed()
                        && successorState.getPathDistance() > currentNodeState.getPathDistance() + successor.getDistance()) {

                    successorState.setPathDistance(currentNodeState.getPathDistance() + successor.getDistance());
                    successorState.setPredecessor(currentNode.getName());
                    qnodes.add(new GraphNodeModel(successor.getName(), successorState.getPathDistance()));
                }
            }
        }
        return nodesInfo;
    }


    @Override
    public PostOfficeServiceModel findFirstCommonPostOffice(String startNode, Map<String, NodeStateModel> nodes,
                                                            List<String> targetNodeNames) {
        TreeModel treeModel = new TreeModel(startNode, nodes, targetNodeNames);
        TreeNodeModel root = treeModel.buildShortestPathsSubTree();
        List<NodeDepthModel> nodeSequence = treeModel.flattenTree(root, 0);
        int minDepth = 9999;
        List<Integer> targetNodeIndices = new ArrayList<>();
        for (String element : targetNodeNames) {
            int currentIndex = findElementIndex(nodeSequence, element);
            if( currentIndex != -1) targetNodeIndices.add(currentIndex);
        }
        int minIndex = Collections.min(targetNodeIndices);
        int maxIndex = Collections.max(targetNodeIndices);
        String resultNode = "";

        for (int i = minIndex; i <= maxIndex; i++) {
            if(minDepth > nodeSequence.get(i).getDepth()) {
                minDepth = nodeSequence.get(i).getDepth();
                resultNode = nodeSequence.get(i).getName();
            }
        }
        PostOffice postOffice = postOfficeRepository.findByOfficeName(resultNode);

        return modelMapper.map(postOffice, PostOfficeServiceModel.class);
    }

    private int findElementIndex(List<NodeDepthModel> nodeSequence, String element) {
        for (int i = 0; i < nodeSequence.size(); i++) {
            if(nodeSequence.get(i).getName().equals(element)) return i;
        }
        return -1;
    }

    private Map<String, List<GraphNodeModel>> buildPostOfficesGraph() {
        List<PostOfficeConnection> connections = postOfficeConnectionRepository.findAll();
        List<PostOfficeConnectionModel> edges = connections
                .stream()
                .map(c -> modelMapper.map(c, PostOfficeConnectionModel.class))
                .collect(Collectors.toList());
        Map<String, List<GraphNodeModel>> graph = new HashMap<>();
        for(PostOfficeConnectionModel edge : edges) {
            if(!graph.containsKey(edge.getFirstPostOfficeName())) {
                graph.put(edge.getFirstPostOfficeName(), new ArrayList<>());
            }
            if(!graph.containsKey(edge.getSecondPostOfficeName())) {
                graph.put(edge.getSecondPostOfficeName(), new ArrayList<>());
            }
            graph.get(edge.getFirstPostOfficeName()).add( new GraphNodeModel(edge.getSecondPostOfficeName(), edge.getDistance()));
            graph.get(edge.getSecondPostOfficeName()).add( new GraphNodeModel(edge.getFirstPostOfficeName(), edge.getDistance()));

        }
        return graph;
    }
}
