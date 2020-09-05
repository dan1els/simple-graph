package name.dan1els.simplegraph;

import name.dan1els.simplegraph.source.AdjacencySource;
import name.dan1els.simplegraph.strategy.ShortPathStrategyFactory;

import java.util.LinkedList;
import java.util.Set;

public class UndirectedGraph<ID, T extends Vertex<ID, ?>> implements Graph<ID, T> {
    
    private final DirectedGraph<ID, T> decorated;
    
    public UndirectedGraph(
        AdjacencySource<ID, T> adjacencySource,
        ShortPathStrategyFactory<ID, T> pathStrategyFactory
    ) {
        decorated = new DirectedGraph<>(adjacencySource, pathStrategyFactory);
    }
    
    @Override
    public Graph<ID, T> addV(T vertex) {
        decorated.addV(vertex);
        return this;
    }
    
    @Override
    public Graph<ID, T> addE(T from, T to) {
        decorated.addE(from, to);
        decorated.addE(to, from);
        return this;
    }
    
    @Override
    public Set<T> vertices() {
        return decorated.vertices();
    }
    
    @Override
    public Set<Edge<T>> outEdges(T from) {
        return decorated.outEdges(from);
    }
    
    @Override
    public T findV(ID label) {
        return decorated.findV(label);
    }
    
    @Override
    public LinkedList<T> shortestPath(T from, T to) {
        return decorated.shortestPath(from, to);
    }
}
