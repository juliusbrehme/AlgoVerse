package com.algoverse.api;


import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

/**
 * Test class to test if the API is working.
 */
@SuppressWarnings("unused")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApiApplicationTests {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;


  /**
   * Tests if the API loads the contexts.
   */
  @Test
  public void contextLoads() {
  }

  /**
   * Tests if a get request can be made for path finding and if a response is generated.
   */
  @Test
  public void pathFindingTest() {
    String result = restTemplate.getForObject("http://localhost:" + port
            + "/pathfinding/findpath?startpoint=0,0&endpoint=4,4&size=5,5&strategy=DIJKSTRA",
        String.class);

    assertThat(result).isNotEmpty();
  }

  /**
   * Tests if a get request can be made for creating random nodes and if a response is generated.
   */
  @Test
  public void createRandomNodeTest() {
    String result = restTemplate.getForObject("http://localhost:" + port
        + "/pathfinding/random-nodes?size=2,1", String.class);

    assertThat(result).isNotEmpty();
  }

  /**
   * Tests if a get request can be made for sorting and if a response is generated.
   */
  @Test
  public void sortTest() {
    String result = restTemplate.getForObject("http://localhost:" + port
        + "/sorting/sort?toSort=3,2,1&strategy=selectionsort", String.class);

    String expected = "[1,2,3]";
    assertThat(result).contains(expected);

    result = restTemplate.getForObject("http://localhost:" + port
        + "/sorting/sort?toSort=&strategy=selectionsort", String.class);

    expected = "[[]]";
    assertThat(result).contains(expected);
  }

  /**
   * Tests if a get request can be made for creating a random array and if a response is generated.
   */
  @Test
  public void createRandomNumbersTest() {
    String result = restTemplate.getForObject("http://localhost:" + port
        + "/sorting/random-numbers?size=10", String.class);

    assertThat(result).isNotEmpty();

    result = restTemplate.getForObject("http://localhost:" + port
        + "/sorting/random-numbers?size=0", String.class);

    assertThat(result).isEqualTo("[]");
  }

  @Test
  public void createSearchTreeTest() {
    String result = restTemplate.getForObject("http://localhost:" + port
        + "/treesearch/create-tree?size=10", String.class);

    assertThat(result).isNotEmpty();

    result = restTemplate.getForObject("http://localhost:" + port
        + "/treesearch/create-tree?size=0", String.class);

    assertThat(result).isEqualTo("[]");
  }

  @Test
  public void searchTreeTest() {
    String result = restTemplate.getForObject("http://localhost:" + port
        + "/treesearch/search?tree=1,2,3,4,10&element=10&strategy=bfs", String.class);

    assertThat(result).isEqualTo("[0,1,2,3,4]");

    result = restTemplate.getForObject("http://localhost:" + port
        + "/treesearch/search?tree=&element=10&strategy=bfs", String.class);

    assertThat(result).isEqualTo("[]");
  }
}
