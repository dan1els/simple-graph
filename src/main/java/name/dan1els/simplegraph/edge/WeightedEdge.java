package name.dan1els.simplegraph.edge;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import name.dan1els.simplegraph.vertex.Vertex;

@EqualsAndHashCode(callSuper = true)
@ToString
public class WeightedEdge<W extends Comparable<W>, V extends Vertex<?,?>> extends Edge<V> {
    
    @EqualsAndHashCode.Exclude
    private final W weight;
    
    public WeightedEdge(V to, W weight) {
        super(to);
        this.weight = weight;
    }
}
