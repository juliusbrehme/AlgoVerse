package com.algoverse.api.pathfinding.strategy;

import com.algoverse.api.pathfinding.board.BoardInformation;
import com.algoverse.api.pathfinding.board.Coordinates;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Dijkstra path finding strategy.
 */
public class Dijkstra implements Strategy {

  public Dijkstra() {
  }

  @Override
  public Path findPath(BoardInformation board) {

    final Coordinates startingNode = board.startingNode();
    final Coordinates endingNode = board.endingNode();
    final ImmutableMap<Coordinates, Integer> obstacle = board.wall();
    final Coordinates boardSize = board.boardSize();

    HashMap<Coordinates, Coordinates> prevNodes = new HashMap<>();
    // To see in what directions the nodes were visited
    List<Coordinates> visitedNodes = new ArrayList<>();
    // To check in O(1) if a node was already visited
    HashMap<Coordinates, Integer> vistedNode = new HashMap<>();

    // create an adjacentMatrix
    int[][] adjacentMatrix = new int[boardSize.x()][boardSize.y()];
    for (int i = 0; i < boardSize.x(); i++) {
      for (int j = 0; j < boardSize.y(); j++) {
        if (startingNode.x() == i && startingNode.y() == j) {
          adjacentMatrix[i][j] = 0;
        } else {
          // for now sufficient, because we do not allow weights
          adjacentMatrix[i][j] = -1;
        }
      }
    }

    List<Coordinates> nextNodes = new ArrayList<>();
    nextNodes.add(startingNode);

    // pathfinding algorithm, we will look at every node and don't stop early
    while (!nextNodes.isEmpty()) {
      Coordinates node = nextNodes.get(0);
      nextNodes.remove(0);
      visitedNodes.add(node);
      vistedNode.put(node, 1);
      List<Coordinates> neighbors = getNeighbors(node, obstacle, vistedNode, boardSize);
      for (Coordinates neighbor : neighbors) {
        if (adjacentMatrix[neighbor.x()][neighbor.y()] > adjacentMatrix[node.x()][node.y()] + 1
            || adjacentMatrix[neighbor.x()][neighbor.y()] == -1) {
          adjacentMatrix[neighbor.x()][neighbor.y()] = adjacentMatrix[node.x()][node.y()] + 1;
          prevNodes.put(neighbor, node);
        }
        // already visited and we do not need to visit again
        if (vistedNode.get(neighbor) == null) {
          // possible alread in nextNodes
          if (!nextNodes.contains(neighbor)) {
            nextNodes.add(neighbor);
          }
        }
      }
    }

    // determining the actual path, not just the cost
    Coordinates node = endingNode;
    List<Coordinates> path = new ArrayList<>();
    // check if a solution exist
    if (prevNodes.get(node) == null) {
      return new Path(ImmutableList.copyOf(path), ImmutableList.copyOf(visitedNodes));
    }

    while (!node.equals(startingNode)) {
      path.add(0, node);
      node = prevNodes.get(node);
    }
    path.add(0, startingNode);

    return new Path(ImmutableList.copyOf(path), ImmutableList.copyOf(visitedNodes));
  }

  /**
   * This method is to generate the next neighbors for the path finding algorithm.
   *
   * @param node         The node to generate the neighbors from
   * @param wall         All obstacles, those nodes can not be visited or used
   * @param visitedNodes All already visited nodes
   * @param boardSize    The size of the board
   * @return Returns a list of all neighbors, that still need to be visited
   */
  public List<Coordinates> getNeighbors(Coordinates node, ImmutableMap<Coordinates, Integer> wall,
                                        HashMap<Coordinates, Integer> visitedNodes,
                                        Coordinates boardSize) {
    // if we allow weighted nodes, implemented getNeighbors with a priorityQueue(heap),
    // so that we use a priority as an attribute getNeighbors updates that priorityQueue.
    // Changes need to be made to findPath as well

    List<Coordinates> neighbors = new ArrayList<>();
    if (0 <= node.x() + 1 && node.x() + 1 < boardSize.x() && 0 <= node.y()
        && node.y() < boardSize.y()) {
      Coordinates neighbor = new Coordinates(node.x() + 1, node.y());
      if (wall.get(neighbor) == null && visitedNodes.get(neighbor) == null) {
        neighbors.add(neighbor);
      }
    }
    if (0 <= node.x() && node.x() < boardSize.x() && 0 <= node.y() + 1
        && node.y() + 1 < boardSize.y()) {
      Coordinates neighbor = new Coordinates(node.x(), node.y() + 1);
      if (wall.get(neighbor) == null && visitedNodes.get(neighbor) == null) {
        neighbors.add(neighbor);
      }
    }
    if (0 <= node.x() - 1 && node.x() - 1 < boardSize.x() && 0 <= node.y()
        && node.y() < boardSize.y()) {
      Coordinates neighbor = new Coordinates(node.x() - 1, node.y());
      if (wall.get(neighbor) == null && visitedNodes.get(neighbor) == null) {
        neighbors.add(neighbor);
      }
    }
    if (0 <= node.x() && node.x() < boardSize.x() && 0 <= node.y() - 1
        && node.y() - 1 < boardSize.y()) {
      Coordinates neighbor = new Coordinates(node.x(), node.y() - 1);
      if (wall.get(neighbor) == null && visitedNodes.get(neighbor) == null) {
        neighbors.add(neighbor);
      }
    }
    return neighbors;
  }
}
