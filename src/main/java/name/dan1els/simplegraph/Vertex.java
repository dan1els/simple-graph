package name.dan1els.simplegraph;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@EqualsAndHashCode
@ToString
public class Vertex<T> {
    
     private final UUID uuid;
     @EqualsAndHashCode.Exclude
     private final T payload;
     
     public Vertex(T payload) {
         this.uuid = randomUUID();
         this.payload = payload;
     }
}
