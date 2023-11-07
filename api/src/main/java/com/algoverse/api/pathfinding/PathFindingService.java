package com.algoverse.api.pathfinding;

import com.algoverse.api.pathfinding.board.BoardInformation;
import com.algoverse.api.pathfinding.board.Coordinates;
import com.algoverse.api.pathfinding.strategy.Path;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

/**
 * The Service to controll the path finding.
 */
@Service
public class PathFindingService {

  /**
   * This method delegates the path finding to the selected strategy.
   *
   * @param board                 The whole board information with starting/end node, obstacles etc.
   * @param pathFindingStrategies The enum value of the strategy that should be used for path
   *                              finding
   * @return Returns the record path with the path and the visited nodes
   */
  public Path findPath(BoardInformation board,
                       PathFindingFactory.Strategies pathFindingStrategies) {
    PathFindingFactory pathFindingFactory =
        PathFindingFactory.createPathFindingStrategy(pathFindingStrategies);
    return pathFindingFactory.findPath(board);
  }

  /**
   * Method to create random nodes.
   *
   * @param boardSize The size of the board
   * @return Returns an immutable list of two coordinates, starting and end node
   */
  public ImmutableList<Coordinates> randomNodeGenerator(Coordinates boardSize) {
    return PathFindingFactory.randomNodeGenerator(boardSize);
  }

  // TODO: implement a random maze generator
  @SuppressWarnings("unused")
  public BoardInformation randomMazeGenerator(Coordinates boardSize) {
    return null;
  }

}
