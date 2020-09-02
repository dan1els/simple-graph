package name.dan1els.simplegraph;

import name.dan1els.simplegraph.strategy.ShortPathStrategyFactory;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class DirectedGraph<ID, T> implements Graph<ID, T> {
    
    private final Map<Vertex<ID, T>, Set<Edge<ID, T>>> adjMap;
    private final ShortPathStrategyFactory<ID, T> strategyFactory;
    
    public DirectedGraph(ShortPathStrategyFactory<ID, T> strategyFactory) {
        this.strategyFactory = strategyFactory;
        this.adjMap = new ConcurrentHashMap<>();
    }
    
    @Override
    public DirectedGraph<ID, T> addV(Vertex<ID, T> vertex) {
        adjMap.putIfAbsent(vertex, new HashSet<>());
        return this;
    }
    
    @Override
    public DirectedGraph<ID, T> addE(Vertex<ID, T> from, Vertex<ID, T> to) {
        addV(to);
        synchronized (this) {
            var edges = adjMap.getOrDefault(from, new HashSet<>());
            edges.add(new Edge<>(to));
            adjMap.put(from, edges);
        }
        return this;
    }
    
    @Override
    public Set<Vertex<ID, T>> vertices() {
        return adjMap.keySet()
            .stream()
            .collect(Collectors.toUnmodifiableSet());
    }
    
    @Override
    public Set<Edge<ID, T>> outEdges(Vertex<ID, T> from) {
        return Set.copyOf(adjMap.get(from));
    }
    
    @Override
    public Vertex<ID, T> findV(ID label) {
        return adjMap.keySet()
            .stream()
            .filter(v -> v.hasLabel(label))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }
    
    @Override
    public LinkedList<Vertex<ID, T>> shortestPath(Vertex<ID, T> from, Vertex<ID, T> to) {
        return strategyFactory.newStrategy(adjMap).shortestPath(from, to);
    }
}
