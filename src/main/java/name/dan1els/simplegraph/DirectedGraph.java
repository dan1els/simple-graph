package name.dan1els.simplegraph;

import name.dan1els.simplegraph.path.PathStrategy;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DirectedGraph<ID extends Comparable<?>, T> {
    
    private final Map<Vertex<ID, T>, Set<Edge<ID, T>>> adjMap;
    private final Function<Map<Vertex<ID, T>, Set<Edge<ID, T>>>, PathStrategy<ID, T>> pathStrategyProvider;
    
    public DirectedGraph(
        Function<Map<Vertex<ID, T>, Set<Edge<ID, T>>>, PathStrategy<ID, T>> pathStrategyProvider
    ) {
        this.pathStrategyProvider = pathStrategyProvider;
        adjMap = new ConcurrentHashMap<>();
    }
    
    public DirectedGraph<ID, T> addV(Vertex<ID, T> vertex) {
        adjMap.putIfAbsent(vertex, new HashSet<>());
        return this;
    }
    
    public DirectedGraph<ID, T> addE(Vertex<ID, T> from, Vertex<ID, T> to) {
        addV(to);
        synchronized (this) {
            var edges = adjMap.getOrDefault(from, new HashSet<>());
            edges.add(new Edge<>(to));
            adjMap.put(from, edges);
        }
        return this;
    }
    
    public Set<Vertex<ID, T>> vertices() {
        return adjMap.keySet()
            .stream()
            .collect(Collectors.toUnmodifiableSet());
    }
    
    public Set<Edge<ID, T>> outEdges(Vertex<ID, T> from) {
        return Set.copyOf(adjMap.get(from));
    }
    
    public Vertex<ID, T> findV(ID label) {
        return adjMap.keySet()
            .stream()
            .filter(v -> v.hasLabel(label))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }
    
    public LinkedList<Vertex<ID, T>> shortestPath(Vertex<ID, T> from, Vertex<ID, T> to) {
        return pathStrategyProvider.apply(adjMap).shortestPath(from, to);
    }
}
