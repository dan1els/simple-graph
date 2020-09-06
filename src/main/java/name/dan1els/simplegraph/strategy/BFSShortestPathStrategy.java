package name.dan1els.simplegraph.strategy;

import name.dan1els.simplegraph.edge.Edge;
import name.dan1els.simplegraph.source.AdjacencySource;
import name.dan1els.simplegraph.vertex.Vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of BFS-based shortest path algo.
 * Works correct only for unweighted edges.
 * Not thread-safe.
 *
 * @param <ID> -- vertex label type.
 * @param <V> -- vertex payload type.
 */
public class BFSShortestPathStrategy<ID, V extends Vertex<ID, ?>, E extends Edge<V>> implements ShortestPathStrategy<ID, V, E> {
    
    private final LinkedList<V> unsettledQueue = new LinkedList<>();
    private final Set<V> visitedVs = new HashSet<>();
    private final Map<V, V> predecessors = new HashMap<>();
    
    private final AdjacencySource<ID, V, E> adjSource;
    
    public BFSShortestPathStrategy(AdjacencySource<ID, V, E> adjSource) {
        this.adjSource = adjSource;
    }
    
    public LinkedList<V> shortestPath(V from, V to) {
        initializeTraversing(from);
        return traverse(to) ? path(to) : new LinkedList<>();
    }
    
    private void initializeTraversing(V from) {
        visitedVs.add(from);
        unsettledQueue.offer(from);
    }
    
    private boolean traverse(V destination) {
        while (!unsettledQueue.isEmpty()) {
            var current = unsettledQueue.poll();
            for (E edge : adjSource.outEdges(current)) {
                var neighbour = edge.outV();
                if (!visitedVs.contains(neighbour)) {
                    visitedVs.add(neighbour);
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
    
    private LinkedList<V> path(V destination) {
        var path = new LinkedList<V>() {{
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
