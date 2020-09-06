package name.dan1els.simplegraph.strategy;

import name.dan1els.simplegraph.edge.WeightedEdge;
import name.dan1els.simplegraph.source.AdjacencySource;
import name.dan1els.simplegraph.vertex.Vertex;

import java.util.LinkedList;

/**
 * TODO: To be done.
 */
public class SomeWeightedShortestPathStrategy<ID, V extends Vertex<ID, ?>, W extends Comparable<W>, E extends WeightedEdge<W, V>>
    implements ShortestPathStrategy<ID, V, E> {
    
    @SuppressWarnings("FieldCanBeLocal")
    private final AdjacencySource<ID, V, E> adjSource;
    
    public SomeWeightedShortestPathStrategy(AdjacencySource<ID, V, E> adjSource) {
        this.adjSource = adjSource;
    }
    
    public LinkedList<V> shortestPath(V from, V to) {
       throw new RuntimeException("TBD");
    }
}
