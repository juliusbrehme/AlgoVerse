package com.algoverse.api.sorting;

import com.algoverse.api.sorting.strategy.SortingStrategies;
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

  // TODO: implement random array of int generator, s. SortingService

  /**
   * This method creates an array of random integers.
   *
   * @return Returns an array of random integers
   */
  @GetMapping("sorting/random/numbers")
  public int[] createRandomNumbers() {
    return sortingService.createRandomNumbers();
  }

  /**
   * This method delegates the sorting to the service to sort the given array.
   *
   * @param toSort     The array that is to be sorted
   * @param strategies The strategy that is supposed to be used
   * @return Returns a list of a list where every swap is saved in the list, the last element is
   *         the sorted array
   */
  @GetMapping("sorting/sort")
  public List<List<Integer>> sort(@RequestParam(value = "toSort") int[] toSort,
                                  @RequestParam(value = "strategy") SortingStrategies strategies) {
    return sortingService.sort(toSort, strategies);
  }
}
