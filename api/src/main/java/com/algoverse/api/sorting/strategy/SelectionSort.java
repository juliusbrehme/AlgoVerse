package com.algoverse.api.sorting.strategy;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
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
  public ImmutableList<ImmutableList<Integer>> sort(int[] toSort) {

    List<ImmutableList<Integer>> sortedLists = new ArrayList<>();
    sortedLists.add(ImmutableList.copyOf(Arrays.stream(toSort).boxed().toList()));

    int lastIndex = toSort.length;
    int insertIndex = 0;
    while (insertIndex < lastIndex) {
      int minimum = insertIndex;
      int insertIndexCopy = insertIndex;
      while (insertIndexCopy + 1 < lastIndex) {
        if (toSort[minimum] > toSort[insertIndexCopy + 1]) {
          minimum = insertIndexCopy + 1;
        }
        insertIndexCopy++;
      }
      swap(toSort, minimum, insertIndex);
      sortedLists.add(ImmutableList.copyOf(Arrays.stream(toSort).boxed().toList()));
      insertIndex++;
    }
    return ImmutableList.copyOf(sortedLists);
  }

  /**
   * Method to swap elements in an array.
   *
   * @param toSort      The array where elements need to be swapped
   * @param minimum     First index of the element
   * @param insertIndex Second index of the second element
   */
  private void swap(int[] toSort, int minimum, int insertIndex) {
    int cache = toSort[insertIndex];
    toSort[insertIndex] = toSort[minimum];
    toSort[minimum] = cache;
  }
}
