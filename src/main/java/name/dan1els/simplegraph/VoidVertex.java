package name.dan1els.simplegraph;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * This type is useful if you don't want to have any data inside vertices.
 * @param <ID>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VoidVertex<ID> extends Vertex<ID, Void> {
    
    public VoidVertex(ID label) {
        super(label, null);
    }
    
    @Override
    public Void getPayload() {
        throw new IllegalStateException("Can't get payload from void vertex");
    }
}
