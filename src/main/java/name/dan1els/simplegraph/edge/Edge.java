package name.dan1els.simplegraph.edge;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import name.dan1els.simplegraph.vertex.Vertex;

@EqualsAndHashCode
@ToString
public class Edge<T extends Vertex<?, ?>> {
    
    private final T to;
    
    public Edge(T to) {
        this.to = to;
    }
    
    public T outV() {
        return to;
    }
}