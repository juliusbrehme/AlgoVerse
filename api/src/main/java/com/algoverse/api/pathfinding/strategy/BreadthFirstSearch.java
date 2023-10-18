package com.algoverse.api.pathfinding.strategy;

import com.algoverse.api.pathfinding.board.BoardInformation;
import com.algoverse.api.pathfinding.board.Coordinates;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Implementation of BFS algorithm. For our example it will find the shortest path
 * because the weight of the nodes are the same.
 */
public class BreadthFirstSearch implements PathFindingStrategy {

  private BreadthFirstSearch() {

  }

  /**
   * Static factory method.
   *
   * @return Returns object of BFS
   */

  public static BreadthFirstSearch createBreadthFirstSearch() {
    return new BreadthFirstSearch();
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

    List<Coordinates> nextNode = new ArrayList<>();
    nextNode.add(startingNode);
    List<Coordinates> visitedNodes = new ArrayList<>();
    HashMap<Coordinates, Integer> visitedNodesMap = new HashMap<>();
    HashMap<Coordinates, Coordinates> parent = new HashMap<>();

    while (!nextNode.isEmpty()) {
      Coordinates node = nextNode.remove(0);
      visitedNodes.add(node);
      visitedNodesMap.put(node, 1);
      List<Coordinates> neighbors = getNeighbors(node, obstacle, visitedNodesMap, boardSize);
      for (Coordinates neighbor : neighbors) {
        parent.put(neighbor, node);
        // neighbor is not in visited, because of getNeighbor function
        if (!nextNode.contains(neighbor)) {
          if (endingNode.equals(neighbor)) {
            visitedNodes.add(neighbor);
            return reconstructPath(startingNode, endingNode, visitedNodes, parent);
          }
          nextNode.add(neighbor);
        }
      }
    }

    return new Path(ImmutableList.of(), ImmutableList.copyOf(visitedNodes));
  }

  /**
   * Method to reconstruct the Path from memory.
   *
   * @param startingNode The starting node
   * @param endingNode   The ending node
   * @param visitedNodes The visited nodes in sequence
   * @param parent       The memory of which node is the parent node
   * @return Return the path and the sequence of visited nodes
   */
  private Path reconstructPath(Coordinates startingNode, Coordinates endingNode,
                               List<Coordinates> visitedNodes,
                               HashMap<Coordinates, Coordinates> parent) {
    Coordinates node = endingNode;
    List<Coordinates> path = new ArrayList<>();
    // check if a solution exist
    if (parent.get(node) == null) {
      return new Path(ImmutableList.copyOf(path), ImmutableList.copyOf(visitedNodes));
    }

    while (!node.equals(startingNode)) {
      path.add(0, node);
      node = parent.get(node);
    }
    path.add(0, startingNode);

    return new Path(ImmutableList.copyOf(path), ImmutableList.copyOf(visitedNodes));
  }

}
