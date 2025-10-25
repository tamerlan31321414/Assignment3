package com.assignment3;

public class Edge implements Comparable<Edge> {
    public String from;
    public String to;
    public int weight;

    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return "{\"from\": \"" + from + "\", \"to\": \"" + to + "\", \"weight\": " + weight + "}";
    }
}