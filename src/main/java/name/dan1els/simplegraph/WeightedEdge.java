package name.dan1els.simplegraph;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
public class WeightedEdge<W extends Comparable<W>, T extends Vertex<?,?>> extends Edge<T> {
    
    @EqualsAndHashCode.Exclude
    private final W weight;
    
    public WeightedEdge(T to, W weight) {
        super(to);
        this.weight = weight;
    }
}
