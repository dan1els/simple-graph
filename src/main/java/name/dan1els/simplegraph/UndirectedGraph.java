package name.dan1els.simplegraph;

import name.dan1els.simplegraph.strategy.ShortPathStrategyFactory;

import java.util.LinkedList;
import java.util.Set;

public class UndirectedGraph<ID, T> implements Graph<ID, T> {
    
    private final DirectedGraph<ID, T> decorated;
    
    public UndirectedGraph(ShortPathStrategyFactory<ID, T> pathStrategyFactory) {
        decorated = new DirectedGraph<>(pathStrategyFactory);
    }
    
    @Override
    public Graph<ID, T> addV(Vertex<ID, T> vertex) {
        decorated.addV(vertex);
        return this;
    }
    
    @Override
    public Graph<ID, T> addE(Vertex<ID, T> from, Vertex<ID, T> to) {
        decorated.addE(from, to);
        decorated.addE(to, from);
        return this;
    }
    
    @Override
    public Set<Vertex<ID, T>> vertices() {
        return decorated.vertices();
    }
    
    @Override
    public Set<Edge<ID, T>> outEdges(Vertex<ID, T> from) {
        return decorated.outEdges(from);
    }
    
    @Override
    public Vertex<ID, T> findV(ID label) {
        return decorated.findV(label);
    }
    
    @Override
    public LinkedList<Vertex<ID, T>> shortestPath(Vertex<ID, T> from, Vertex<ID, T> to) {
        return decorated.shortestPath(from, to);
    }
}
