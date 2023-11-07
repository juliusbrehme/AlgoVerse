package com.algoverse.api;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.algoverse.api.pathfinding.PathFindingController;
import com.algoverse.api.pathfinding.PathFindingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Class to test the api get request.
 */
@SuppressWarnings("unused")
@WebMvcTest(controllers = PathFindingController.class)
public class PathFindingControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PathFindingService pathFindingService;

  @Autowired
  private PathFindingController pathFindingController;

  /**
   * Test the call to find path.
   *
   * @throws Exception Exception
   */
  @Test
  public void findPathCallTest() throws Exception {
    mockMvc.perform(get("/pathfinding/findpath")
            .param("startpoint", "0", "0")
            .param("endpoint", "9", "9")
            .param("size", "10", "10")
            .param("strategy", "dijkstra"))
        .andExpect(status().isOk());
  }

  /**
   * Test the call to find path with wrong input. Exception should be thrown.
   *
   * @throws Exception Exception
   */
  @Test
  public void findPathWrongWrongStartingPointTest() throws Exception {
    mockMvc.perform(get("/pathfinding/findpath")
            .param("startpoint", "0", "0", "0")
            .param("endpoint", "9", "9")
            .param("size", "10", "10")
            .param("strategy", "dijkstra"))
        .andExpect(status().isBadRequest())
        .andExpect(result -> assertTrue(result.getResolvedException()
            instanceof ExceptionHandling.WrongCoordinateInputException));
  }

  /**
   * Test the call to find path with wrong input. Exception should be thrown.
   *
   * @throws Exception Exception
   */
  @Test
  public void findPathWrongEndingPointTest() throws Exception {
    mockMvc.perform(get("/pathfinding/findpath")
            .param("startpoint", "0", "0")
            .param("endpoint", "9", "9", "2")
            .param("size", "10", "10")
            .param("strategy", "dijkstra"))
        .andExpect(status().isBadRequest())
        .andExpect(result -> assertTrue(result.getResolvedException()
            instanceof ExceptionHandling.WrongCoordinateInputException));
  }

  /**
   * Test the call to find path with wrong input. Exception should be thrown.
   *
   * @throws Exception Exception
   */
  @Test
  public void findPathPointNotOnBoardTest() throws Exception {
    mockMvc.perform(get("/pathfinding/findpath")
            .param("startpoint", "0", "0")
            .param("endpoint", "9", "9")
            .param("size", "10", "2")
            .param("strategy", "dijkstra"))
        .andExpect(status().isBadRequest())
        .andExpect(result -> assertTrue(result.getResolvedException()
            instanceof ExceptionHandling.WrongInputOfBoardSizeAndPoints));
  }

  /**
   * Test the call to find path with wrong input. Exception should be thrown.
   *
   * @throws Exception Exception
   */
  @Test
  public void findPathPointNoStrategyTest() throws Exception {
    mockMvc.perform(get("/pathfinding/findpath")
            .param("startpoint", "0", "0")
            .param("endpoint", "9", "9")
            .param("size", "10", "10")
            .param("strategy", "dijkt"))
        .andExpect(status().isBadRequest())
        .andExpect(result -> assertTrue(result.getResolvedException()
            instanceof MethodArgumentTypeMismatchException));
  }

  /**
   * Test the call to create random nodes.
   *
   * @throws Exception Exception
   */
  @Test
  public void createRandomNodesCallTest() throws Exception {
    mockMvc.perform(get("/pathfinding/random-nodes")
            .param("size", "10", "10"))
        .andExpect(status().isOk());
  }

  /**
   * Test the call to create a random maze.
   *
   * @throws Exception Exception
   */
  @Test
  public void createRandomMazeCallTest() throws Exception {
    mockMvc.perform(get("/pathfinding/random-maze")
            .param("size", "10", "10"))
        .andExpect(status().isOk());
  }
}
