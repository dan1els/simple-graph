package name.dan1els.simplegraph;

import lombok.EqualsAndHashCode;
import lombok.ToString;

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