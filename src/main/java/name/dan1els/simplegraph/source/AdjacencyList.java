package name.dan1els.simplegraph.source;

import name.dan1els.simplegraph.Edge;
import name.dan1els.simplegraph.Vertex;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Adjacency list graph source implementation.
 *
 * This object is thread safe.
 *
 * @param <ID> -- type of vertex label;
 * @param <T> -- type of vertex payload.
 */
public class AdjacencyList<ID, T> implements AdjacencySource<ID, T> {
    
    private final Map<Vertex<ID, T>, Set<Edge<ID, T>>> source;
    
    public AdjacencyList() {
        source = new ConcurrentHashMap<>();
    }
    
    @Override
    public void addV(Vertex<ID, T> vertex) {
        source.putIfAbsent(vertex, new HashSet<>());
    }
    
    @Override
    public void addE(Vertex<ID, T> from, Vertex<ID, T> to) {
        addV(to);
        synchronized (this) {
            var edges = source.getOrDefault(from, new HashSet<>());
            edges.add(new Edge<>(to));
            source.put(from, edges);
        }
    }
    
    @Override
    public Set<Vertex<ID, T>> vertices() {
        return source.keySet()
            .stream()
            .collect(Collectors.toUnmodifiableSet());
    }
    
    @Override
    public Set<Edge<ID, T>> outEdges(Vertex<ID, T> from) {
        return Set.copyOf(source.get(from));
    }
    
    @Override
    public Vertex<ID, T> findV(ID label) {
        return source.keySet()
            .stream()
            .filter(v -> v.hasLabel(label))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }
}
