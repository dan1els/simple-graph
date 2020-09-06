package name.dan1els.simplegraph.source;

import name.dan1els.simplegraph.edge.Edge;
import name.dan1els.simplegraph.vertex.Vertex;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Adjacency list graph source implementation.
 *
 * This object is thread safe.
 *
 * @param <V> -- type of vertex payload.
 */
public class AdjacencyList<ID, V extends Vertex<ID, ?>, E extends Edge<V>> implements AdjacencySource<ID, V, E> {
    
    private final Map<V, Set<E>> source;
    
    public AdjacencyList() {
        source = new ConcurrentHashMap<>();
    }
    
    @Override
    public void addV(V vertex) {
        source.putIfAbsent(vertex, new HashSet<>());
    }
    
    @Override
    public void addE(V from, V to, Function<V,E> edgeCtor) {
        addV(to);
        synchronized (this) {
            var edges = source.getOrDefault(from, new HashSet<>());
            edges.add(edgeCtor.apply(to));
            source.put(from, edges);
        }
    }
    
    @Override
    public Set<V> vertices() {
        return source.keySet()
            .stream()
            .collect(Collectors.toUnmodifiableSet());
    }
    
    @Override
    public Set<E> outEdges(V from) {
        return Set.copyOf(source.get(from));
    }
    
    @Override
    public V findV(ID label) {
        return source.keySet()
            .stream()
            .filter(v -> v.hasLabel(label))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }
}
