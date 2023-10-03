package com.algoverse.api.pathfinding;

import com.algoverse.api.exception.ExceptionHandling;
import com.algoverse.api.pathfinding.board.BoardInformation;
import com.algoverse.api.pathfinding.board.Coordinates;
import com.algoverse.api.pathfinding.strategy.Path;
import com.algoverse.api.pathfinding.strategy.Strategies;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathFindingController {

  private final PathFindingService pathFindingService;

  @Autowired
  public PathFindingController(PathFindingService pathFindingService) {
    this.pathFindingService = pathFindingService;
  }

  // TODO: implement a random nodes generator, s. PathFindingService
  @GetMapping("/pathfinding/random/nodes")
  public Coordinates[] createRandomNodes(@RequestParam(value = "size") int[] boardSize) {
    if (boardSize.length != 2) {
      throw new ExceptionHandling.WrongCoordinateInputException();
    }
    return pathFindingService.randomNodeGenerator(new Coordinates(boardSize[0], boardSize[1]));
  }

  // TODO: implement a random board generator, s. PathFindingService
  @GetMapping("pathfinding/random/board")
  public BoardInformation createRandomBoard(@RequestParam(value = "size") int[] boardSize) {
    if (boardSize.length != 2) {
      throw new ExceptionHandling.WrongCoordinateInputException();
    }
    return pathFindingService.randomBoardGenerator(new Coordinates(boardSize[0], boardSize[1]));
  }

  @GetMapping("pathfinding/findpath")
  public Path findPath(@RequestParam(value = "startpoint") int[] startingNode,
                       @RequestParam(value = "endpoint") int[] endingNode,
                       @RequestParam(value = "wall", required = false, defaultValue = " ") int[][] wall,
                       @RequestParam(value = "size") int[] boardSize,
                       @RequestParam(value = "strategy") Strategies strategy) {
    if (startingNode.length != 2 || endingNode.length != 2 || boardSize.length != 2) {
      throw new ExceptionHandling.WrongCoordinateInputException();
    }
    if (startingNode[0] < 0 || startingNode[1] < 0 || startingNode[0] >= boardSize[0] || startingNode[1] >= boardSize[1]
      || endingNode[0] < 0 || endingNode[1] < 0 || endingNode[0] >= boardSize[0] || endingNode[1] >= boardSize[1]) {
      throw new ExceptionHandling.WrongInputOfBoardSizeAndPoints();
    }
    HashMap<Coordinates, Integer> obstacle = new HashMap<>();
    //Coordinates[] obstacles = new Coordinates[wall.length];
    if (!(wall.length == 1 && wall[0].length == 0)) {
      for (int i = 0; i < wall.length; i++) {
        if (wall[i].length != 2) {
          throw new ExceptionHandling.WrongWallInputException();
        }
        //obstacles[i] = new Coordinates(wall[i][0], wall[i][1]);
        obstacle.put(new Coordinates(wall[i][0], wall[i][1]), 1);
      }
    }
    return pathFindingService.findPath(
        new BoardInformation(new Coordinates(startingNode[0], startingNode[1]),
            new Coordinates(endingNode[0], endingNode[1]),
            obstacle,
            new Coordinates(boardSize[0], boardSize[1])),
            strategy);
  }
}