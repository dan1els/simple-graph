package name.dan1els.simplegraph;

import name.dan1els.simplegraph.source.AdjacencyList;
import name.dan1els.simplegraph.strategy.BFSShortestPathStrategy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class UndirectedGraphBFSShortestPathStrategyTest {
    
    /**
     * Graph:
     * 0 -- 1    4
     *  \  /    /
     *   2 -- 3
     */
    @Test
    void shortestPathExists() {
        var graph = new UndirectedGraph<Integer, VoidVertex<Integer>>(
            new AdjacencyList<>(),
            BFSShortestPathStrategy::new
        )
            .addV(new VoidVertex<>(0))
            .addV(new VoidVertex<>(1))
            .addV(new VoidVertex<>(2))
            .addV(new VoidVertex<>(3))
            .addV(new VoidVertex<>(4));
        graph
            .addE(graph.findV(0), graph.findV(1))
            .addE(graph.findV(0), graph.findV(2))
            .addE(graph.findV(1), graph.findV(2))
            .addE(graph.findV(2), graph.findV(3))
            .addE(graph.findV(3), graph.findV(4));
    
        assertThat(graph.shortestPath(graph.findV(0), graph.findV(4)))
            .containsExactly(graph.findV(0), graph.findV(2), graph.findV(3), graph.findV(4));
    }
    
    /**
     * Graph:
     * 0 --  1  3
     *  \  /
     *   2
     */
    @Test
    void shortestPathDoesNotExist() {
        var graph = new UndirectedGraph<Integer, VoidVertex<Integer>>(
            new AdjacencyList<>(),
            BFSShortestPathStrategy::new
        )
            .addV(new VoidVertex<>(0))
            .addV(new VoidVertex<>(1))
            .addV(new VoidVertex<>(2))
            .addV(new VoidVertex<>(3));
        graph
            .addE(graph.findV(0), graph.findV(1))
            .addE(graph.findV(1), graph.findV(2))
            .addE(graph.findV(2), graph.findV(0));
    
        assertThat(graph.shortestPath(graph.findV(0), graph.findV(3))).isEmpty();
    }
}