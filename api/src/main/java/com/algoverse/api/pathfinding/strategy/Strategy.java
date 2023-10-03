package com.algoverse.api.pathfinding.strategy;

import com.algoverse.api.pathfinding.board.BoardInformation;

/**
 * The interface for the Strategy. Every new implemented path finding strategy
 * should implement this interface.
 */
public interface Strategy {

  /**
   * This method is generating the path of the path finding algorithm.
   *
   * @param board Holds the information of the board.
   * @return Returns a record of Path, which holds the path and the visited nodes
   */
  Path findPath(BoardInformation board);

}
