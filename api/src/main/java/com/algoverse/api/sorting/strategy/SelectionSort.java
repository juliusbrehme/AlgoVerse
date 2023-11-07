package com.algoverse.api.sorting.strategy;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

/**
 * Selection sort implementation.
 */
public class SelectionSort implements SortingStrategy {

  private SelectionSort() {
  }

  /**
   * Static Factory Method.
   *
   * @return Return a SelectionSort object
   */
  public static SelectionSort createSelectionSort() {
    return new SelectionSort();
  }

  @Override
  public ImmutableList<ImmutableList<Integer>> sort(List<Integer> toSort) {

    List<ImmutableList<Integer>> sortedLists = new ArrayList<>();
    sortedLists.add(ImmutableList.copyOf(toSort));

    int lastIndex = toSort.size();
    int insertIndex = 0;
    while (insertIndex < lastIndex) {
      int minimum = insertIndex;
      int insertIndexCopy = insertIndex;
      while (insertIndexCopy + 1 < lastIndex) {
        if (toSort.get(minimum) > toSort.get(insertIndexCopy + 1)) {
          minimum = insertIndexCopy + 1;
        }
        insertIndexCopy++;
      }
      swap(toSort, minimum, insertIndex);
      sortedLists.add(ImmutableList.copyOf(toSort));
      insertIndex++;
    }
    return ImmutableList.copyOf(sortedLists);
  }
}
