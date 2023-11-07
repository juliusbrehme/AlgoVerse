package com.algoverse.api.pathfinding.strategy;

import com.algoverse.api.pathfinding.board.BoardInformation;
import com.algoverse.api.pathfinding.board.Coordinates;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Dijkstra path finding strategy.
 */
public class Dijkstra implements PathFindingStrategy {

  private Dijkstra() {
  }


  /**
   * Static Factory Method.
   *
   * @return Return a Dijkstra Object
   */
  public static Dijkstra createDijkstra() {
    return new Dijkstra();
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
    HashMap<Coordinates, Integer> vistedNodeMap = new HashMap<>();

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
      Coordinates node = nextNodes.remove(0);
      visitedNodes.add(node);
      vistedNodeMap.put(node, 1);
      List<Coordinates> neighbors = getNeighbors(node, obstacle, vistedNodeMap, boardSize);
      for (Coordinates neighbor : neighbors) {
        if (adjacentMatrix[neighbor.x()][neighbor.y()] > adjacentMatrix[node.x()][node.y()] + 1
            || adjacentMatrix[neighbor.x()][neighbor.y()] == -1) {
          adjacentMatrix[neighbor.x()][neighbor.y()] = adjacentMatrix[node.x()][node.y()] + 1;
          prevNodes.put(neighbor, node);
        }
        // already visited and we do not need to visit again
        if (vistedNodeMap.get(neighbor) == null) {
          // possible already in nextNodes
          if (!nextNodes.contains(neighbor)) {
            nextNodes.add(neighbor);
          }
        }
      }
    }

    return reconstructPath(startingNode, endingNode, visitedNodes, prevNodes);
  }


}
