package com.algoverse.api;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.algoverse.api.sorting.SortingController;
import com.algoverse.api.sorting.SortingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@WebMvcTest(controllers = SortingController.class)
public class SortingControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private SortingService sortingService;

  @Autowired
  private SortingController sortingController;

  @Test
  public void sortCallTest() throws Exception {
    mockMvc.perform(get("/sorting/sort")
            .param("toSort", "0", "0", "5", "1", "0")
            .param("strategy", "selectionsort"))
        .andExpect(status().isOk());
  }

  @Test
  public void sortWithWrongStrategyTest() throws Exception {
    mockMvc.perform(get("/sorting/sort")
        .param("toSort", "4", "3", "2", "1")
        .param("strategy", "selection"))
        .andExpect(status().isBadRequest())
        .andExpect(result -> assertTrue(result.getResolvedException() instanceof
            MethodArgumentTypeMismatchException));
  }

  @Test
  public void createRandomNumberCallTest() throws Exception {
    mockMvc.perform(get("/sorting/random-numbers")
        .param("size", "100"))
        .andExpect(status().isOk());
  }

}
