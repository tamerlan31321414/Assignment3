City Transportation Network Optimization (Minimum Spanning Tree)

This project implements Prim’s and Kruskal’s algorithms in Java to optimize a city’s transportation network by constructing the Minimum Spanning Tree (MST) with the lowest possible road construction cost.

The city is represented as a weighted undirected graph, where vertices (nodes) represent city districts, edges represent possible roads between districts, and weights represent the construction cost of each road. The objective is to connect all districts while minimizing total cost and ensuring full connectivity. Two algorithms are implemented and compared: Prim’s Algorithm and Kruskal’s Algorithm. Each algorithm reports MST edges, total MST cost, number of operations, execution time in milliseconds, and graph statistics (vertices and edges).

Algorithms Implemented: Prim’s MST uses a Priority Queue (Min-Heap) with time complexity O((V + E) log V), which is best suited for dense graphs. Kruskal’s MST uses Union-Find with Edge Sorting, has time complexity O(E log E), and is best for sparse graphs.

Project Structure: The project folder includes the following files: Assignment3_MST/src/main/java/com/assignment3/Main.java, Graph.java, Edge.java, PrimResult.java, KruskalResult.java, UnionFind.java, and Utils.java. There are also resources/ass_3_input.json, output/ass_3_output.json, pom.xml, and README.md.

Input Format (JSON): Example of ass_3_input.json:
'''json
{ "graphs": [ { "id": 1, "nodes": ["A", "B", "C", "D", "E"], "edges": [ {"from": "A", "to": "B", "weight": 4}, {"from": "A", "to": "C", "weight": 3}, {"from": "B", "to": "C", "weight": 2}, {"from": "B", "to": "D", "weight": 5}, {"from": "C", "to": "D", "weight": 7}, {"from": "C", "to": "E", "weight": 8}, {"from": "D", "to": "E", "weight": 6} ] } ] }

Output Format (JSON): Example of ass_3_output.json:
{ "results": [ { "graph_id": 1, "input_stats": { "vertices": 5, "edges": 7 }, "prim": { "mst_edges": [ {"from": "C", "to": "B", "weight": 2}, {"from": "A", "to": "C", "weight": 3}, {"from": "B", "to": "D", "weight": 5}, {"from": "D", "to": "E", "weight": 6} ], "total_cost": 16, "operations_count": { "relaxations": 14, "heap_pops": 8, "key_updates": 4 }, "execution_time_ms": 0.01 }, "kruskal": { "mst_edges": [ {"from": "B", "to": "C", "weight": 2}, {"from": "A", "to": "C", "weight": 3}, {"from": "B", "to": "D", "weight": 5}, {"from": "D", "to": "E", "weight": 6} ], "total_cost": 16, "operations_count": { "edge_checks": 5, "find_calls": 23, "union_calls": 4, "edges_in_mst": 4 }, "execution_time_ms": 0.02 } } ] }

How to Run the Project:

Build the project using Maven and JDK 11 or later with the command: mvn clean package

Run the program using the command: java -cp target/assignment3-1.0-SNAPSHOT.jar com.assignment3.Main src/main/resources/ass_3_input.json output/ass_3_output.json

Input and Output file paths: Input JSON – src/main/resources/ass_3_input.json, Output JSON – output/ass_3_output.json

Results and Analysis: Both algorithms produce identical MST total cost. Execution time and operation counts vary slightly due to algorithm design. For small graphs (5–8 vertices), Prim is as fast as Kruskal. For medium graphs (8–12 vertices), Kruskal performs slightly faster on sparse graphs. For large graphs (12+ vertices), Prim performs better on dense graphs.

Conclusion: Prim’s algorithm is efficient for dense graphs with many connections. Kruskal’s algorithm performs well for sparse graphs with fewer edges. Both always yield the same MST total cost but differ in performance and operation count.

Possible Improvements: Implement automatic graph generation for small, medium, and large test cases. Add visualization of MST results using JavaFX or GraphStream. Store benchmark results as CSV files for analysis.

Author: Tamerlan Murat – Astana IT University, Faculty of Software Engineering.
Assignment 3 – Optimization of a City Transportation Network (MST).
Status: Fully implemented, tested on multiple graphs, ready for submission and GitHub release.
