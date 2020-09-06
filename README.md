# simple-graph

Create a graph:

```java
var graph = new DirectedGraph<Integer, VoidVertex<Integer>, Edge<VoidVertex<Integer>>>(
    new AdjacencyList<>(),
    BFSShortestPathStrategy::new
)
    .addV(new VoidVertex<>(0))
    .addV(new VoidVertex<>(1))
    .addV(new VoidVertex<>(2))
    .addV(new VoidVertex<>(3))
    .addV(new VoidVertex<>(4));
    
graph
    .addE(graph.findV(0), graph.findV(1), Edge::new)
    .addE(graph.findV(0), graph.findV(2), Edge::new)
    .addE(graph.findV(1), graph.findV(2), Edge::new)
    .addE(graph.findV(1), graph.findV(4), Edge::new)
    .addE(graph.findV(2), graph.findV(3), Edge::new)
    .addE(graph.findV(3), graph.findV(4), Edge::new);
```

Find path between 2 vertices:

```java
graph.shortestPath(graph.findV(0), graph.findV(4))
```
