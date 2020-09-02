package name.dan1els.simplegraph;

import lombok.ToString;

@ToString
public class Edge<ID, T> {
    
    private final Vertex<ID, T> to;
    //TODO: TBD
    private final double weight = 1;
    
    public Edge(Vertex<ID, T> to) {
        this.to = to;
    }
    
    public Vertex<ID, T> outV() {
        return to;
    }
}