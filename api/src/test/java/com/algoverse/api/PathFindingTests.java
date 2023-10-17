package com.algoverse.api;

import static com.google.common.truth.Truth.assertThat;
import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.algoverse.api.pathfinding.PathFindingFactory;
import com.algoverse.api.pathfinding.board.BoardInformation;
import com.algoverse.api.pathfinding.board.Coordinates;
import com.algoverse.api.pathfinding.strategy.Path;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/**
 * Test class for testing the path finding algorithms.
 */
public class PathFindingTests {

  private static final int NUMBER_OF_TESTS = 10;
  private static final Random RAND = new Random();
  private PathFindingFactory factory;

  /*
   * Skip test if a strategy does not support to the shortest path.
   * @param strategy The strategy that is being used.

  private void requireShortestPath(PathFindingFactory.Strategies strategy) {
    assume()
        .withMessage("Path finding algorithm does not support shortest Path.")
        .that(strategy)
        .isNoneOf(PathFindingFactory.Strategies.DFS, PathFindingFactory.Strategies.BFS);
  }*/

  /**
   * Creation of a random test board with different setups.
   *
   * @param oneRow     Board has only one row
   * @param oneCol     Board has only one column
   * @param noSolution Board has walls around starting node, so no path can be found
   * @return Return the board information
   */
  public BoardInformation testBoard(boolean oneRow, boolean oneCol, boolean noSolution) {
    Coordinates boardSize =
        new Coordinates(RAND.nextInt(1, 100), RAND.nextInt(1, 100));
    ImmutableList<Coordinates> nodes = PathFindingFactory.randomNodeGenerator(boardSize);
    Coordinates startNode = nodes.get(0);
    Coordinates endNode = nodes.get(1);
    HashMap<Coordinates, Integer> noWall = new HashMap<>();
    if (!oneRow && !oneCol && !noSolution) {
      return new BoardInformation(startNode, endNode, ImmutableMap.copyOf(noWall), boardSize);
    } else if (!oneRow && !oneCol && noSolution) {
      noWall.put(new Coordinates(startNode.x() + 1, startNode.y()), 1);
      noWall.put(new Coordinates(startNode.x() - 1, startNode.y()), 1);
      noWall.put(new Coordinates(startNode.x(), startNode.y() + 1), 1);
      noWall.put(new Coordinates(startNode.x(), startNode.y() - 1), 1);
      return new BoardInformation(startNode, endNode, ImmutableMap.copyOf(noWall), boardSize);
    } else if (oneRow && !oneCol && !noSolution) {
      boardSize = new Coordinates(RAND.nextInt(2, 100), 1);
      nodes = PathFindingFactory.randomNodeGenerator(boardSize);
      startNode = nodes.get(0);
      endNode = nodes.get(1);
      return new BoardInformation(startNode, endNode, ImmutableMap.copyOf(noWall), boardSize);
    } else if (!oneRow && oneCol && !noSolution) {
      boardSize = new Coordinates(1, RAND.nextInt(2, 100));
      nodes = PathFindingFactory.randomNodeGenerator(boardSize);
      startNode = nodes.get(0);
      endNode = nodes.get(1);
      return new BoardInformation(startNode, endNode, ImmutableMap.copyOf(noWall), boardSize);
    } else {
      throw new IllegalArgumentException("Not able to generate board with this setup.");
    }
  }

  /**
   * A random board is set up, that there is only one path from start to end node.
   *
   * @return Returns the board information
   */
  public BoardInformation oneSolution() {
    Coordinates boardSize =
        new Coordinates(RAND.nextInt(4, 100), RAND.nextInt(4, 100));
    ImmutableList<Coordinates> nodes = PathFindingFactory.randomNodeGenerator((boardSize));
    Coordinates startingNode = nodes.get(0);
    Coordinates endingNode = nodes.get(1);
    HashMap<Coordinates, Integer> walls = new HashMap<>();

    int[][] wallSetUp = new int[boardSize.y()][boardSize.x()];
    int newPoint;
    if (startingNode.x() < endingNode.x()) {
      for (int i = startingNode.x(); i <= endingNode.x(); i++) {
        wallSetUp[startingNode.y()][i] = 1;
      }
      newPoint = endingNode.x();
    } else {
      for (int i = startingNode.x(); i >= endingNode.x(); i--) {
        wallSetUp[startingNode.y()][i] = 1;
      }
      newPoint = endingNode.x();
    }
    if (startingNode.y() <= endingNode.y()) {
      for (int i = startingNode.y() + 1; i <= endingNode.y(); i++) {
        wallSetUp[i][newPoint] = 1;
      }
    } else {
      for (int i = startingNode.y() - 1; i >= endingNode.y(); i--) {
        wallSetUp[i][newPoint] = 1;
      }
    }

    for (int i = 0; i < wallSetUp.length; i++) {
      for (int j = 0; j < wallSetUp[0].length; j++) {
        if (wallSetUp[i][j] == 0) {
          walls.put(new Coordinates(j, i), 1);
        }
      }
    }

    return new BoardInformation(startingNode, endingNode, ImmutableMap.copyOf(walls), boardSize);
  }

