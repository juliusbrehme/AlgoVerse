package com.algoverse.api.sorting;

import com.algoverse.api.sorting.strategy.SelectionSort;
import com.algoverse.api.sorting.strategy.SortingStrategy;
import com.google.common.collect.ImmutableList;

/**
 * The factory to create the different strategies and use the different strategies.
 */
public class SortingFactory {

  private final SortingStrategy strategy;

  private SortingFactory(SortingStrategy strategy) {
    this.strategy = strategy;
  }

  /**
   * The method creates a new factory to use the different strategy for sorting.
   *
   * @param strategy The strategy that is supposed to be used.
   * @return Returns the factory with the strategy
   */
  public static SortingFactory createSortingStrategy(Strategies strategy) {
    switch (strategy) {
      case SELECTIONSORT:
        return new SortingFactory(SelectionSort.createSelectionSort());
      default:
        throw new IllegalArgumentException("The enum does not exist and the strategy could not "
            + "be initialized.");
    }
  }

  /**
   * The method delegates to the selected strategy to sort the array.
   *
   * @param toSort The array that is to be sorted
   * @return Returns a list of list with each step saved in a list
   */
  public ImmutableList<ImmutableList<Integer>> sort(int[] toSort) {
    return strategy.sort(toSort);
  }

  /**
   * Enum for all the sorting strategies.
   */
  public enum Strategies {
    SELECTIONSORT
  }
}
