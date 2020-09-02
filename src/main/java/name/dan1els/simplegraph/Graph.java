package name.dan1els.simplegraph;

import java.util.LinkedList;
import java.util.Set;

public interface Graph<ID, T> {
    
    DirectedGraph<ID, T> addV(Vertex<ID, T> vertex);
    
    DirectedGraph<ID, T> addE(Vertex<ID, T> from, Vertex<ID, T> to);
    
    Set<Vertex<ID, T>> vertices();
    
    Set<Edge<ID, T>> outEdges(Vertex<ID, T> from);
    
    Vertex<ID, T> findV(ID label);
    
    LinkedList<Vertex<ID, T>> shortestPath(Vertex<ID, T> from, Vertex<ID, T> to);
}
