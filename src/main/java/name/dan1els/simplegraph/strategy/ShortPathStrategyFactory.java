package name.dan1els.simplegraph.strategy;

import name.dan1els.simplegraph.source.AdjacencySource;

public interface ShortPathStrategyFactory<ID, T> {
    
    ShortestPathStrategy<ID, T> newInstance(AdjacencySource<ID,T> adjSource);
}
