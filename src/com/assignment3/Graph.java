package com.assignment3;

import com.assignment3.Edge;

import java.util.List;

public class Graph {
    public int id;
    public List<String> nodes;
    public List<Edge> edges;

    public Graph(int id, List<String> nodes, List<Edge> edges) {
        this.id = id;
        this.nodes = nodes;
        this.edges = edges;
    }

    public int getVerticesCount() {
        return nodes.size();
    }

    public int getEdgesCount() {
        return edges.size();
    }
}