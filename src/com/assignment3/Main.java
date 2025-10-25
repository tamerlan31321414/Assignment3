package com.assignment3;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            String inputFile = "input.json";
            String outputFile = "output.json";

            createInputFileIfNotExists(inputFile);

            List<Graph> graphs = JSONProcessor.readGraphsFromFile(inputFile);

            PrimMST prim = new PrimMST();
            KruskalMST kruskal = new KruskalMST();
            List<Map<String, Object>> results = new ArrayList<>();

            for (Graph graph : graphs) {
                System.out.println("Processing graph " + graph.id + "...");

                AlgorithmResult primResult = prim.findMST(graph);
                AlgorithmResult kruskalResult = kruskal.findMST(graph);

                Map<String, Object> result = new HashMap<>();
                result.put("graph_id", graph.id);
                result.put("vertices", graph.getVerticesCount());
                result.put("edges", graph.getEdgesCount());
                result.put("prim", primResult);
                result.put("kruskal", kruskalResult);

                results.add(result);

                printResults(graph, primResult, kruskalResult);
            }

            JSONProcessor.writeResultsToFile(outputFile, results);

            System.out.println("\nProcessing completed. Results saved to " + outputFile);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createInputFileIfNotExists(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Creating file " + filename + "...");

            String jsonContent = "{\n" +
                    "  \"graphs\": [\n" +
                    "    {\n" +
                    "      \"id\": 1,\n" +
                    "      \"nodes\": [\"Astana\", \"Almaty\", \"Shymkent\", \"Karaganda\", \"Aktobe\"],\n" +
                    "      \"edges\": [\n" +
                    "        {\"from\": \"Astana\", \"to\": \"Almaty\", \"weight\": 12},\n" +
                    "        {\"from\": \"Astana\", \"to\": \"Karaganda\", \"weight\": 22},\n" +
                    "        {\"from\": \"Almaty\", \"to\": \"Shymkent\", \"weight\": 68},\n" +
                    "        {\"from\": \"Almaty\", \"to\": \"Karaganda\", \"weight\": 85},\n" +
                    "        {\"from\": \"Shymkent\", \"to\": \"Karaganda\", \"weight\": 47},\n" +
                    "        {\"from\": \"Shymkent\", \"to\": \"Aktobe\", \"weight\": 33},\n" +
                    "        {\"from\": \"Karaganda\", \"to\": \"Aktobe\", \"weight\": 29}\n" +
                    "      ]\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 2,\n" +
                    "      \"nodes\": [\"Mangystau\", \"Atyrau\", \"WestKazakhstan\", \"Kyzylorda\"],\n" +
                    "      \"edges\": [\n" +
                    "        {\"from\": \"Mangystau\", \"to\": \"Atyrau\", \"weight\": 15},\n" +
                    "        {\"from\": \"Mangystau\", \"to\": \"WestKazakhstan\", \"weight\": 42},\n" +
                    "        {\"from\": \"Atyrau\", \"to\": \"WestKazakhstan\", \"weight\": 28},\n" +
                    "        {\"from\": \"WestKazakhstan\", \"to\": \"Kyzylorda\", \"weight\": 35},\n" +
                    "        {\"from\": \"Atyrau\", \"to\": \"Kyzylorda\", \"weight\": 51}\n" +
                    "      ]\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 3,\n" +
                    "      \"nodes\": [\"NorthKazakhstan\", \"Pavlodar\", \"Kostanay\", \"Turkistan\", \"Zhezkazgan\"],\n" +
                    "      \"edges\": [\n" +
                    "        {\"from\": \"NorthKazakhstan\", \"to\": \"Pavlodar\", \"weight\": 18},\n" +
                    "        {\"from\": \"NorthKazakhstan\", \"to\": \"Kostanay\", \"weight\": 24},\n" +
                    "        {\"from\": \"Pavlodar\", \"to\": \"Kostanay\", \"weight\": 31},\n" +
                    "        {\"from\": \"Pavlodar\", \"to\": \"Turkistan\", \"weight\": 56},\n" +
                    "        {\"from\": \"Kostanay\", \"to\": \"Turkistan\", \"weight\": 43},\n" +
                    "        {\"from\": \"Kostanay\", \"to\": \"Zhezkazgan\", \"weight\": 27},\n" +
                    "        {\"from\": \"Turkistan\", \"to\": \"Zhezkazgan\", \"weight\": 38},\n" +
                    "        {\"from\": \"Pavlodar\", \"to\": \"Zhezkazgan\", \"weight\": 49}\n" +
                    "      ]\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

            FileWriter writer = new FileWriter(file);
            writer.write(jsonContent);
            writer.close();

            System.out.println("File " + filename + " created successfully");
        }
    }

    private static void printResults(Graph graph, AlgorithmResult prim, AlgorithmResult kruskal) {
        System.out.println("\n=== Graph " + graph.id + " ===");
        System.out.println("Vertices: " + graph.getVerticesCount() + ", Edges: " + graph.getEdgesCount());

        System.out.println("\nPrim's Algorithm:");
        System.out.println("MST Cost: " + prim.totalCost);
        System.out.println("Operations: " + prim.operationsCount);
        System.out.println("Time: " + prim.executionTimeMs + " ms");
        System.out.print("MST Edges: ");
        for (Edge edge : prim.mstEdges) {
            System.out.print(edge.from + "-" + edge.to + "(" + edge.weight + ") ");
        }

        System.out.println("\n\nKruskal's Algorithm:");
        System.out.println("MST Cost: " + kruskal.totalCost);
        System.out.println("Operations: " + kruskal.operationsCount);
        System.out.println("Time: " + kruskal.executionTimeMs + " ms");
        System.out.print("MST Edges: ");
        for (Edge edge : kruskal.mstEdges) {
            System.out.print(edge.from + "-" + edge.to + "(" + edge.weight + ") ");
        }
        System.out.println("\n" + "=".repeat(50));
    }
}