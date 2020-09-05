package name.dan1els.simplegraph.strategy;

import name.dan1els.simplegraph.Vertex;

import java.util.LinkedList;

public interface ShortestPathStrategy<ID, T extends Vertex<ID, ?>> {
    
    LinkedList<T> shortestPath(T from, T to);
    
}