  /**
   * This test tests if the starting and end node are always the first and last element in the
   * calculated path.
   *
   * @param strategy The strategy that is used for the path finding algorithm
   */
  @ParameterizedTest(name = "{0}")
  @EnumSource(PathFindingFactory.Strategies.class)
  public void findPathWithOutWallsTest(PathFindingFactory.Strategies strategy) {
    factory = PathFindingFactory.createPathFindingStrategy(strategy);
    // rerun test
    for (int i = 0; i < NUMBER_OF_TESTS; i++) {
      BoardInformation boardInformation = testBoard(false, false, false);
      Path pathInformation = factory.findPath(boardInformation);

      List<Coordinates> path = pathInformation.path().asList();
      assertThat(boardInformation.startingNode()).isEqualTo(path.get(0));
      assertThat(boardInformation.endingNode()).isEqualTo(Iterables.getLast(path));
    }
  }

  /**
   * There should never be a path to the end node. We build a wall around the starting node, so
   * that it is not possible to find a path.
   *
   * @param strategy The strategy that is used for the path finding algorithm
   */
  @ParameterizedTest(name = "{0}")
  @EnumSource(PathFindingFactory.Strategies.class)
  public void noPathTest(PathFindingFactory.Strategies strategy) {
    factory = PathFindingFactory.createPathFindingStrategy(strategy);
    // rerun test
    for (int i = 0; i < NUMBER_OF_TESTS; i++) {
      BoardInformation boardInformation = testBoard(false, false, true);
      Path pathInformation = factory.findPath(boardInformation);

      assertThat(0).isEqualTo(pathInformation.path().size());
      assertThat(pathInformation.visitedNodes()).containsNoneIn(boardInformation.wall().keySet());
    }
  }

  /**
   * Test that the returned path is correct. The path is a straight line along the x-axis.
   *
   * @param strategy The strategy that is used for the path finding algorithm
   */
  @ParameterizedTest(name = "{0}")
  @EnumSource(PathFindingFactory.Strategies.class)
  public void straightPathAlongRowTest(PathFindingFactory.Strategies strategy) {
    factory = PathFindingFactory.createPathFindingStrategy(strategy);
    // rerun test
    for (int i = 0; i < NUMBER_OF_TESTS; i++) {
      BoardInformation boardInformation = testBoard(true, false, false);
      Path pathInformation = factory.findPath(boardInformation);

      List<Coordinates> expected = new ArrayList<>();

      int index = 0;
      if (boardInformation.startingNode().x() < boardInformation.endingNode().x()) {
        expected.add(boardInformation.startingNode());
        while (!expected.get(index).equals(boardInformation.endingNode())) {
          Coordinates prevNode = expected.get(index);
          expected.add(new Coordinates(prevNode.x() + 1, 0));
          index++;
        }
      } else {
        expected.add(boardInformation.startingNode());
        while (!expected.get(index).equals(boardInformation.endingNode())) {
          Coordinates prevNode = expected.get(index);
          expected.add(new Coordinates(prevNode.x() - 1, 0));
          index++;
        }
      }

      assertThat(expected).isEqualTo(pathInformation.path());
    }
  }

