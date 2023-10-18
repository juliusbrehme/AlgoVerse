package com.algoverse.api.pathfinding;

import com.algoverse.api.pathfinding.board.BoardInformation;
import com.algoverse.api.pathfinding.board.Coordinates;
import com.algoverse.api.pathfinding.strategy.BreadthFirstSearch;
import com.algoverse.api.pathfinding.strategy.DepthFirstSearch;
import com.algoverse.api.pathfinding.strategy.Dijkstra;
import com.algoverse.api.pathfinding.strategy.Path;
import com.algoverse.api.pathfinding.strategy.PathFindingStrategy;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The factory to create the different strategies and use the different strategies.
 */
public class PathFindingFactory {

  private static final Random RAND = new Random();
  private final PathFindingStrategy strategy;

  private PathFindingFactory(PathFindingStrategy strategy) {
    this.strategy = strategy;
  }

  /**
   * Static method to create the factory.
   *
   * @param strategy The strategy that is supposed to be used
   * @return Returns the PathFindingFactory
   */
  public static PathFindingFactory createPathFindingStrategy(
      PathFindingFactory.Strategies strategy) {
    return switch (strategy) {
      case DIJKSTRA -> new PathFindingFactory(Dijkstra.createDijkstra());
      case DFS -> new PathFindingFactory(DepthFirstSearch.createDepthFirstSearch());
      case BFS -> new PathFindingFactory(BreadthFirstSearch.createBreadthFirstSearch());
      default ->
          throw new IllegalArgumentException("The enum does not exist and the strategy could not "
              + "be initialized.");
    };
  }

  /**
   * Method to create random nodes.
   *
   * @param boardSize The size of the board
   * @return Returns an immutable list of two coordinates, starting and end node
   */
  public static ImmutableList<Coordinates> randomNodeGenerator(Coordinates boardSize) {
    int randX1 = RAND.nextInt(0, boardSize.x());
    int randY1 = RAND.nextInt(0, boardSize.y());
    Coordinates startNode = new Coordinates(randX1, randY1);

    int randX2 = RAND.nextInt(0, boardSize.x());
    int randY2 = RAND.nextInt(0, boardSize.y());
    Coordinates endNode = new Coordinates(randX2, randY2);

    while (startNode.equals(endNode)) {
      endNode = new Coordinates(RAND.nextInt(boardSize.x()), RAND.nextInt(boardSize.y()));
    }

    List<Coordinates> coordinatesList = new ArrayList<>(Arrays.asList(startNode, endNode));
    return ImmutableList.copyOf(coordinatesList);
  }

  /**
   * Method to find the path. Delegates to the used strategy.
   *
   * @param board The information of the board
   * @return Return the Path with the path and the visited nodes
   */
  public Path findPath(BoardInformation board) {
    return strategy.findPath(board);
  }


  /**
   * The enum for all the path finding strategies.
   */
  public enum Strategies {
    DIJKSTRA, DFS, BFS
  }
}
