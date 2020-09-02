package name.dan1els.simplegraph.source;

import name.dan1els.simplegraph.Edge;
import name.dan1els.simplegraph.Vertex;

import java.util.Set;

public interface AdjacencySource<ID, T> {
    void addV(Vertex<ID, T> vertex);
    
    void addE(Vertex<ID, T> from, Vertex<ID, T> to);
    
    Set<Vertex<ID, T>> vertices();
    
    Set<Edge<ID, T>> outEdges(Vertex<ID, T> from);
    
    Vertex<ID, T> findV(ID label);
}
