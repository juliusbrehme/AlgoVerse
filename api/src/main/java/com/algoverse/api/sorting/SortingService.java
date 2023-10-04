package com.algoverse.api.sorting;

import com.algoverse.api.sorting.strategy.SelectionSort;
import com.algoverse.api.sorting.strategy.SortingStrategies;
import com.algoverse.api.sorting.strategy.SortingStrategy;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * The service class for sorting.
 */
@Service
public class SortingService {

  private SortingStrategy sortingStrategy;

  public void setSortingStrategy(SortingStrategy sortingStrategy) {
    this.sortingStrategy = sortingStrategy;
  }

  /**
   * This method delegates the sorting to the given sorting strategy.
   *
   * @param toSort            The array that is to be sorted
   * @param sortingStrategies The strategy that is supposed to be used
   * @return Returns a list of a list where every swap is saved in the list
   */
  public List<List<Integer>> sort(int[] toSort, SortingStrategies sortingStrategies) {
    if (sortingStrategies == SortingStrategies.SELECTIONSORT) {
      setSortingStrategy(SelectionSort.createSelectionSort());
    }
    return sortingStrategy.sort(toSort);
  }

  // TODO: implement random array of int generator

  /**
   * This method creates an array of random integers.
   *
   * @return Array of random integers
   */
  public int[] createRandomNumbers() {
    return null;
  }
}
