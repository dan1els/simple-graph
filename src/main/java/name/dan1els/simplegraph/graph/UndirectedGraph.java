package name.dan1els.simplegraph.graph;

import name.dan1els.simplegraph.edge.Edge;
import name.dan1els.simplegraph.edge.EdgeFactory;
import name.dan1els.simplegraph.source.AdjacencySource;
import name.dan1els.simplegraph.strategy.ShortPathStrategyFactory;
import name.dan1els.simplegraph.vertex.Vertex;

import java.util.LinkedList;
import java.util.Set;

public class UndirectedGraph<ID, V extends Vertex<ID, ?>, E extends Edge<V>> implements Graph<ID, V, E> {
    
    private final DirectedGraph<ID, V, E> decorated;
    
    public UndirectedGraph(
        AdjacencySource<ID, V, E> adjacencySource,
        ShortPathStrategyFactory<ID, V, E> pathStrategyFactory
    ) {
        decorated = new DirectedGraph<>(adjacencySource, pathStrategyFactory);
    }
    
    @Override
    public Graph<ID, V, E> addV(V vertex) {
        decorated.addV(vertex);
        return this;
    }
    
    @Override
    public Graph<ID, V, E> addE(V from, V to, EdgeFactory<V, E> edgeFactory) {
        decorated.addE(from, to, edgeFactory);
        decorated.addE(to, from, edgeFactory);
        return this;
    }
    
    @Override
    public Set<V> vertices() {
        return decorated.vertices();
    }
    
    @Override
    public Set<E> outEdges(V from) {
        return decorated.outEdges(from);
    }
    
    @Override
    public V findV(ID label) {
        return decorated.findV(label);
    }
    
    @Override
    public LinkedList<V> shortestPath(V from, V to) {
        return decorated.shortestPath(from, to);
    }
}
