package com.algoverse.api.pathfinding.strategy;

import com.algoverse.api.pathfinding.board.BoardInformation;
import com.algoverse.api.pathfinding.board.Coordinates;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Dijkstra implements Strategy{

  public Dijkstra() {
  }

  @Override
  public Path findPath(BoardInformation board) {

    Coordinates startingNode = board.startingNode();
    System.out.println(Integer.toString(startingNode.x()) + Integer.toString(startingNode.y()));
    Coordinates endingNode = board.endingNode();
    System.out.println(Integer.toString(endingNode.x()) + Integer.toString(endingNode.y()));
    HashMap<Coordinates, Integer> obstacle = board.wall();
    Coordinates boardSize = board.boardSize();
    System.out.println(Integer.toString(boardSize.x()) + Integer.toString(boardSize.y()));

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
    for (int i = 0; i < adjacentMatrix.length; i++) {
      System.out.print(Arrays.toString(adjacentMatrix[i]));
    }
    System.out.println();

    List<Coordinates> nextNodes = new ArrayList<>();
    nextNodes.add(startingNode);

    // pathfinding algorithm, we will look at every node and don't stop early
    while (!nextNodes.isEmpty()) {
      Coordinates node = nextNodes.get(0);
      nextNodes.remove(0);
      System.out.println("Gerade die Node: " + node);
      System.out.println("Zu visiteden Node: " + nextNodes);
      visitedNodes.add(node);
      vistedNode.put(node, 1);
      System.out.println("Schon besuchte Nodes: " + vistedNode);
      List<Coordinates> neighbors = getNeighbors(node, obstacle, vistedNode, boardSize);
      System.out.println("Zunäcsht betrachteten Nachbarn: " + neighbors);
      for (Coordinates neighbor: neighbors) {
        if (adjacentMatrix[neighbor.x()][neighbor.y()] > adjacentMatrix[node.x()][node.y()] + 1 ||
            adjacentMatrix[neighbor.x()][neighbor.y()] == -1) {
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
      for (int i = 0; i < adjacentMatrix.length; i++) {
        System.out.print(Arrays.toString(adjacentMatrix[i]));
      }
      System.out.println();
    }

    System.out.println(prevNodes);

    // determining the actual path, not just the cost
    Coordinates node = endingNode;
    List<Coordinates> path = new ArrayList<>();
    // check if a solution exist, because if there is no entry for the endingNode, there is no solution
    if (prevNodes.get(node) == null) {
      return new Path(path, visitedNodes);
    }

    while (!node.equals(startingNode)) {
      path.add(0, node);
      node = prevNodes.get(node);
    }
    path.add(0, startingNode);

    return new Path(path, visitedNodes);
  }

  public List<Coordinates> getNeighbors(Coordinates node, HashMap<Coordinates, Integer> wall, HashMap<Coordinates, Integer> visitedNodes, Coordinates boardSize) {
    // if we allow weighted nodes, implemented getNeighbors with a priorityQueue(heap), so that we use a priority as an attribute
    // getNeighbors updates that priorityQueue. Changes need to be made to findPath as well

    List<Coordinates> neighbors = new ArrayList<>();
    if (0 <= node.x() + 1 && node.x() + 1 < boardSize.x() && 0 <= node.y() && node.y() < boardSize.y())  {
      Coordinates neighbor = new Coordinates(node.x() + 1, node.y());
      if (wall.get(neighbor) == null && visitedNodes.get(neighbor) == null) {
        neighbors.add(neighbor);
      }
    }
    if (0 <= node.x() && node.x() < boardSize.x() && 0 <= node.y() + 1 && node.y() + 1 < boardSize.y()) {
      Coordinates neighbor = new Coordinates(node.x(), node.y() + 1);
      if (wall.get(neighbor) == null && visitedNodes.get(neighbor) == null) {
        neighbors.add(neighbor);
      }
    }
    if (0 <= node.x() - 1 && node.x() - 1 < boardSize.x() && 0 <= node.y() && node.y() < boardSize.y()) {
      Coordinates neighbor = new Coordinates(node.x() - 1, node.y());
      if (wall.get(neighbor) == null && visitedNodes.get(neighbor) == null) {
        neighbors.add(neighbor);
      }
    }
    if (0 <= node.x() && node.x() < boardSize.x() && 0 <= node.y() - 1 && node.y() - 1 < boardSize.y()) {
      Coordinates neighbor = new Coordinates(node.x(), node.y() - 1);
      if (wall.get(neighbor) == null && visitedNodes.get(neighbor) == null) {
        neighbors.add(neighbor);
      }
    }
    return neighbors;
  }
}
