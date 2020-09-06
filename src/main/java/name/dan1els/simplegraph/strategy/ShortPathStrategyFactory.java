package name.dan1els.simplegraph.strategy;

import name.dan1els.simplegraph.Edge;
import name.dan1els.simplegraph.Vertex;
import name.dan1els.simplegraph.source.AdjacencySource;

public interface ShortPathStrategyFactory<ID, V extends Vertex<ID, ?>, E extends Edge<V>> {
    
    ShortestPathStrategy<ID, V, E> newInstance(AdjacencySource<ID, V, E> adjSource);
}
