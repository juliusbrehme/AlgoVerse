package com.algoverse.api.pathfinding.strategy;

import com.algoverse.api.pathfinding.board.BoardInformation;

public interface Strategy {

  Path findPath(BoardInformation board);

}
