package com.algoverse.api.pathfinding;

import com.algoverse.api.exception.ExceptionHandling;
import com.algoverse.api.pathfinding.board.BoardInformation;
import com.algoverse.api.pathfinding.board.Coordinates;
import com.algoverse.api.pathfinding.strategy.Path;
import com.algoverse.api.pathfinding.strategy.PathFindingStrategies;
import com.google.common.collect.ImmutableMap;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Arrays;
import java.util.HashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is the controller for the path finding.
 */
@SuppressWarnings("unused")
@SuppressFBWarnings("EI_EXPOSE_REP2")
@RestController
public class PathFindingController {

  private final PathFindingService pathFindingService;

  /**
   * Constructor to be able to use PathFindingService.
   *
   * @param pathFindingService The PathFindingService
   */
  public PathFindingController(PathFindingService pathFindingService) {
    this.pathFindingService = pathFindingService;
  }

  // TODO: implement a random nodes generator, s. PathFindingService

  /**
   * This method generates random starting and end node.
   *
   * @param boardSize The size of the board
   * @return Return starting and end node
   */
  @GetMapping("/pathfinding/random/nodes")
  public Coordinates[] createRandomNodes(@RequestParam(value = "size") int[] boardSize) {
    if (boardSize.length != 2) {
      throw new ExceptionHandling.WrongCoordinateInputException();
    }
    return pathFindingService.randomNodeGenerator(new Coordinates(boardSize[0], boardSize[1]));
  }

  // TODO: implement a random board generator, s. PathFindingService

  /**
   * This method generates a random board with the size being set.
   *
   * @param boardSize The size of the board
   * @return The board information with starting, ending node and obstacles
   */
  @GetMapping("pathfinding/random/board")
  public BoardInformation createRandomBoard(@RequestParam(value = "size") int[] boardSize) {
    if (boardSize.length != 2) {
      throw new ExceptionHandling.WrongCoordinateInputException();
    }
    return pathFindingService.randomBoardGenerator(new Coordinates(boardSize[0], boardSize[1]));
  }

  /**
   * This method finds the paths from the starting point to ending point.
   *
   * @param startingNode The start node
   * @param endingNode   The end node
   * @param wall         The set obstacles, can be empty as well
   * @param boardSize    The size of the board
   * @param strategy     The strategy to be used
   * @return Returns a record Path that holds the path and the visited nodes
   */
  @GetMapping("pathfinding/findpath")
  public Path findPath(@RequestParam(value = "startpoint") int[] startingNode,
                       @RequestParam(value = "endpoint") int[] endingNode,
                       @RequestParam(value = "wall", required = false, defaultValue = " ")
                       int[][] wall,
                       @RequestParam(value = "size") int[] boardSize,
                       @RequestParam(value = "strategy") PathFindingStrategies strategy) {
    if (startingNode.length != 2 || endingNode.length != 2 || boardSize.length != 2) {
      throw new ExceptionHandling.WrongCoordinateInputException();
    }
    if (startingNode[0] < 0 || startingNode[1] < 0 || startingNode[0] >= boardSize[0]
        || startingNode[1] >= boardSize[1]
        || endingNode[0] < 0 || endingNode[1] < 0 || endingNode[0] >= boardSize[0]
        || endingNode[1] >= boardSize[1]) {
      throw new ExceptionHandling.WrongInputOfBoardSizeAndPoints();
    }
    HashMap<Coordinates, Integer> obstacle =
        getCoordinatesIntegerHashMap(wall, boardSize);
    return pathFindingService.findPath(
        new BoardInformation(new Coordinates(startingNode[0], startingNode[1]),
            new Coordinates(endingNode[0], endingNode[1]),
            ImmutableMap.copyOf(obstacle),
            new Coordinates(boardSize[0], boardSize[1])),
        strategy);
  }

  private HashMap<Coordinates, Integer> getCoordinatesIntegerHashMap(int[][] wall,
                                                                     int[] boardSize) {
    HashMap<Coordinates, Integer> obstacle = new HashMap<>();
    // check if only one obstacle is given or if it is empty request
    if (wall.length == 2 && wall[0].length == 1 && wall[1].length == 1) {
      obstacle.put(new Coordinates(wall[0][0], wall[1][0]), 1);
    } else if (!(wall.length == 1 && wall[0].length == 0)) {
      for (int[] coordinates : wall) {
        if (coordinates.length != 2) {
          throw new ExceptionHandling.WrongWallInputException();
        }
        if (coordinates[0] < 0 || coordinates[1] < 0 || coordinates[0] >= boardSize[0]
            || coordinates[1] >= boardSize[1]) {
          throw new ExceptionHandling.WrongInputOfBoardSizeAndWalls();
        }
        obstacle.put(new Coordinates(coordinates[0], coordinates[1]), 1);
      }
    }
    return obstacle;
  }
}
