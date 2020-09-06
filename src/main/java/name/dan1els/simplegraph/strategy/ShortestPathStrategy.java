package name.dan1els.simplegraph.strategy;

import name.dan1els.simplegraph.edge.Edge;
import name.dan1els.simplegraph.vertex.Vertex;

import java.util.LinkedList;

public interface ShortestPathStrategy<ID, V extends Vertex<ID, ?>, E extends Edge<V>> {
    
    LinkedList<V> shortestPath(V from, V to);
    
}
