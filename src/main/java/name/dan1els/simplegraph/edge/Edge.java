package name.dan1els.simplegraph.edge;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import name.dan1els.simplegraph.vertex.Vertex;

@EqualsAndHashCode
@ToString
public class Edge<V extends Vertex<?, ?>> {
    
    private final V to;
    
    public Edge(V to) {
        this.to = to;
    }
    
    public V outV() {
        return to;
    }
}