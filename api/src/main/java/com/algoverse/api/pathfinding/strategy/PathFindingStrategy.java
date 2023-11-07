package com.algoverse.api.pathfinding.strategy;

import com.algoverse.api.pathfinding.board.BoardInformation;
import com.algoverse.api.pathfinding.board.Coordinates;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The interface for the Strategy. Every new implemented path finding strategy
 * should implement this interface.
 */
public interface PathFindingStrategy {

  /**
   * This method is generating the path of the path finding algorithm.
   *
   * @param board Holds the information of the board.
   * @return Returns a record of Path, which holds the path and the visited nodes
   */
  Path findPath(BoardInformation board);

  /**
   * This method is to generate the next neighbors for the path finding algorithm.
   *
   * @param node         The node to generate the neighbors from
   * @param wall         All obstacles, those nodes can not be visited or used
   * @param visitedNodes All already visited nodes
   * @param boardSize    The size of the board
   * @return Returns a list of all neighbors, that still need to be visited
   */
  default List<Coordinates> getNeighbors(Coordinates node, ImmutableMap<Coordinates, Integer> wall,
                                         HashMap<Coordinates, Integer> visitedNodes,
                                         Coordinates boardSize) {
    // if we allow weighted nodes, implement getNeighbors with a priorityQueue (heap),
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

  /**
   * Method to reconstruct the Path from memory.
   *
   * @param startingNode The starting node
   * @param endingNode   The ending node
   * @param visitedNodes The visited nodes in sequence
   * @param parent       The memory of which node is the parent node
   * @return Return the path and the sequence of visited nodes
   */
  default Path reconstructPath(Coordinates startingNode, Coordinates endingNode,
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
