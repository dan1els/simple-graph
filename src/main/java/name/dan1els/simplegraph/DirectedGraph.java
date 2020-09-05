package name.dan1els.simplegraph;

import name.dan1els.simplegraph.source.AdjacencySource;
import name.dan1els.simplegraph.strategy.ShortPathStrategyFactory;

import java.util.LinkedList;
import java.util.Set;

public class DirectedGraph<ID, T extends Vertex<ID, ?>> implements Graph<ID, T> {
    
    private final AdjacencySource<ID, T> adjSource;
    private final ShortPathStrategyFactory<ID, T> pathStrategyFactory;
    
    public DirectedGraph(
        AdjacencySource<ID, T> adjacencySource,
        ShortPathStrategyFactory<ID, T> pathStrategyFactory
    ) {
        this.adjSource = adjacencySource;
        this.pathStrategyFactory = pathStrategyFactory;
    }
    
    @Override
    public Graph<ID, T> addV(T vertex) {
        adjSource.addV(vertex);
        return this;
    }
    
    @Override
    public Graph<ID, T> addE(T from, T to) {
        adjSource.addE(from, to);
        return this;
    }
    
    @Override
    public Set<T> vertices() {
        return adjSource.vertices();
    }
    
    @Override
    public Set<Edge<T>> outEdges(T from) {
        return adjSource.outEdges(from);
    }
    
    @Override
    public T findV(ID label) {
        return adjSource.findV(label);
    }
    
    @Override
    public LinkedList<T> shortestPath(T from, T to) {
        return pathStrategyFactory.newInstance(adjSource).shortestPath(from, to);
    }
}
