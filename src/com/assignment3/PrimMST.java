package com.assignment3;

import java.util.*;

public class PrimMST {
    private int operationsCount;

    public AlgorithmResult findMST(Graph graph) {
        operationsCount = 0;
        long startTime = System.nanoTime();

        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;


        Map<String, List<Edge>> adjacencyList = new HashMap<>();
        for (String node : graph.nodes) {
            adjacencyList.put(node, new ArrayList<>());
        }

        for (Edge edge : graph.edges) {
            adjacencyList.get(edge.from).add(new Edge(edge.from, edge.to, edge.weight));
            adjacencyList.get(edge.to).add(new Edge(edge.to, edge.from, edge.weight));
            operationsCount += 2;
        }

        Set<String> visited = new HashSet<>();
        // Приоритетная очередь для ребер (вес, узел, откуда пришли)
        PriorityQueue<Edge> minHeap = new PriorityQueue<>();

        // Начинаем с первого узла
        String startNode = graph.nodes.get(0);
        minHeap.add(new Edge(null, startNode, 0));

        while (!minHeap.isEmpty() && visited.size() < graph.nodes.size()) {
            Edge currentEdge = minHeap.poll();
            operationsCount++;

            String currentNode = currentEdge.to;

            if (visited.contains(currentNode)) {
                continue;
            }

            visited.add(currentNode);
            operationsCount++;

            // Добавляем ребро в MST (кроме начального)
            if (currentEdge.from != null) {
                mstEdges.add(new Edge(currentEdge.from, currentNode, currentEdge.weight));
                totalCost += currentEdge.weight;
            }


            for (Edge neighbor : adjacencyList.get(currentNode)) {
                operationsCount++;
                if (!visited.contains(neighbor.to)) {
                    minHeap.add(new Edge(currentNode, neighbor.to, neighbor.weight));
                    operationsCount++;
                }
            }
        }

        long executionTimeMs = (System.nanoTime() - startTime) / 1000000;
        return new AlgorithmResult(mstEdges, totalCost, operationsCount, executionTimeMs);
    }
}