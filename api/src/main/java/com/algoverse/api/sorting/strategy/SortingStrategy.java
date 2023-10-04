package com.algoverse.api.sorting.strategy;

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
  List<List<Integer>> sort(int[] toSort);
}