  /**
   * Test that the returned path is correct. The path is a straight line along the y-axis.
   *
   * @param strategy The strategy that is used for the path finding algorithm
   */
  @ParameterizedTest(name = "{0}")
  @EnumSource(PathFindingFactory.Strategies.class)
  public void straightPathAlongColTest(PathFindingFactory.Strategies strategy) {
    factory = PathFindingFactory.createPathFindingStrategy(strategy);
    // rerun test
    for (int i = 0; i < NUMBER_OF_TESTS; i++) {
      BoardInformation boardInformation = testBoard(false, true, false);
      Path pathInformation = factory.findPath(boardInformation);

      List<Coordinates> expected = new ArrayList<>();

      int index = 0;
      if (boardInformation.startingNode().y() < boardInformation.endingNode().y()) {
        expected.add(boardInformation.startingNode());
        while (!expected.get(index).equals(boardInformation.endingNode())) {
          Coordinates prevNode = expected.get(index);
          expected.add(new Coordinates(0, prevNode.y() + 1));
          index++;
        }
      } else {
        expected.add(boardInformation.startingNode());
        while (!expected.get(index).equals(boardInformation.endingNode())) {
          Coordinates prevNode = expected.get(index);
          expected.add(new Coordinates(0, prevNode.y() - 1));
          index++;
        }
      }
      assertThat(expected).isEqualTo(pathInformation.path());
    }
  }

  /**
   * The test gets a board with walls, where the walls are set up, that there is only one possible
   * path.
   *
   * @param strategy The strategy that is used for the path finding algorithm
   */
  @ParameterizedTest(name = "{0}")
  @EnumSource(PathFindingFactory.Strategies.class)
  public void onlyOneSolutionTest(PathFindingFactory.Strategies strategy) {
    factory = PathFindingFactory.createPathFindingStrategy(strategy);
    for (int i = 0; i < NUMBER_OF_TESTS; i++) {
      BoardInformation board = oneSolution();
      Coordinates startingNode = board.startingNode();
      Coordinates endingNode = board.endingNode();
      Path pathInformation = factory.findPath(board);

      List<Coordinates> expected = new ArrayList<>();
      if (startingNode.x() < endingNode.x()) {
        for (int j = startingNode.x(); j <= endingNode.x(); j++) {
          expected.add(new Coordinates(j, startingNode.y()));
        }
      } else {
        for (int j = startingNode.x(); j >= endingNode.x(); j--) {
          expected.add(new Coordinates(j, startingNode.y()));
        }
      }

      if (startingNode.y() < endingNode.y()) {
        for (int j = startingNode.y() + 1; j <= endingNode.y(); j++) {
          expected.add(new Coordinates(endingNode.x(), j));
        }
      } else {
        for (int j = startingNode.y() - 1; j >= endingNode.y(); j--) {
          expected.add(new Coordinates(endingNode.x(), j));
        }
      }

      assertThat(expected).isEqualTo(pathInformation.path());
      assertThat(expected).isEqualTo(pathInformation.visitedNodes());
      assertThat(pathInformation.visitedNodes()).containsNoneIn(board.wall().keySet());

    }
  }

  /**
   * Test for shortest path without walls, ignore algorithms, that do not necessarily find the
   * shortest path.
   *
   * @param strategy The strategy that is used for the path finding algorithm
   */
  @ParameterizedTest(name = "{0}")
  @EnumSource(PathFindingFactory.Strategies.class)
  public void shortestPathNoWallsTest(PathFindingFactory.Strategies strategy) {
    //requireShortestPath(strategy);
    factory = PathFindingFactory.createPathFindingStrategy(strategy);
    for (int i = 0; i < NUMBER_OF_TESTS; i++) {
      BoardInformation boardInformation = testBoard(false, false, false);
      Coordinates startingNode = boardInformation.startingNode();
      Coordinates endingNode = boardInformation.endingNode();

      Path pathInformation = factory.findPath(boardInformation);

      // Manhattan distance is the shortest path
      int shortestPath =
          abs(startingNode.x() - endingNode.x()) + abs(startingNode.y() - endingNode.y());
      assertThat(shortestPath).isEqualTo(pathInformation.path().size() - 1);
    }
  }

  /**
   * Test for shortest path with walls, ignore algorithms, that do not necessarily find the
   * shortest path.
   *
   * @param strategy The strategy that is used for the path finding algorithm
   */
  @ParameterizedTest(name = "{0}")
  @EnumSource(PathFindingFactory.Strategies.class)
  public void shortestPathWithWallsTest(PathFindingFactory.Strategies strategy) {
    //requireShortestPath(strategy);
    factory = PathFindingFactory.createPathFindingStrategy(strategy);
    Coordinates boardSize = new Coordinates(10, 10);
    Coordinates startingNode = new Coordinates(1, 1);
    Coordinates endingNode = new Coordinates(9, 9);
    HashMap<Coordinates, Integer> walls = new HashMap<>();
    walls.put(new Coordinates(2, 1), 1);
    walls.put(new Coordinates(2, 2), 1);
    walls.put(new Coordinates(1, 2), 1);

    Path pathInformation = factory.findPath(
        new BoardInformation(startingNode, endingNode, ImmutableMap.copyOf(walls), boardSize));

    assertThat(18).isEqualTo(pathInformation.path().size() - 1);
    assertThat(pathInformation.visitedNodes()).containsNoneIn(walls.keySet());
  }

