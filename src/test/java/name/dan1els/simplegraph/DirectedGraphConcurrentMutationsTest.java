package name.dan1els.simplegraph;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.assertj.core.api.Assertions.assertThat;

class DirectedGraphConcurrentMutationsTest {
    
    @Test
    void addV() throws InterruptedException {
        
        var sut = new DirectedGraph<Integer>();
        var executor = newFixedThreadPool(8);
        
        var vertices = IntStream.range(0, 100000)
            .mapToObj(Vertex::new)
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
        var from = new Vertex<>(0);
        var executor = newFixedThreadPool(8);
        var sut = new DirectedGraph<Integer>()
            .addV(from);
        
        var outVs = IntStream.range(1, 10001)
            .mapToObj(Vertex::new)
            .collect(Collectors.toUnmodifiableSet());
        
        try {
            outVs.forEach(it ->
                executor.submit(() ->
                    sut.addE(from, it)
                )
            );
        } finally {
            executor.shutdown();
        }
    
        executor.awaitTermination(2000, TimeUnit.MILLISECONDS);
        
        assertThat(sut.outEdges(from))
            .hasSize(10000)
            .extracting(e -> e.to)
            .containsExactlyInAnyOrderElementsOf(outVs);
    }
}