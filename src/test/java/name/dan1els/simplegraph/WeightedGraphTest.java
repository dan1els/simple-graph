package name.dan1els.simplegraph;

import name.dan1els.simplegraph.source.AdjacencyList;
import name.dan1els.simplegraph.strategy.BFSWeightedShortestPathStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeightedGraphTest {
    
    @Test
    void testWeightedShortestPath() {
        var graph = new DirectedGraph<Integer, VoidVertex<Integer>, WeightedEdge<Integer, VoidVertex<Integer>>>(
            new AdjacencyList<>(),
            BFSWeightedShortestPathStrategy::new
        )
            .addV(new VoidVertex<>(1))
            .addV(new VoidVertex<>(2));
        
        graph.addE(graph.findV(1), graph.findV(2), to -> new WeightedEdge<>(to, 1));
    
        Assertions.assertThrows(RuntimeException.class, () -> graph.shortestPath(graph.findV(1), graph.findV(2)));
    }
}
