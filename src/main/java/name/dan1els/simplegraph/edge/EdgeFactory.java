package name.dan1els.simplegraph.edge;

import name.dan1els.simplegraph.vertex.Vertex;

public interface EdgeFactory<V extends Vertex<?,?>, E extends Edge<V>> {
    
     E newEdge(V to);
}
