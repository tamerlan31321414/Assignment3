package com.assignment3;

import java.util.*;
import java.io.*;
import java.nio.file.*;

public class JSONProcessor {

    public static List<Graph> readGraphsFromFile(String filename) throws IOException {
        List<Graph> graphs = new ArrayList<>();

        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException("Файл " + filename + " не найден");
        }

        String content = new String(Files.readAllBytes(Paths.get(filename)));
        content = content.trim();

        int graphsStart = content.indexOf("\"graphs\":") + 8;
        if (graphsStart < 8) {
            throw new IOException("Неверный формат JSON: не найден 'graphs'");
        }

        int graphsEnd = content.lastIndexOf("]");
        if (graphsEnd <= graphsStart) {
            throw new IOException("Неверный формат JSON");
        }

        String graphsContent = content.substring(graphsStart, graphsEnd).trim();
        graphsContent = graphsContent.substring(graphsContent.indexOf("[") + 1);

        int pos = 0;
        while (pos < graphsContent.length()) {
            int graphStart = graphsContent.indexOf("{", pos);
            if (graphStart == -1) break;

            int graphEnd = findMatchingBrace(graphsContent, graphStart);
            if (graphEnd == -1) break;

            String graphStr = graphsContent.substring(graphStart, graphEnd + 1);
            Graph graph = parseGraph(graphStr);
            if (graph != null) {
                graphs.add(graph);
            }

            pos = graphEnd + 1;
        }

