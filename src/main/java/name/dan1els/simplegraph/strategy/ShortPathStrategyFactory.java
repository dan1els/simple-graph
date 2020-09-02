package name.dan1els.simplegraph.strategy;

import name.dan1els.simplegraph.source.AdjacencyList;

public interface ShortPathStrategyFactory<ID, T> {
    
    ShortPathStrategy<ID, T> newInstance(AdjacencyList<ID, T> adjSource);
}
