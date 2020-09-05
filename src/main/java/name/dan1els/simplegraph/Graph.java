package name.dan1els.simplegraph;

import java.util.LinkedList;
import java.util.Set;

public interface Graph<ID, T extends Vertex<ID, ?>> {
    
    Graph<ID, T> addV(T vertex);
    
    Graph<ID, T> addE(T from, T to);
    
    Set<T> vertices();
    
    Set<Edge<T>> outEdges(T from);
    
    T findV(ID label);
    
    LinkedList<T> shortestPath(T from, T to);
}
