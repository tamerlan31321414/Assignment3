package com.assignment3;

import java.util.List;

public class AlgorithmResult {
    public List<Edge> mstEdges;
    public int totalCost;
    public int operationsCount;
    public long executionTimeMs;

    public AlgorithmResult(List<Edge> mstEdges, int totalCost, int operationsCount, long executionTimeMs) {
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.operationsCount = operationsCount;
        this.executionTimeMs = executionTimeMs;
    }
}