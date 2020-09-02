package name.dan1els.simplegraph;

import name.dan1els.simplegraph.strategy.ShortPathStrategyFactory;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class DirectedGraph<ID, T> implements Graph<ID, T> {
    
    private final Map<Vertex<ID, T>, Set<Edge<ID, T>>> adjSource;
    private final ShortPathStrategyFactory<ID, T> pathStrategyFactory;
    
    public DirectedGraph(ShortPathStrategyFactory<ID, T> pathStrategyFactory) {
        this.pathStrategyFactory = pathStrategyFactory;
        this.adjSource = new ConcurrentHashMap<>();
    }
    
    @Override
    public Graph<ID, T> addV(Vertex<ID, T> vertex) {
        adjSource.putIfAbsent(vertex, new HashSet<>());
        return this;
    }
    
    @Override
    public Graph<ID, T> addE(Vertex<ID, T> from, Vertex<ID, T> to) {
        addV(to);
        synchronized (this) {
            var edges = adjSource.getOrDefault(from, new HashSet<>());
            edges.add(new Edge<>(to));
            adjSource.put(from, edges);
        }
        return this;
    }
    
    @Override
    public Set<Vertex<ID, T>> vertices() {
        return adjSource.keySet()
            .stream()
            .collect(Collectors.toUnmodifiableSet());
    }
    
    @Override
    public Set<Edge<ID, T>> outEdges(Vertex<ID, T> from) {
        return Set.copyOf(adjSource.get(from));
    }
    
    @Override
    public Vertex<ID, T> findV(ID label) {
        return adjSource.keySet()
            .stream()
            .filter(v -> v.hasLabel(label))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }
    
    @Override
    public LinkedList<Vertex<ID, T>> shortestPath(Vertex<ID, T> from, Vertex<ID, T> to) {
        return pathStrategyFactory.newInstance(adjSource).shortestPath(from, to);
    }
}
