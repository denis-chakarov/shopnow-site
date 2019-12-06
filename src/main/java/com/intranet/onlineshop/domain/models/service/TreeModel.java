package com.intranet.onlineshop.domain.models.service;

import java.util.*;

public class TreeModel {

    private String rootName;
    private Map<String, NodeStateModel> nodes;
    private List<String> targetNodeNames;
    private Map<String, Boolean> visitedNodes;
    private List<NodeDepthModel> traversedNodesSequence;

    public TreeModel(String rootName, Map<String, NodeStateModel> nodes, List<String> targetNodeNames) {
        this.rootName = rootName;
        this.nodes = nodes;
        this.targetNodeNames = targetNodeNames;
        visitedNodes = new HashMap<>();
        traversedNodesSequence = new ArrayList<>();

    }

    public List<NodeDepthModel> flattenTree(TreeNodeModel start, int depth) {
        flattenTreeRec(start, depth);
        return traversedNodesSequence;
    }
    private void flattenTreeRec(TreeNodeModel start, int depth) {
        visitedNodes.put(start.getName(), true);
        traversedNodesSequence.add(new NodeDepthModel(start.getName(), depth));
        if(start.getSuccessors() != null) {
            for (TreeNodeModel node : start.getSuccessors()) {
                if(!visitedNodes.containsKey(node.getName()) || !visitedNodes.get(node.getName())) {
                    flattenTree(node, depth + 1);
                    traversedNodesSequence.add(new NodeDepthModel(start.getName(), depth));
                }
            }
        }
    }

    public TreeNodeModel buildShortestPathsSubTree() {
        for(NodeStateModel nodeState : nodes.values()) {
            nodeState.setUsed(false);
        }
        Deque<String> stack = new ArrayDeque<>();
        TreeNodeModel tree = new TreeNodeModel(rootName);
        tree.setSuccessors(new ArrayList<>());
        nodes.get(rootName).setUsed(true);

        for(String currentNodeName : targetNodeNames) {

            while(!nodes.get(currentNodeName).isUsed()) {
                stack.push(currentNodeName);
                nodes.get(currentNodeName).setUsed(true);
                currentNodeName = nodes.get(currentNodeName).getPredecessor();
            }

            TreeNodeModel branch = new TreeNodeModel(stack.poll());
            TreeNodeModel temp = branch;
            while(!stack.isEmpty()) {
                temp.setSuccessors(new ArrayList<>());
                TreeNodeModel element = new TreeNodeModel(stack.poll());
                temp.getSuccessors().add(element);
                temp = element;
            }
            TreeNodeModel currentNode = findNodeByName(currentNodeName, tree);
            if(currentNode == null) currentNode = tree;

            currentNode.getSuccessors().add(branch);
        }
        return tree;
    }

    private TreeNodeModel findNodeByName(String nodeName, TreeNodeModel tree) {
        Deque<TreeNodeModel> stack = new ArrayDeque<>();
        stack.push(tree);

        while(!stack.isEmpty()) {

            TreeNodeModel currentNode = stack.poll();
            if(currentNode.getName().equals(nodeName)) return currentNode;

            if(currentNode.getSuccessors() == null) continue;

            for(TreeNodeModel succ : currentNode.getSuccessors()) {
                stack.push(succ);
            }
        }
        return null;
    }
}
