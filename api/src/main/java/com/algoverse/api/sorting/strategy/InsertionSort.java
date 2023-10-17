package com.algoverse.api.sorting.strategy;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of insertion sort.
 */
public class InsertionSort implements SortingStrategy {

  private InsertionSort() {

  }

  /**
   * Static factory method.
   *
   * @return Returns an object of InsertionSort
   */
  public static InsertionSort createInsertionSort() {
    return new InsertionSort();
  }

  @Override
  public ImmutableList<ImmutableList<Integer>> sort(List<Integer> toSort) {

    List<ImmutableList<Integer>> sortedLists = new ArrayList<>();
    sortedLists.add(ImmutableList.copyOf(toSort));

    for (int i = 1; i < toSort.size(); ++i) {
      int key = toSort.get(i);
      int j = i - 1;
      while (j >= 0 && toSort.get(j) > key) {
        toSort.set(j + 1, toSort.get(j));
        j--;
      }
      toSort.set(j + 1, key);
      sortedLists.add(ImmutableList.copyOf(toSort));
    }
    return ImmutableList.copyOf(sortedLists);
  }
}