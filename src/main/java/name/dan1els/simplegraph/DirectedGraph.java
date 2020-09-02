package name.dan1els.simplegraph;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class DirectedGraph<T> {
    
    private final Map<Vertex<T>, Set<Edge<T>>> adjMap;
    
    public DirectedGraph() {
        adjMap = new ConcurrentHashMap<>();
    }
    
    public DirectedGraph<T> addV(Vertex<T> vertex) {
        adjMap.putIfAbsent(vertex, new HashSet<>());
        return this;
    }
    
    public DirectedGraph<T> addE(Vertex<T> from, Vertex<T> to) {
        addV(to);
        synchronized (this) {
            var edges = adjMap.getOrDefault(from, new HashSet<>());
            edges.add(new Edge<>(to));
            adjMap.put(from, edges);
        }
        return this;
    }
    
    public Set<Vertex<T>> vertices() {
        return adjMap.keySet()
            .stream()
            .collect(Collectors.toUnmodifiableSet());
    }
    
    public Set<Edge<T>> outEdges(Vertex<T> from) {
        return Set.copyOf(adjMap.get(from));
    }
}
