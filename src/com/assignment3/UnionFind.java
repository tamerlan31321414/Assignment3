package com.assignment3;

import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private final Map<String, String> parent = new HashMap<>();
    private final Map<String, Integer> rank = new HashMap<>();

    public long findCalls = 0;
    public long unionCalls = 0;

    public UnionFind(Iterable<String> items) {
        for (String x : items) {
            parent.put(x, x);
            rank.put(x, 0);
        }
    }

    public String find(String x) {
        findCalls++;
        String p = parent.get(x);
        if (!p.equals(x)) {
            String root = find(p);
            parent.put(x, root);
            return root;
        }
        return p;
    }

    public boolean union(String a, String b) {
        unionCalls++;
        String ra = find(a);
        String rb = find(b);
        if (ra.equals(rb)) return false;
        int rA = rank.get(ra);
        int rB = rank.get(rb);
        if (rA < rB) parent.put(ra, rb);
        else {
            parent.put(rb, ra);
            if (rA == rB) rank.put(ra, rA + 1);
        }
        return true;
    }
}
