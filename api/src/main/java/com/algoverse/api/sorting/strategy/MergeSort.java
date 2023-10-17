package com.algoverse.api.sorting.strategy;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of in place merge sort.
 */
public class MergeSort implements SortingStrategy {

  private MergeSort() {

  }

  /**
   * Static factory methods.
   *
   * @return Returns MergeSort object
   */
  public static MergeSort createMergeSort() {
    return new MergeSort();
  }

  @Override
  public ImmutableList<ImmutableList<Integer>> sort(List<Integer> toSort) {

    List<ImmutableList<Integer>> sortedLists = new ArrayList<>();
    sortedLists.add(ImmutableList.copyOf(toSort));

    int currentSize;
    int leftStart;

    for (currentSize = 1; currentSize <= toSort.size() - 1; currentSize = 2 * currentSize) {
      for (leftStart = 0; leftStart < toSort.size() - 1; leftStart += 2 * currentSize) {
        int mid = Math.min(leftStart + currentSize - 1, toSort.size() - 1);
        int rightEnd = Math.min(leftStart + 2 * currentSize - 1, toSort.size() - 1);
        merge(toSort, leftStart, mid, rightEnd);
        sortedLists.add(ImmutableList.copyOf(toSort));
      }
    }
    return ImmutableList.copyOf(sortedLists);
  }

  private void merge(List<Integer> toSort, int left, int mid, int right) {
    int i;
    int j;
    int n = mid - left + 1;
    int m = right - mid;

    int[] leftArr = new int[n];
    int[] rightArr = new int[m];

    for (i = 0; i < n; i++) {
      leftArr[i] = toSort.get(left + i);
    }
    for (j = 0; j < m; j++) {
      rightArr[j] = toSort.get(mid + 1 + j);
    }

    i = 0;
    j = 0;
    int k = left;

    while (i < n && j < m) {
      if (leftArr[i] <= rightArr[j]) {
        toSort.set(k, leftArr[i]);
        i++;
      } else {
        toSort.set(k, rightArr[j]);
        j++;
      }
      k++;
    }

    while (i < n) {
      toSort.set(k, leftArr[i]);
      i++;
      k++;
    }

    while (j < m) {
      toSort.set(k, rightArr[j]);
      j++;
      k++;

    }
  }
}