        return graphs;
    }

    private static int findMatchingBrace(String str, int start) {
        int count = 0;
        for (int i = start; i < str.length(); i++) {
            if (str.charAt(i) == '{') count++;
            else if (str.charAt(i) == '}') count--;
            if (count == 0) return i;
        }
        return -1;
    }

    private static Graph parseGraph(String graphStr) {
        try {
            int idIndex = graphStr.indexOf("\"id\":");
            if (idIndex == -1) return null;
            int idStart = graphStr.indexOf(":", idIndex) + 1;
            int idEnd = findNextDelimiter(graphStr, idStart);
            int id = Integer.parseInt(graphStr.substring(idStart, idEnd).trim());

            List<String> nodes = parseArray(graphStr, "nodes");
            List<Edge> edges = parseEdges(graphStr);

            return new Graph(id, nodes, edges);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static int findNextDelimiter(String str, int start) {
        for (int i = start; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ',' || c == '}' || c == ']') return i;
        }
        return str.length();
    }

    private static List<String> parseArray(String str, String key) {
        List<String> result = new ArrayList<>();
        int keyIndex = str.indexOf("\"" + key + "\":");
        if (keyIndex == -1) return result;

        int arrayStart = str.indexOf("[", keyIndex) + 1;
        int arrayEnd = str.indexOf("]", arrayStart);
        if (arrayEnd <= arrayStart) return result;

        String arrayContent = str.substring(arrayStart, arrayEnd).trim();

        String[] items = arrayContent.split(",");
        for (String item : items) {
            String cleaned = item.trim().replace("\"", "");
            if (!cleaned.isEmpty()) {
                result.add(cleaned);
            }
        }

        return result;
    }

    private static List<Edge> parseEdges(String graphStr) {
        List<Edge> edges = new ArrayList<>();

        int edgesIndex = graphStr.indexOf("\"edges\":");
        if (edgesIndex == -1) return edges;

        int arrayStart = graphStr.indexOf("[", edgesIndex) + 1;
        int arrayEnd = strIndexOf(graphStr, "]", arrayStart);
        if (arrayEnd <= arrayStart) return edges;

        String edgesContent = graphStr.substring(arrayStart, arrayEnd);

        int pos = 0;
        while (pos < edgesContent.length()) {
            int edgeStart = edgesContent.indexOf("{", pos);
            if (edgeStart == -1) break;

            int edgeEnd = findMatchingBrace(edgesContent, edgeStart);
            if (edgeEnd == -1) break;

            String edgeStr = edgesContent.substring(edgeStart, edgeEnd + 1);
            Edge edge = parseEdge(edgeStr);
            if (edge != null) {
                edges.add(edge);
            }

            pos = edgeEnd + 1;
        }

        return edges;
    }

    private static int strIndexOf(String str, String target, int fromIndex) {
        int index = str.indexOf(target, fromIndex);
        return index == -1 ? str.length() : index;
    }

    private static Edge parseEdge(String edgeStr) {
        try {
            String from = extractStringValue(edgeStr, "from");
            String to = extractStringValue(edgeStr, "to");
            int weight = extractIntValue(edgeStr, "weight");

            return new Edge(from, to, weight);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String extractStringValue(String str, String key) {
        int keyIndex = str.indexOf("\"" + key + "\":");
        if (keyIndex == -1) return "";

        int valueStart = str.indexOf(":", keyIndex) + 1;
        int valueEnd = findNextDelimiter(str, valueStart);
        String value = str.substring(valueStart, valueEnd).trim().replace("\"", "");
        return value;
    }

    private static int extractIntValue(String str, String key) {
        int keyIndex = str.indexOf("\"" + key + "\":");
        if (keyIndex == -1) return 0;

        int valueStart = str.indexOf(":", keyIndex) + 1;
        int valueEnd = findNextDelimiter(str, valueStart);
        String valueStr = str.substring(valueStart, valueEnd).trim();
        return Integer.parseInt(valueStr);
    }

    public static void writeResultsToFile(String filename, List<Map<String, Object>> results) throws IOException {
        StringBuilder json = new StringBuilder();
        json.append("{\n  \"results\": [\n");

        for (int i = 0; i < results.size(); i++) {
            Map<String, Object> result = results.get(i);
            json.append("    {\n");
            json.append("      \"graph_id\": ").append(result.get("graph_id")).append(",\n");
            json.append("      \"input_stats\": {\n");
            json.append("        \"vertices\": ").append(result.get("vertices")).append(",\n");
            json.append("        \"edges\": ").append(result.get("edges")).append("\n");
            json.append("      },\n");

            AlgorithmResult prim = (AlgorithmResult) result.get("prim");
            json.append("      \"prim\": {\n");
            json.append("        \"mst_edges\": [\n");
            for (int j = 0; j < prim.mstEdges.size(); j++) {
                Edge edge = prim.mstEdges.get(j);
                json.append("          {\"from\": \"").append(edge.from)
                        .append("\", \"to\": \"").append(edge.to)
                        .append("\", \"weight\": ").append(edge.weight).append("}");
                if (j < prim.mstEdges.size() - 1) json.append(",");
                json.append("\n");
            }
            json.append("        ],\n");
            json.append("        \"total_cost\": ").append(prim.totalCost).append(",\n");
            json.append("        \"operations_count\": ").append(prim.operationsCount).append(",\n");
            json.append("        \"execution_time_ms\": ").append(prim.executionTimeMs).append("\n");
            json.append("      },\n");

            AlgorithmResult kruskal = (AlgorithmResult) result.get("kruskal");
            json.append("      \"kruskal\": {\n");
            json.append("        \"mst_edges\": [\n");
            for (int j = 0; j < kruskal.mstEdges.size(); j++) {
                Edge edge = kruskal.mstEdges.get(j);
                json.append("          {\"from\": \"").append(edge.from)
                        .append("\", \"to\": \"").append(edge.to)
                        .append("\", \"weight\": ").append(edge.weight).append("}");
                if (j < kruskal.mstEdges.size() - 1) json.append(",");
                json.append("\n");
            }
            json.append("        ],\n");
            json.append("        \"total_cost\": ").append(kruskal.totalCost).append(",\n");
            json.append("        \"operations_count\": ").append(kruskal.operationsCount).append(",\n");
            json.append("        \"execution_time_ms\": ").append(kruskal.executionTimeMs).append("\n");
            json.append("      }\n");

            json.append("    }");
            if (i < results.size() - 1) json.append(",");
            json.append("\n");
        }

        json.append("  ]\n}");

        Files.write(Paths.get(filename), json.toString().getBytes());
    }
}