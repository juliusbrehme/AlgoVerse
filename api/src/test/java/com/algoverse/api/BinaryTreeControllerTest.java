package com.algoverse.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.algoverse.api.binarytree.BinaryTreeController;
import com.algoverse.api.binarytree.BinaryTreeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test the calls to the tree controller.
 */
@SuppressWarnings("unused")
@WebMvcTest(controllers = BinaryTreeController.class)
public class BinaryTreeControllerTest {


  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BinaryTreeService treeService;

  @Autowired
  private BinaryTreeController treeController;

  /**
   * Test the call to search in a tree.
   *
   * @throws Exception Exception
   */
  @Test
  public void searchTreeCallTest() throws Exception {
    mockMvc.perform(get("/treesearch/search")
            .param("tree", "1", "2", "3", "4", "5")
            .param("element", "6")
            .param("strategy", "bfs"))
        .andExpect(status().isOk());
  }

  /**
   * Test the call to search in a tree with wrong call.
   *
   * @throws Exception Exception
   */
  @Test
  public void searchTreeWrongCallTest() throws Exception {
    mockMvc.perform(get("/treesearch/search")
            .param("element", "6")
            .param("strategy", "bfs"))
        .andExpect(status().isBadRequest());
  }

  /**
   * Test the call to create a tree.
   *
   * @throws Exception Exception
   */
  @Test
  public void createSearchTreeCall() throws Exception {
    mockMvc.perform(get("/treesearch/create-tree")
            .param("size", "-10"))
        .andExpect(status().isOk());

    mockMvc.perform(get("/treesearch/create-tree")
            .param("size", "10"))
        .andExpect(status().isOk());
  }

  /**
   * Test the call to create a tree with wrong call.
   *
   * @throws Exception Exception
   */
  @Test
  public void createSearchTreeWronngCall() throws Exception {
    mockMvc.perform(get("/treesearch/create-tree")
            .param("size", "b"))
        .andExpect(status().isBadRequest());
  }

}
