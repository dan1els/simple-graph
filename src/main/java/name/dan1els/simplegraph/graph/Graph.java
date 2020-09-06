package name.dan1els.simplegraph.graph;

import name.dan1els.simplegraph.edge.Edge;
import name.dan1els.simplegraph.edge.EdgeFactory;
import name.dan1els.simplegraph.vertex.Vertex;

import java.util.LinkedList;
import java.util.Set;

public interface Graph<ID, V extends Vertex<ID, ?>, E extends Edge<V>> {
    
    Graph<ID, V, E> addV(V vertex);
    
    Graph<ID, V, E> addE(V from, V to, EdgeFactory<V, E> edgeFactory);
    
    Set<V> vertices();
    
    Set<E> outEdges(V from);
    
    V findV(ID label);
    
    LinkedList<V> shortestPath(V from, V to);
}
