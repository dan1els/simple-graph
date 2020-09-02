package name.dan1els.simplegraph.path;

import name.dan1els.simplegraph.Vertex;

import java.util.LinkedList;

public interface PathStrategy<ID extends Comparable<?>, T> {
    
    LinkedList<Vertex<ID, T>> shortestPath(Vertex<ID, T> from, Vertex<ID, T> to);
    
}
