package name.dan1els.simplegraph.strategy;

import name.dan1els.simplegraph.edge.WeightedEdge;
import name.dan1els.simplegraph.source.AdjacencySource;
import name.dan1els.simplegraph.vertex.Vertex;

import java.util.LinkedList;

/**
 * TODO: To be done.
 */
public class SomeWeightedShortestPathStrategy<ID, V extends Vertex<ID, ?>, W extends Comparable<W>>
    implements ShortestPathStrategy<ID, V, WeightedEdge<W, V>> {
    
    @SuppressWarnings("FieldCanBeLocal")
    private final AdjacencySource<ID, V, WeightedEdge<W, V>> adjSource;
    
    public SomeWeightedShortestPathStrategy(AdjacencySource<ID, V, WeightedEdge<W, V>> adjSource) {
        this.adjSource = adjSource;
    }
    
    public LinkedList<V> shortestPath(V from, V to) {
       throw new RuntimeException("TBD");
    }
}
