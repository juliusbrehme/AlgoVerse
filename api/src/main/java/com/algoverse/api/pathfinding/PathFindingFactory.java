package com.algoverse.api.pathfinding;

import com.algoverse.api.pathfinding.board.BoardInformation;
import com.algoverse.api.pathfinding.strategy.Dijkstra;
import com.algoverse.api.pathfinding.strategy.Path;
import com.algoverse.api.pathfinding.strategy.PathFindingStrategy;

/**
 * The factory to create the different strategies and use the different strategies.
 */
public class PathFindingFactory {

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
    switch (strategy) {
      case DIJKSTRA:
        return new PathFindingFactory(Dijkstra.createDijkstra());
      default:
        throw new IllegalArgumentException("The enum does not exist and the strategy could not "
            + "be initialized.");
    }
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
    DIJKSTRA
  }
}
