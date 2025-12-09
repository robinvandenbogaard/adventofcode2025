package nl.roka.adventofcode.aoc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Graph<V> {
  private final HashMap<V, List<V>> graph;

  public Graph() {
    graph = new HashMap<>();
  }

  void add(V vertex, V edge) {
    graph.computeIfAbsent(vertex, k -> new ArrayList<>()).add(edge);
    graph.computeIfAbsent(edge, k -> new ArrayList<>());
  }

  public boolean isSorted(List<V> digits) {
    var sorted = true;
    for (int i = 1; i < digits.size() && sorted; i++) {
      sorted = canReach(digits.get(i - 1), digits.get(i));
    }
    return sorted;
  }

  public boolean canReach(V start, V target) {
    var visited = new ArrayList<V>();
    dfs(start, visited);
    return visited.contains(target);
  }

  private void dfs(V node, List<V> visited) {
    visited.add(node);
    for (V next : graph.get(node)) {
      if (!visited.contains(next)) {
        dfs(next, visited);
      }
    }
  }

  /**
   * Kahn's Algorithm
   *
   * @return sorted topological nodes
   */
  public List<V> topologicalNodeOrder() {
    // Compute in-degree for each node
    HashMap<V, Integer> inDegree = new HashMap<>();
    for (V node : graph.keySet()) {
      inDegree.putIfAbsent(node, 0);
      for (V neighbor : graph.get(node)) {
        inDegree.put(neighbor, inDegree.getOrDefault(neighbor, 0) + 1);
      }
    }

    // Add nodes with in-degree of 0 to the queue
    Queue<V> queue = new LinkedList<>();
    for (Map.Entry<V, Integer> entry : inDegree.entrySet()) {
      if (entry.getValue() == 0) {
        queue.add(entry.getKey());
      }
    }

    // Perform topological sort
    List<V> topologicalOrder = new ArrayList<>();
    while (!queue.isEmpty()) {
      V current = queue.poll();
      topologicalOrder.add(current);

      // Decrease in-degree of neighbors
      for (V neighbor : graph.getOrDefault(current, new ArrayList<>())) {
        inDegree.put(neighbor, inDegree.get(neighbor) - 1);
        if (inDegree.get(neighbor) == 0) {
          queue.add(neighbor);
        }
      }
    }

    // Check if topological sort is valid (DAG condition)
    if (topologicalOrder.size() != graph.size()) {
      throw new IllegalArgumentException("The graph is not a DAG (it contains a cycle).");
    }

    return topologicalOrder;
  }
}
