package name.dan1els.simplegraph.graph;

import name.dan1els.simplegraph.edge.Edge;
import name.dan1els.simplegraph.edge.EdgeFactory;
import name.dan1els.simplegraph.source.AdjacencySource;
import name.dan1els.simplegraph.strategy.ShortPathStrategyFactory;
import name.dan1els.simplegraph.vertex.Vertex;

import java.util.LinkedList;
import java.util.Set;

public class DirectedGraph<ID, V extends Vertex<ID, ?>, E extends Edge<V>> implements Graph<ID, V, E> {
    
    private final AdjacencySource<ID, V, E> adjSource;
    private final ShortPathStrategyFactory<ID, V, E> pathStrategyFactory;
    
    public DirectedGraph(
        AdjacencySource<ID, V, E> adjacencySource,
        ShortPathStrategyFactory<ID, V, E> pathStrategyFactory
    ) {
        this.adjSource = adjacencySource;
        this.pathStrategyFactory = pathStrategyFactory;
    }
    
    @Override
    public Graph<ID, V, E> addV(V vertex) {
        adjSource.addV(vertex);
        return this;
    }
    
    @Override
    public Graph<ID, V, E> addE(V from, V to, EdgeFactory<V, E> edgeFactory) {
        adjSource.addE(from, to, edgeFactory);
        return this;
    }
    
    @Override
    public Set<V> vertices() {
        return adjSource.vertices();
    }
    
    @Override
    public Set<E> outEdges(V from) {
        return adjSource.outEdges(from);
    }
    
    @Override
    public V findV(ID label) {
        return adjSource.findV(label);
    }
    
    @Override
    public LinkedList<V> shortestPath(V from, V to) {
        return pathStrategyFactory.newInstance(adjSource).shortestPath(from, to);
    }
}
