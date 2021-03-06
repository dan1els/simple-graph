package name.dan1els.simplegraph.source;

import name.dan1els.simplegraph.edge.Edge;
import name.dan1els.simplegraph.edge.EdgeFactory;
import name.dan1els.simplegraph.vertex.Vertex;

import java.util.Set;
import java.util.function.Function;

public interface AdjacencySource<ID, V extends Vertex<ID, ?>, E extends Edge<V>> {
    void addV(V vertex);
    
    void addE(V from, V to, EdgeFactory<V, E> egdeFactory);
    
    Set<V> vertices();
    
    Set<E> outEdges(V from);
    
    V findV(ID label);
}
