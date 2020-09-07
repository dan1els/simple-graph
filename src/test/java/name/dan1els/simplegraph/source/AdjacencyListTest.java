package name.dan1els.simplegraph.source;

import name.dan1els.simplegraph.edge.Edge;
import name.dan1els.simplegraph.vertex.Vertex;
import name.dan1els.simplegraph.vertex.VoidVertex;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.assertj.core.api.Assertions.assertThat;

class AdjacencyListTest {
    
    @Test
    void addV() throws InterruptedException {
        
        var sut = new AdjacencyList<Integer, Vertex<Integer, Integer>, Edge<Vertex<Integer, Integer>>>();
        var executor = newFixedThreadPool(8);
        
        var vertices = IntStream.range(0, 100000)
            .mapToObj(i -> new Vertex<>(i, i))
            .collect(Collectors.toUnmodifiableSet());
        
        try {
            vertices.forEach(it ->
                executor.submit(() ->
                    sut.addV(it)
                )
            );
        } finally {
            executor.shutdown();
        }
        
        executor.awaitTermination(2000, TimeUnit.MILLISECONDS);
        
        assertThat(sut.vertices())
            .hasSize(100000)
            .isEqualTo(vertices);
    }
    
    @Test
    void addE() throws InterruptedException {
        var fromV = new Vertex<>(0, 0);
        var executor = newFixedThreadPool(8);
        var sut = new AdjacencyList<Integer, Vertex<Integer, Integer>, Edge<Vertex<Integer, Integer>>>();
        sut.addV(fromV);
        
        var outVs = IntStream.range(1, 10001)
            .mapToObj(i -> new Vertex<>(i, i))
            .collect(Collectors.toUnmodifiableSet());
        
        try {
            outVs.forEach(it ->
                executor.submit(() ->
                    sut.addE(fromV, it, Edge::new)
                )
            );
        } finally {
            executor.shutdown();
        }
        
        executor.awaitTermination(2000, TimeUnit.MILLISECONDS);
        
        assertThat(sut.outEdges(fromV))
            .hasSize(10000)
            .extracting(Edge::outV)
            .containsExactlyInAnyOrderElementsOf(outVs);
        
        assertThat(sut.vertices())
            .hasSize(10001)
            .containsAll(outVs)
            .contains(fromV);
    }
    
    @Test
    void addSameVTwiceShouldBeAddedFirst() {
        var sut = new AdjacencyList<Integer, VoidVertex<Integer>, Edge<VoidVertex<Integer>>>();
        sut.addV(new VoidVertex<>(0));
        sut.addV(new VoidVertex<>(0));
       
        assertThat(sut.vertices()).hasSize(1);
    }
}