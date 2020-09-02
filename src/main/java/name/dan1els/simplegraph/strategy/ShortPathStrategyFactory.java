package name.dan1els.simplegraph.strategy;

import name.dan1els.simplegraph.Edge;
import name.dan1els.simplegraph.Vertex;

import java.util.Map;
import java.util.Set;

public interface ShortPathStrategyFactory<ID, T> {
    
    ShortPathStrategy<ID, T> newStrategy(Map<Vertex<ID, T>, Set<Edge<ID, T>>> adjSource);
}
