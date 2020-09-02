package name.dan1els.simplegraph.path;

import name.dan1els.simplegraph.Edge;
import name.dan1els.simplegraph.Vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation of Dijkstra shortest path algo.
 *
 * Not thread-safe.
 * @param <ID, T> -- type of vertices.
 */
public class DijkstraPathStrategy<ID extends Comparable<?>, T> implements PathStrategy<ID, T> {
    
    private final LinkedList<Vertex<ID, T>> unsettledQueue = new LinkedList<>();
    private final Set<Vertex<ID, T>> visitedVs = new HashSet<>();
    private final Map<Vertex<ID, T>, Vertex<ID, T>> predecessors = new HashMap<>();
    
    private final Map<Vertex<ID, T>, Set<Edge<ID, T>>> adjSource;
    private final Map<Vertex<ID, T>, Integer> distances;
    
    public DijkstraPathStrategy(Map<Vertex<ID, T>, Set<Edge<ID, T>>> adjSource) {
        this.adjSource = adjSource;
        this.distances = adjSource.keySet()
            .stream()
            .collect(Collectors.toMap(Function.identity(), v -> Integer.MAX_VALUE));
    }
    
    public LinkedList<Vertex<ID, T>> shortestPath(Vertex<ID, T> from, Vertex<ID, T> to) {
        initializeTraversing(from);
        return traverse(to) ? path(to) : new LinkedList<>();
    }
    
    private void initializeTraversing(Vertex<ID, T> from) {
        visitedVs.add(from);
        distances.put(from, 0);
        unsettledQueue.offer(from);
    }
    
    private boolean traverse(Vertex<ID, T> destination) {
        while (!unsettledQueue.isEmpty()) {
            var current = unsettledQueue.poll();
            for (Edge<ID, T> edge : adjSource.get(current)) {
                var neighbour = edge.outV();
                if (!visitedVs.contains(neighbour)) {
                    visitedVs.add(neighbour);
                    distances.put(neighbour, distances.get(current) + 1);
                    predecessors.put(neighbour, current);
    
                    if (neighbour == destination)
                        return true;
                    else
                        unsettledQueue.offer(neighbour);
                }
            }
        }
        return false;
    }
    
    private LinkedList<Vertex<ID, T>> path(Vertex<ID, T> destination) {
        var path = new LinkedList<Vertex<ID, T>>() {{
            add(destination);
        }};
        var cursor = destination;
        while (predecessors.get(cursor) != null) {
            path.addFirst(predecessors.get(cursor));
            cursor = predecessors.get(cursor);
        }
        return path;
    }
}
