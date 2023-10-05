package com.algoverse.api.sorting;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;

/**
 * The service class for sorting.
 */
@Service
public class SortingService {

  private static final Random RAND = new Random();

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
    List<Integer> randomList = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      randomList.add(RAND.nextInt(100));
    }
    return ImmutableList.copyOf(randomList);
  }
}