  /**
   * Test for shortest path with walls, ignore algorithms, that do not necessarily find the
   * shortest path.
   *
   * @param strategy The strategy that is used for the path finding algorithm
   */
  @ParameterizedTest(name = "{0}")
  @EnumSource(PathFindingFactory.Strategies.class)
  public void shortestPathWithWalls2Test(PathFindingFactory.Strategies strategy) {
    //requireShortestPath(strategy);
    factory = PathFindingFactory.createPathFindingStrategy(strategy);
    Coordinates boardSize = new Coordinates(10, 10);
    Coordinates startingNode = new Coordinates(2, 2);
    Coordinates endingNode = new Coordinates(9, 9);
    HashMap<Coordinates, Integer> walls = new HashMap<>();
    walls.put(new Coordinates(2, 1), 1);
    walls.put(new Coordinates(3, 1), 1);
    walls.put(new Coordinates(3, 2), 1);
    walls.put(new Coordinates(3, 3), 1);
    walls.put(new Coordinates(2, 3), 1);
    walls.put(new Coordinates(1, 3), 1);
    walls.put(new Coordinates(0, 3), 1);

    Path pathInformation = factory.findPath(
        new BoardInformation(startingNode, endingNode, ImmutableMap.copyOf(walls), boardSize));

    assertThat(20).isEqualTo(pathInformation.path().size() - 1);
    assertThat(pathInformation.visitedNodes()).containsNoneIn(walls.keySet());
  }

  /**
   * Test to find path with walls.
   *
   * @param strategy The strategy that is used for the path finding algorithm
   */
  @ParameterizedTest(name = "{0}")
  @EnumSource(PathFindingFactory.Strategies.class)
  public void findPathWithWallsTest(PathFindingFactory.Strategies strategy) {
    factory = PathFindingFactory.createPathFindingStrategy(strategy);
    Coordinates boardSize = new Coordinates(10, 10);
    Coordinates startingNode = new Coordinates(1, 1);
    Coordinates endingNode = new Coordinates(9, 9);
    HashMap<Coordinates, Integer> walls = new HashMap<>();
    walls.put(new Coordinates(2, 1), 1);
    walls.put(new Coordinates(2, 2), 1);
    walls.put(new Coordinates(1, 2), 1);

    Path pathInformation = factory.findPath(
        new BoardInformation(startingNode, endingNode, ImmutableMap.copyOf(walls), boardSize));

    assertThat(startingNode).isEqualTo(pathInformation.path().get(0));
    assertThat(endingNode).isEqualTo(Iterables.getLast(pathInformation.path()));
    assertThat(pathInformation.visitedNodes()).containsNoneIn(walls.keySet());
  }

  /**
   * Test to find path with walls.
   *
   * @param strategy The strategy that is used for the path finding algorithm
   */
  @ParameterizedTest(name = "{0}")
  @EnumSource(PathFindingFactory.Strategies.class)
  public void findPathWithWalls2Test(PathFindingFactory.Strategies strategy) {
    factory = PathFindingFactory.createPathFindingStrategy(strategy);
    Coordinates boardSize = new Coordinates(10, 10);
    Coordinates startingNode = new Coordinates(2, 2);
    Coordinates endingNode = new Coordinates(9, 9);
    HashMap<Coordinates, Integer> walls = new HashMap<>();
    walls.put(new Coordinates(2, 1), 1);
    walls.put(new Coordinates(3, 1), 1);
    walls.put(new Coordinates(3, 2), 1);
    walls.put(new Coordinates(3, 3), 1);
    walls.put(new Coordinates(2, 3), 1);
    walls.put(new Coordinates(1, 3), 1);
    walls.put(new Coordinates(0, 3), 1);

    Path pathInformation = factory.findPath(
        new BoardInformation(startingNode, endingNode, ImmutableMap.copyOf(walls), boardSize));

    assertThat(startingNode).isEqualTo(pathInformation.path().get(0));
    assertThat(endingNode).isEqualTo(Iterables.getLast(pathInformation.path()));
    assertThat(pathInformation.visitedNodes()).containsNoneIn(walls.keySet());
  }

}
