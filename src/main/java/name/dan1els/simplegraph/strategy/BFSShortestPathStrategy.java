package name.dan1els.simplegraph.strategy;

import name.dan1els.simplegraph.Edge;
import name.dan1els.simplegraph.Vertex;
import name.dan1els.simplegraph.source.AdjacencySource;

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
 * @param <T> -- vertex payload type.
 */
public class BFSShortestPathStrategy<ID, T extends Vertex<ID, ?>> implements ShortestPathStrategy<ID, T> {
    
    private final LinkedList<T> unsettledQueue = new LinkedList<>();
    private final Set<T> visitedVs = new HashSet<>();
    private final Map<T, T> predecessors = new HashMap<>();
    
    private final AdjacencySource<ID, T> adjSource;
    
    public BFSShortestPathStrategy(AdjacencySource<ID, T> adjSource) {
        this.adjSource = adjSource;
    }
    
    public LinkedList<T> shortestPath(T from, T to) {
        initializeTraversing(from);
        return traverse(to) ? path(to) : new LinkedList<>();
    }
    
    private void initializeTraversing(T from) {
        visitedVs.add(from);
        unsettledQueue.offer(from);
    }
    
    private boolean traverse(T destination) {
        while (!unsettledQueue.isEmpty()) {
            var current = unsettledQueue.poll();
            for (Edge<T> edge : adjSource.outEdges(current)) {
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
    
    private LinkedList<T> path(T destination) {
        var path = new LinkedList<T>() {{
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
