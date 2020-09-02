package name.dan1els.simplegraph;

import name.dan1els.simplegraph.strategy.DijShortPathStrategy;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.assertj.core.api.Assertions.assertThat;

class UndirectedGraphConcurrentMutationsTest {
    
    @Test
    void addV() throws InterruptedException {
        
        var sut = new UndirectedGraph<Integer, Integer>(DijShortPathStrategy::new);
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
        var sut = new UndirectedGraph<Integer, Integer>(DijShortPathStrategy::new)
            .addV(fromV);
        
        var outVs = IntStream.range(1, 10001)
            .mapToObj(i -> new Vertex<>(i, i))
            .collect(Collectors.toUnmodifiableSet());
        
        try {
            outVs.forEach(it ->
                executor.submit(() ->
                    sut.addE(fromV, it)
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
}