package name.dan1els.simplegraph.strategy;

import name.dan1els.simplegraph.Vertex;

import java.util.LinkedList;

public interface ShortPathStrategy<ID, T> {
    
    LinkedList<Vertex<ID, T>> shortestPath(Vertex<ID, T> from, Vertex<ID, T> to);
    
}
