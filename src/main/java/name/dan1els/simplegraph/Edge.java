package name.dan1els.simplegraph;

import lombok.ToString;

@ToString
public class Edge<T> {
    
    public final Vertex<T> to;
    //TODO: TBD
    private final double weight = 1;
    
    public Edge(Vertex<T> to) {
        this.to = to;
    }
}