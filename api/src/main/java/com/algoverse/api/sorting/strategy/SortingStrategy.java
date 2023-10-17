package com.algoverse.api.sorting.strategy;

import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 * This is the interface for the different sorting algorithms. Every sorting algorithm should
 * implement this interface.
 */
public interface SortingStrategy {

  /**
   * This method is to sort the given integer array.
   *
   * @param toSort The input as an array
   * @return Returns a list of a list where every swap is saved in the list
   */
  ImmutableList<ImmutableList<Integer>> sort(List<Integer> toSort);

  /**
   * Method to swap elements in an array.
   *
   * @param toSort      The array where elements need to be swapped
   * @param minimum     First index of the element
   * @param insertIndex Second index of the second element
   */
  default void swap(List<Integer> toSort, int minimum, int insertIndex) {
    int cache = toSort.get(insertIndex);
    toSort.set(insertIndex, toSort.get(minimum));
    toSort.set(minimum, cache);
  }
}
