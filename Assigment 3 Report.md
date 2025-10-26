Assignment 3 Report: Optimization of City Transportation Network 
Student: Murat Tamerlan SE-2402
1. Introduction
This report presents the implementation and analysis of Prim's and Kruskal's algorithms for finding Minimum Spanning Trees (MST) in graphs representing city transportation networks. The objective was to compare algorithm efficiency and determine optimal use cases for each approach.

2. Methodology
2.1. Input Data
Two test graphs were used for evaluation:
Graph 1: 5 vertices (A, B, C, D, E), 7 edges
Graph 2: 4 vertices (A, B, C, D), 5 edges
2.2. Implemented Algorithms
Prim's Algorithm
Uses priority queue for selecting minimum-weight edges
Starts from arbitrary vertex and gradually expands the tree
Time complexity: O(E log V) with binary heap
Kruskal's Algorithm
Sorts all edges by weight and sequentially adds them to the tree
Uses Union-Find data structure for cycle detection
Time complexity: O(E log E)

3. Results
3.1. Graph 1 (5 vertices, 7 edges)
Prim's Algorithm:
MST Total Cost: 16
Operations Count: 42
Execution Time: 1.52 ms
MST Edges: B-C(2), A-C(3), B-D(5), D-E(6)
Kruskal's Algorithm:
MST Total Cost: 16
Operations Count: 37

3.2. Graph 2 (4 vertices, 5 edges)
Prim's Algorithm:
MST Total Cost: 6
Operations Count: 29
Execution Time: 0.87 ms
MST Edges: A-B(1), B-C(2), C-D(3)
Kruskal's Algorithm:
MST Total Cost: 6
Operations Count: 31
Execution Time: 0.92 ms
MST Edges: A-B(1), B-C(2), C-D(3)

4.1. Time Complexity Comparison
Graph	Prim Operations	Kruskal Operations	Prim Time (ms)	Kruskal Time (ms)
Graph 1	42	37	1.52	1.28
Graph 2	29	31	0.87	0.92
4.2. Key Observations
Consistent MST Cost: Both algorithms produced identical total costs for both graphs, validating implementation correctness.
Operations Efficiency:
For dense graphs (Graph 1), Kruskal showed better performance (37 vs 42 operations)
For sparse graphs (Graph 2), Prim was slightly more efficient (29 vs 31 operations)
Execution Time: Kruskal demonstrated faster execution times in most cases, particularly for larger graphs.
Execution Time: 1.28 ms
MST Edges: B-C(2), A-C(3), B-D(5), D-E(6)

5. Algorithm Comparison
5.1. Prim's Algorithm Advantages
Better for dense graphs

More efficient when the graph is represented as an adjacency list

No need for initial sorting of all edges

Guaranteed optimal performance with Fibonacci heap implementation

5.2. Kruskal's Algorithm Advantages
Better for sparse graphs
Simpler implementation
Naturally parallelizable (edge sorting and processing)
More efficient with edge-list representation
Better performance when edges are already sorted

5.3. Memory Usage
Prim: O(V) for priority queue and visited set
Kruskal: O(E) for edge storage and Union-Find structure

6. Recommendations for Different Scenarios
6.1. Graph Density-Based Selection
Dense Graphs (E ≈ V²): Prefer Prim's algorithm
Sparse Graphs (E ≈ V): Prefer Kruskal's algorithm

6.2. Implementation Considerations
Adjacency List Representation: Prim's algorithm
Edge List Representation: Kruskal's algorithm
Dynamic Graphs: Prim's algorithm (better for incremental updates)
Static Graphs: Kruskal's algorithm

6.3. Real-World Applications
City Planning (Dense Networks): Prim's algorithm
Telecommunication Networks: Kruskal's algorithm
Transportation Routes: Depends on network density

7. Technical Implementation Details
7.1. Data Structures Used
Priority Queue: For Prim's algorithm edge selection
Union-Find: For Kruskal's cycle detection
Adjacency List: Efficient graph representation
Custom JSON Parser: For input data processing

7.2. Performance Optimizations
Lazy deletion in Prim's priority queue
Path compression in Union-Find
Efficient edge comparison and sorting
Memory-efficient data structures

8. Conclusion
Both Prim's and Kruskal's algorithms successfully solved the MST problem with identical optimal costs. The choice between algorithms depends on specific use cases:
Prim's Algorithm is preferable for:
Dense graphs
Adjacency list representations
Scenarios requiring frequent updates
Kruskal's Algorithm is preferable for:
Sparse graphs
Edge list representations
Distributed computing environments
When edges are pre-sorted
The implementation demonstrates that for typical city transportation networks, which tend to be moderately dense, Prim's algorithm might offer better performance. However, Kruskal's algorithm provides simpler implementation and better scalability for large, sparse networks.
