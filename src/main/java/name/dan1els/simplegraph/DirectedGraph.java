package name.dan1els.simplegraph;

import name.dan1els.simplegraph.source.AdjacencyList;
import name.dan1els.simplegraph.strategy.ShortPathStrategyFactory;

import java.util.LinkedList;
import java.util.Set;

public class DirectedGraph<ID, T> implements Graph<ID, T> {
    
    private final AdjacencyList<ID, T> adjSource;
    private final ShortPathStrategyFactory<ID, T> pathStrategyFactory;
    
    public DirectedGraph(ShortPathStrategyFactory<ID, T> pathStrategyFactory) {
        this.pathStrategyFactory = pathStrategyFactory;
        this.adjSource = new AdjacencyList<>();
    }
    
    @Override
    public Graph<ID, T> addV(Vertex<ID, T> vertex) {
        adjSource.addV(vertex);
        return this;
    }
    
    @Override
    public Graph<ID, T> addE(Vertex<ID, T> from, Vertex<ID, T> to) {
        adjSource.addE(from, to);
        return this;
    }
    
    @Override
    public Set<Vertex<ID, T>> vertices() {
        return adjSource.vertices();
    }
    
    @Override
    public Set<Edge<ID, T>> outEdges(Vertex<ID, T> from) {
        return adjSource.outEdges(from);
    }
    
    @Override
    public Vertex<ID, T> findV(ID label) {
        return adjSource.findV(label);
    }
    
    @Override
    public LinkedList<Vertex<ID, T>> shortestPath(Vertex<ID, T> from, Vertex<ID, T> to) {
        return pathStrategyFactory.newInstance(adjSource).shortestPath(from, to);
    }
}
