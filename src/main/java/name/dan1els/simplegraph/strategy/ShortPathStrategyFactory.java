package name.dan1els.simplegraph.strategy;

import name.dan1els.simplegraph.edge.Edge;
import name.dan1els.simplegraph.source.AdjacencySource;
import name.dan1els.simplegraph.vertex.Vertex;

public interface ShortPathStrategyFactory<ID, V extends Vertex<ID, ?>, E extends Edge<V>> {
    
    ShortestPathStrategy<ID, V, E> newInstance(AdjacencySource<ID, V, E> adjSource);
}
