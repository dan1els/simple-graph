package name.dan1els.simplegraph.source;

import name.dan1els.simplegraph.Edge;
import name.dan1els.simplegraph.Vertex;

import java.util.Set;
import java.util.function.Function;

public interface AdjacencySource<ID, V extends Vertex<ID, ?>, E extends Edge<V>> {
    void addV(V vertex);
    
    void addE(V from, V to, Function<V, E> egdeCtor);
    
    Set<V> vertices();
    
    Set<E> outEdges(V from);
    
    V findV(ID label);
}
