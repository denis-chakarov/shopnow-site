package com.intranet.onlineshop.unit.service;

import com.intranet.onlineshop.domain.models.service.NodeDepthModel;
import com.intranet.onlineshop.domain.models.service.NodeStateModel;
import com.intranet.onlineshop.domain.models.service.TreeModel;
import com.intranet.onlineshop.domain.models.service.TreeNodeModel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TreeModelTest {

    @Test
    public void testFlattenTreeMethodWithMoreThanOneNode() {
        TreeModel tree = new TreeModel(null, null, null);
        TreeNodeModel root = new TreeNodeModel("1");
        root.setSuccessors(new ArrayList<>());
        TreeNodeModel elem = new TreeNodeModel("2");
        elem.setSuccessors(new ArrayList<>());
        elem.getSuccessors().addAll(List.of(new TreeNodeModel("4"),
                new TreeNodeModel("5"), new TreeNodeModel("6")));
        root.getSuccessors().add(elem);
        elem = new TreeNodeModel("3");
        elem.setSuccessors(new ArrayList<>());
        elem.getSuccessors().addAll(List.of(new TreeNodeModel("7"), new TreeNodeModel("8")));
        root.getSuccessors().add(elem);
        List<NodeDepthModel> actualNodeSequence = tree.flattenTree(root, 0);
        List<String> expectedNodeSequenceNames =
                List.of("1", "2", "4", "2", "5", "2", "6", "2", "1", "3", "7", "3", "8", "3", "1");
        List<Integer> expectedNodeSequenceDepths = List.of(0, 1, 2, 1, 2, 1, 2, 1, 0, 1, 2, 1, 2, 1, 0);

        for (int i = 0; i < actualNodeSequence.size(); i++) {
            Assert.assertEquals(expectedNodeSequenceNames.get(i), actualNodeSequence.get(i).getName());
            Assert.assertEquals((int) expectedNodeSequenceDepths.get(i), actualNodeSequence.get(i).getDepth());
        }
    }

    @Test
    public void testBuildShortestPathsTreeMethod() {
        List<String> targetNodeNames = List.of("R", "K", "Q", "P", "V");
        TreeModel tree = new TreeModel("A", getMockNodesInfo(), targetNodeNames);
        TreeNodeModel root = tree.buildShortestPathsSubTree();
        List<String> actualTraversedSequence = bfsTraversal(root);
        List<String> expectedTraversedSequence = List.of("A", "B", "D", "F", "E", "K", "R", "O", "V", "Q", "P");
        for (int i = 0; i < actualTraversedSequence.size(); i++) {
            Assert.assertEquals(expectedTraversedSequence.get(i), actualTraversedSequence.get(i));
        }

    }

    private List<String> bfsTraversal(TreeNodeModel root) {
        Queue<TreeNodeModel> queue = new LinkedList<>();
        List<String> traversedSequence = new ArrayList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNodeModel current = queue.remove();
            traversedSequence.add(current.getName());
            if(current.getSuccessors() != null) {
                queue.addAll(current.getSuccessors());
            }
        }
        return traversedSequence;
    }
    static Map<String, NodeStateModel> getMockNodesInfo() {
        Map<String, NodeStateModel> nodes = new HashMap<>();

        nodes.put("A", new NodeStateModel(0, true, ""));
        nodes.put("Q", new NodeStateModel(0, true, "E"));
        nodes.put("E", new NodeStateModel(0, true, "B"));
        nodes.put("B", new NodeStateModel(0, true, "A"));
        nodes.put("L", new NodeStateModel(0, true, "E"));
        nodes.put("P", new NodeStateModel(0, true, "O"));
        nodes.put("O", new NodeStateModel(0, true, "F"));
        nodes.put("F", new NodeStateModel(0, true, "B"));
        nodes.put("S", new NodeStateModel(0, true, "O"));
        nodes.put("N", new NodeStateModel(0, true, "V"));
        nodes.put("V", new NodeStateModel(0, true, "F"));
        nodes.put("G", new NodeStateModel(0, true, "C"));
        nodes.put("C", new NodeStateModel(0, true, "A"));
        nodes.put("M", new NodeStateModel(0, true, "J"));
        nodes.put("J", new NodeStateModel(0, true, "D"));
        nodes.put("D", new NodeStateModel(0, true, "A"));
        nodes.put("K", new NodeStateModel(0, true, "D"));
        nodes.put("R", new NodeStateModel(0, true, "F"));

        return nodes;
    }
}
