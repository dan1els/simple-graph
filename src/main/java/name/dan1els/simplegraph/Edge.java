package name.dan1els.simplegraph;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Edge<ID, T> {
    
    private final Vertex<ID, T> to;
    
    public Edge(Vertex<ID, T> to) {
        this.to = to;
    }
    
    public Vertex<ID, T> outV() {
        return to;
    }
}