package com.algoverse.api.pathfinding;

import com.algoverse.api.pathfinding.board.BoardInformation;
import com.algoverse.api.pathfinding.board.Coordinates;
import com.algoverse.api.pathfinding.strategy.Dijkstra;
import com.algoverse.api.pathfinding.strategy.Path;
import com.algoverse.api.pathfinding.strategy.PathFindingStrategies;
import com.algoverse.api.pathfinding.strategy.PathFindingStrategy;
import org.springframework.stereotype.Service;

/**
 * The Service to controll the path finding.
 */
@Service
public class PathFindingService {

  private PathFindingStrategy pathFindingStrategy;

  /**
   * This method sets the strategy for path finding.
   *
   * @param pathFindingStrategy Strategy to use for path finding
   */
  public void setStrategy(PathFindingStrategy pathFindingStrategy) {
    this.pathFindingStrategy = pathFindingStrategy;
  }

  /**
   * This method delegates the path finding to the selected strategy.
   *
   * @param board                 The whole board information with starting/end node, obstacles etc.
   * @param pathFindingStrategies The enum value of the strategy that should be used for path
   *                              finding
   * @return Returns the record path with the path and the visited nodes
   */
  public Path findPath(BoardInformation board, PathFindingStrategies pathFindingStrategies) {
    // Switch to use different strategies
    if (pathFindingStrategies == PathFindingStrategies.DIJKSTRA) {
      setStrategy(Dijkstra.createDijkstra());
    }
    return pathFindingStrategy.findPath(board);
  }


  // TODO: implement a random nodes generator
  @SuppressWarnings("unused")
  public Coordinates[] randomNodeGenerator(Coordinates boardSize) {
    return null;
  }

  // TODO: implement a random board generator
  @SuppressWarnings("unused")
  public BoardInformation randomBoardGenerator(Coordinates boardSize) {
    return null;
  }

}
