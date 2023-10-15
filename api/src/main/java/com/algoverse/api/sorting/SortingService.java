package com.algoverse.api.sorting;

import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

/**
 * The service class for sorting.
 */
@Service
public class SortingService {

  /**
   * This method delegates the sorting to the given sorting strategy.
   *
   * @param toSort            The array that is to be sorted
   * @param sortingStrategies The strategy that is supposed to be used
   * @return Returns a list of a list where every swap is saved in the list
   */
  public ImmutableList<ImmutableList<Integer>> sort(int[] toSort,
                                                    SortingFactory.Strategies sortingStrategies) {
    SortingFactory sortingFactory = SortingFactory.createSortingStrategy(sortingStrategies);
    return sortingFactory.sort(toSort);
  }

  /**
   * This method creates an array of random integers.
   *
   * @return Immutable list of random integers
   */
  public ImmutableList<Integer> createRandomNumbers(int size) {
    return ImmutableList.copyOf(SortingFactory.createRandomArray(size));
  }
}
