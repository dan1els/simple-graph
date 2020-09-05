package name.dan1els.simplegraph.source;

import name.dan1els.simplegraph.Edge;
import name.dan1els.simplegraph.Vertex;

import java.util.Set;

public interface AdjacencySource<ID, T extends Vertex<ID, ?>> {
    void addV(T vertex);
    
    void addE(T from, T to);
    
    Set<T> vertices();
    
    Set<Edge<T>> outEdges(T from);
    
    T findV(ID label);
}
