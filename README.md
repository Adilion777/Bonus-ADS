# Bonus Task — Dijkstra's Shortest Path Algorithm maded by Adil Kaliolla BDA 2506

## What this is

This is a task that extending the graph implementation to support **weighted edges** and computing shortest paths using **Dijkstra's algorithm**.

The implementation is intentionally kept simple — no priority queue, just plain arrays — which is all that's needed for the graph sizes we're working with.


## Project Structure

```
dijkstra/
├── src/
│   ├── Edge.java       # Weighted edge (destination + weight)
│   ├── Graph.java      # Adjacency-list graph + Dijkstra's method
│   └── Main.java       # Two demo scenarios (including a disconnected graph)
└── README.md
```

Expected output shows a shortest-distance table with the reconstructed path for each vertex.


## What Changed from the Base Task

### Edge.java — added `weight` field

The original `Edge` only stored a destination vertex. I added a `weight` field with a constructor guard so negative weights are rejected early:

```java
public Edge(int destination, int weight) {
    if (weight < 0) throw new IllegalArgumentException(...);
    this.destination = destination;
    this.weight = weight;
}
```

### Graph.java — weighted edges + `dijkstra(int start)`

`addEdge` now takes three arguments: `(src, dest, weight)`. I also added `addUndirectedEdge` as a convenience wrapper that just calls `addEdge` twice.

The main new piece is `dijkstra(int start)`:

```java
public void dijkstra(int start) { ... }
```

## How the Algorithm Works

Dijkstra's algorithm finds the shortest path from one source vertex to every other vertex in a graph with non-negative edge weights.

always process the closest unvisited vertex next. Once a vertex is "settled", its shortest distance is final — you can't improve it later because all weights are ≥ 0.

**Step-by-step:**

1. Set `dist[start] = 0`, everything else `= ∞`.
2. Repeat until all vertices are visited:
    - Pick the **unvisited vertex `u`** with the smallest `dist[u]`.
    - Mark `u` as visited.
    - For each neighbour `v` of `u`: if `dist[u] + weight(u→v) < dist[v]`, update `dist[v]` and record `prev[v] = u`.
3. Read off distances from the `dist[]` array. Reconstruct any path by following `prev[]` backwards from the destination.

**Time complexity:** O(V²) with the simple array approach used here. Good enough for small-to-medium graphs; a priority queue would bring it to O((V + E) log V).

**Why it doesn't work with negative weights:** once a vertex is settled we never revisit it. A negative edge could create a shorter path through a "settled" vertex, which we'd miss. (That's what Bellman-Ford is for.)


## Example Walkthrough

Using Example 1 from `Main.java` (5 vertices, directed):

```
0 --2--> 1 --3--> 3
|        |        ^
6        1        |
v        v        2
2 <--4-- 4 -------+
```

## Output / Screenshots

### Example 1 — Classic 5-Vertex Directed Graph

![Example 1 output](docs\screenshots\Example 1.png)

### Example 2 — Disconnected Graph

Vertex 5 has no edges, so it shows `UNREACHABLE` with distance `N/A`.

![Example 2 output](docs\screenshots\Example 2.png)


