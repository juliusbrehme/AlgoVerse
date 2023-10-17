package com.algoverse.api.sorting;

import com.google.common.collect.ImmutableList;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller class for sorting.
 */
@SuppressWarnings("unused")
@SuppressFBWarnings("EI_EXPOSE_REP2")
@RestController
public class SortingController {

  private final SortingService sortingService;

  public SortingController(SortingService sortingService) {
    this.sortingService = sortingService;
  }


  /**
   * This method creates an array of random integers.
   *
   * @param size The size of the generated array
   * @return Returns an immutable list of random integers
   */
  @GetMapping("sorting/random/numbers")
  public ImmutableList<Integer> createRandomNumbers(@RequestParam(value = "size") int size) {
    return sortingService.createRandomNumbers(size);
  }

  /**
   * This method delegates the sorting to the service to sort the given array.
   *
   * @param toSort     The array that is to be sorted
   * @param strategies The strategy that is supposed to be used
   * @return Returns a list of a list where every swap is saved in the list
   */
  @GetMapping("sorting/sort")
  public ImmutableList<ImmutableList<Integer>> sort(@RequestParam(value = "toSort")
                                                    List<Integer> toSort,
                                                    @RequestParam(value = "strategy")
                                                    SortingFactory.Strategies strategies) {
    return sortingService.sort(toSort, strategies);
  }
}
