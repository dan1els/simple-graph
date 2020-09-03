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
public class BFSShortestPathStrategy<ID, T> implements ShortestPathStrategy<ID, T> {
    
    private final LinkedList<Vertex<ID, T>> unsettledQueue = new LinkedList<>();
    private final Set<Vertex<ID, T>> visitedVs = new HashSet<>();
    private final Map<Vertex<ID, T>, Vertex<ID, T>> predecessors = new HashMap<>();
    
    private final AdjacencySource<ID, T> adjSource;
    
    public BFSShortestPathStrategy(AdjacencySource<ID, T> adjSource) {
        this.adjSource = adjSource;
    }
    
    public LinkedList<Vertex<ID, T>> shortestPath(Vertex<ID, T> from, Vertex<ID, T> to) {
        initializeTraversing(from);
        return traverse(to) ? path(to) : new LinkedList<>();
    }
    
    private void initializeTraversing(Vertex<ID, T> from) {
        visitedVs.add(from);
        unsettledQueue.offer(from);
    }
    
    private boolean traverse(Vertex<ID, T> destination) {
        while (!unsettledQueue.isEmpty()) {
            var current = unsettledQueue.poll();
            for (Edge<ID, T> edge : adjSource.outEdges(current)) {
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
