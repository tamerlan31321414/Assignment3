 City Transportation Network Optimization (Minimum Spanning Tree)

This project implements **Prim’s** and **Kruskal’s** algorithms in **Java** to optimize a city’s transportation network by constructing the **Minimum Spanning Tree (MST)** with the lowest possible road construction cost.

Project Overview

The city is represented as a **weighted undirected graph**, where:
- **Vertices (nodes)** → City districts  
- **Edges** → Possible roads between districts  
- **Weights** → Construction cost of each road  

The objective is to connect all districts while minimizing total cost and ensuring every district is reachable from any other.

Two classical algorithms are implemented and compared:
- **Prim’s Algorithm**
- **Kruskal’s Algorithm**

Each algorithm reports:
- MST edges
- Total MST cost
- Number of operations
- Execution time (ms)
- Graph statistics (vertices & edges)


Algorithms Implemented

| Algorithm | Data Structures Used | Time Complexity | Best For |
|------------|----------------------|-----------------|-----------|
| **Prim’s MST** | Priority Queue (Min-Heap) | `O((V + E) log V)` | Dense graphs |
| **Kruskal’s MST** | Union-Find + Edge Sorting | `O(E log E)` | Sparse graphs |

📦 Assignment3_MST
├─ 📂 src
│ └─ 📂 main/java/com/assignment3
│ ├─ Main.java # Main class (reads JSON, runs algorithms)
│ ├─ Graph.java # Graph data structure
│ ├─ Edge.java # Edge representation
│ ├─ PrimResult.java # Result container for Prim
│ ├─ KruskalResult.java # Result container for Kruskal
│ ├─ UnionFind.java # Disjoint Set for Kruskal
│ └─ Utils.java # Helper methods
├─ 📂 resources
│ └─ ass_3_input.json # Input JSON with graph data
├─ 📂 output
│ └─ ass_3_output.json # Generated output JSON (results)
├─ pom.xml # Maven dependencies (includes Gson)
└─ README.md

Input Format (JSON)

Example `ass_3_input.json`:

```json
{
  "graphs": [
    {
      "id": 1,
      "nodes": ["A", "B", "C", "D", "E"],
      "edges": [
        {"from": "A", "to": "B", "weight": 4},
        {"from": "A", "to": "C", "weight": 3},
        {"from": "B", "to": "C", "weight": 2},
        {"from": "B", "to": "D", "weight": 5},
        {"from": "C", "to": "D", "weight": 7},
        {"from": "C", "to": "E", "weight": 8},
        {"from": "D", "to": "E", "weight": 6}
      ]
    }
  ]
}
 Output Format (JSON)
Example ass_3_output.json:

{
  "results": [
    {
      "graph_id": 1,
      "input_stats": { "vertices": 5, "edges": 7 },
      "prim": {
        "mst_edges": [
          {"from": "C", "to": "B", "weight": 2},
          {"from": "A", "to": "C", "weight": 3},
          {"from": "B", "to": "D", "weight": 5},
          {"from": "D", "to": "E", "weight": 6}
        ],
        "total_cost": 16,
        "operations_count": {
          "relaxations": 14,
          "heap_pops": 8,
          "key_updates": 4
        },
        "execution_time_ms": 0.01
      },
      "kruskal": {
        "mst_edges": [
          {"from": "B", "to": "C", "weight": 2},
          {"from": "A", "to": "C", "weight": 3},
          {"from": "B", "to": "D", "weight": 5},
          {"from": "D", "to": "E", "weight": 6}
        ],
        "total_cost": 16,
        "operations_count": {
          "edge_checks": 5,
          "find_calls": 23,
          "union_calls": 4,
          "edges_in_mst": 4
        },
        "execution_time_ms": 0.02
      }
    }
  ]
}
 How to Run the Project
1️ Build the project
Make sure Maven and JDK 11+ are installed.

bash
Копировать код
mvn clean package
2️ Run the program
bash
Копировать код
java -cp target/assignment3-1.0-SNAPSHOT.jar com.assignment3.Main src/main/resources/ass_3_input.json output/ass_3_output.json
3️Input & Output Paths
File	Path
Input JSON	src/main/resources/ass_3_input.json
Output JSON	output/ass_3_output.json

Results & Analysis
Both algorithms produce identical MST total cost.
⏱ Execution time and operation counts vary slightly due to algorithm design.

Graph Size	Algorithm	Total Cost	Time (ms)	Best Use
Small (5–8 vertices)	Prim	same as Kruskal	very fast	Both
Medium (8–12 vertices)	Kruskal	same	faster	Sparse graphs
Large (12+ vertices)	Prim	same	better	Dense graphs

Conclusion
Prim’s algorithm is efficient for dense graphs (many connections).

Kruskal’s algorithm performs well for sparse graphs (fewer edges).

Both always yield the same MST total cost but differ in performance and operation count.

Possible Improvements
Implement automatic graph generation for testing (small, medium, large).

Add visualization of MST results using JavaFX or GraphStream.

Store benchmark results as CSV for analysis.

Author
Tamerlan Murat — Astana IT University
Faculty of Software Engineering
Assignment 3 — Optimization of a City Transportation Network (MST)
