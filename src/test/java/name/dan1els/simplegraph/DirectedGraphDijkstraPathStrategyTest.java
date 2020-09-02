package name.dan1els.simplegraph;

import name.dan1els.simplegraph.path.DijkstraPathStrategy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class DirectedGraphDijkstraPathStrategyTest {
    
    /**
     * Graph:
     * 0 -> 1 -> 4
     *  ↘  ↗   ↗
     *   2 -> 3
     */
    @Test
    void shortestPathNoCycleExists() {
        
        var graph = new DirectedGraph<Integer, Void>(DijkstraPathStrategy::new)
            .addV(new Vertex<>(0))
            .addV(new Vertex<>(1))
            .addV(new Vertex<>(2))
            .addV(new Vertex<>(3))
            .addV(new Vertex<>(4));
        graph
            .addE(graph.findV(0), graph.findV(1))
            .addE(graph.findV(0), graph.findV(2))
            .addE(graph.findV(1), graph.findV(2))
            .addE(graph.findV(1), graph.findV(4))
            .addE(graph.findV(2), graph.findV(3))
            .addE(graph.findV(3), graph.findV(4));
        
        assertThat(graph.shortestPath(graph.findV(0), graph.findV(4)))
            .containsExactly(graph.findV(0), graph.findV(1), graph.findV(4));
    }
    
    /**
     * Graph:
     * 0 -> 1 -> 5
     *    ↙  ↖    ↘
     *   2 -> 3 -> 4
     */
    @Test
    void shortestPathWithCycleExists() {
        var graph = new DirectedGraph<Integer, Void>(DijkstraPathStrategy::new)
            .addV(new Vertex<>(0))
            .addV(new Vertex<>(1))
            .addV(new Vertex<>(2))
            .addV(new Vertex<>(3))
            .addV(new Vertex<>(4))
            .addV(new Vertex<>(5));
        graph
            .addE(graph.findV(0), graph.findV(1))
            .addE(graph.findV(1), graph.findV(2))
            .addE(graph.findV(1), graph.findV(5))
            .addE(graph.findV(2), graph.findV(3))
            .addE(graph.findV(3), graph.findV(1))
            .addE(graph.findV(3), graph.findV(4))
            .addE(graph.findV(5), graph.findV(4));
    
        assertThat(graph.shortestPath(graph.findV(0), graph.findV(4)))
            .containsExactly(graph.findV(0), graph.findV(1), graph.findV(5), graph.findV(4));
    }
    
    @Test
    void shortestPathDoesNotExists() {
    }
}