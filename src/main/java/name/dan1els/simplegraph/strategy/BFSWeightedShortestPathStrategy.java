package name.dan1els.simplegraph.strategy;

import name.dan1els.simplegraph.edge.WeightedEdge;
import name.dan1els.simplegraph.source.AdjacencySource;
import name.dan1els.simplegraph.vertex.Vertex;

import java.util.LinkedList;

/**
 * Implementation of BFS-based shortest path algo.
 * Works correct only for unweighted edges.
 * Not thread-safe.
 *
 * @param <ID> -- vertex label type.
 * @param <V> -- vertex payload type.
 */
public class BFSWeightedShortestPathStrategy<ID, V extends Vertex<ID, ?>, W extends Comparable<W>> implements ShortestPathStrategy<ID, V, WeightedEdge<W, V>> {
    
    private final AdjacencySource<ID, V, WeightedEdge<W, V>> adjSource;
    
    public BFSWeightedShortestPathStrategy(AdjacencySource<ID, V, WeightedEdge<W, V>> adjSource) {
        this.adjSource = adjSource;
    }
    
    public LinkedList<V> shortestPath(V from, V to) {
       throw new RuntimeException("TBD");
    }
}
