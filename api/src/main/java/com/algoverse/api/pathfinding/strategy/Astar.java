package com.algoverse.api.pathfinding.strategy;

import static java.lang.Math.abs;

import com.algoverse.api.pathfinding.board.BoardInformation;
import com.algoverse.api.pathfinding.board.Coordinates;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of A*. We use only the heuristic function, because the cost for to the
 * next node is always the same.
 */
public class Astar implements PathFindingStrategy {

  private Astar() {

  }

  /**
   * Static factory method.
   *
   * @return Return an Astar object
   */
  public static Astar createAstar() {
    return new Astar();
  }

  @Override
  public Path findPath(BoardInformation board) {

    final Coordinates startingNode = board.startingNode();
    final Coordinates endingNode = board.endingNode();
    final ImmutableMap<Coordinates, Integer> obstacle = board.wall();
    final Coordinates boardSize = board.boardSize();

    if (startingNode.equals(endingNode)) {
      return new Path(ImmutableList.copyOf(ImmutableList.of(startingNode)),
          ImmutableList.copyOf(ImmutableList.of(startingNode)));
    }

    List<Tuple> nextNode = new ArrayList<>();
    nextNode.add(new Tuple(startingNode, 0, 0));

    List<Coordinates> visitedNodes = new ArrayList<>();
    HashMap<Coordinates, Integer> visitedNodesMap = new HashMap<>();
    HashMap<Coordinates, Coordinates> parent = new HashMap<>();

    while (!nextNode.isEmpty()) {
      Tuple nextTuple = nextNode.remove(0);
      Coordinates node = nextTuple.getNode();
      visitedNodes.add(node);
      visitedNodesMap.put(node, nextTuple.getCost());

      if (node.equals(endingNode)) {
        return reconstructPath(startingNode, endingNode, visitedNodes, parent);
      }

      List<Coordinates> neighbors = getNeighbors(node, obstacle, visitedNodesMap, boardSize);

      for (Coordinates neighbor : neighbors) {
        // cost and dist is irrelevant for equality, check if node is in nextNode
        if (!nextNode.contains(new Tuple(neighbor, 0, 0))) {
          parent.put(neighbor, node);
          nextNode.add(new Tuple(neighbor, nextTuple.getCost() + 1,
              heuristicFunction(neighbor, endingNode)));
        } else {
          int index = nextNode.indexOf(new Tuple(neighbor, 0, 0));
          Tuple tuple = nextNode.get(index);
          // if better less cost, change parent and update to nextNode
          if (tuple.getCost() > nextTuple.getCost() + 1) {
            parent.put(neighbor, node);
            nextNode.add(new Tuple(neighbor, nextTuple.getCost() + 1,
                heuristicFunction(neighbor, endingNode)));
          }
        }
        // sort nextNode, so the first element is the best choice
        nextNode.sort(Tuple::compareTo);
      }
    }
    return reconstructPath(startingNode, endingNode, visitedNodes, parent);
  }

  /**
   * Calculates the manhattan distance.
   *
   * @param startNode The starting point
   * @param endNode   The ending point
   * @return Returns the distance
   */
  private int heuristicFunction(Coordinates startNode, Coordinates endNode) {
    return abs(startNode.x() - endNode.x()) + abs(startNode.y() - endNode.y());
  }

  /**
   * Tuple class to store information for about the nodes.
   */
  private static class Tuple implements Comparable<Tuple> {

    private final Coordinates node;
    private final int cost;

    private final  int dist;

    public Tuple(Coordinates node, int cost, int dist) {
      this.node = node;
      this.cost = cost;
      this.dist = dist;
    }

    public Coordinates getNode() {
      return this.node;
    }

    public int getCost() {
      return this.cost;
    }

    public int getDist() {
      return this.dist;
    }

    // The manhattan distance plus the cost is the comparing factor
    @Override
    public int compareTo(Tuple tuple) {
      return Integer.compare(getCost() + getDist(), tuple.getCost() + tuple.getDist());
    }

    // we need equals only to check if a certain node is in the nextNode list. Therefore, we care
    // only about the coordinate and not the cost or dist
    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null) {
        return false;
      }
      if (!(o instanceof Tuple tuple)) {
        return false;
      }
      return tuple.getNode().equals(getNode());
    }

    @Override
    public int hashCode() {
      return Objects.hash(node, cost);
    }
  }

}
