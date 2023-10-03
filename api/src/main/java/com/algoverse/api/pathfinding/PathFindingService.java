package com.algoverse.api.pathfinding;

import com.algoverse.api.pathfinding.board.BoardInformation;
import com.algoverse.api.pathfinding.board.Coordinates;
import com.algoverse.api.pathfinding.strategy.Dijkstra;
import com.algoverse.api.pathfinding.strategy.Path;
import com.algoverse.api.pathfinding.strategy.Strategies;
import com.algoverse.api.pathfinding.strategy.Strategy;
import org.springframework.stereotype.Service;

@Service
public class PathFindingService {

  private Strategy strategy;

  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }

  public Path findPath(BoardInformation board, Strategies strategies) {
    if (strategies == Strategies.DIJKSTRA) {
      setStrategy(new Dijkstra());
    }
    return strategy.findPath(board);
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
