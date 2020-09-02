package name.dan1els.simplegraph;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Vertex<ID extends Comparable<ID>, T> {
    
    private final ID label;
    @EqualsAndHashCode.Exclude
    @Getter
    private final T payload;
    
    public Vertex(ID label, T payload) {
        this.label = label;
        this.payload = payload;
    }
    
    public boolean hasLabel(ID label) {
        return this.label.equals(label);
    }
}
