package com.assignment3;

import java.util.*;

public class KruskalMST {
    private int operationsCount;

    private String findParent(Map<String, String> parent, String node) {
        operationsCount++;
        if (!parent.get(node).equals(node)) {
            parent.put(node, findParent(parent, parent.get(node)));
        }
        return parent.get(node);
    }

    private boolean union(Map<String, String> parent, Map<String, Integer> rank, String node1, String node2) {
        String root1 = findParent(parent, node1);
        String root2 = findParent(parent, node2);
        operationsCount += 2;

        if (!root1.equals(root2)) {

            if (rank.get(root1) > rank.get(root2)) {
                parent.put(root2, root1);
            } else if (rank.get(root1) < rank.get(root2)) {
                parent.put(root1, root2);
            } else {
                parent.put(root2, root1);
                rank.put(root1, rank.get(root1) + 1);
            }
            operationsCount += 3;
            return true;
        }
        return false;
    }

    public AlgorithmResult findMST(Graph graph) {
        operationsCount = 0;
        long startTime = System.nanoTime();

        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;


        List<Edge> sortedEdges = new ArrayList<>(graph.edges);
        Collections.sort(sortedEdges);
        operationsCount += graph.edges.size(); // операции сортировки


        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> rank = new HashMap<>();
        for (String node : graph.nodes) {
            parent.put(node, node);
            rank.put(node, 0);
        }

        for (Edge edge : sortedEdges) {
            if (mstEdges.size() == graph.nodes.size() - 1) {
                break;
            }

            if (union(parent, rank, edge.from, edge.to)) {
                mstEdges.add(edge);
                totalCost += edge.weight;
                operationsCount += 2;
            }
        }

        long executionTimeMs = (System.nanoTime() - startTime) / 1000000;
        return new AlgorithmResult(mstEdges, totalCost, operationsCount, executionTimeMs);
    }
}