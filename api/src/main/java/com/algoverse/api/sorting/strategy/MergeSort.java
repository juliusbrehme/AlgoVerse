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
    int i, j, k;
    int n = mid - left + 1;
    int m = right - mid;

    int[] L = new int[n];
    int[] R = new int[m];

    for (i = 0; i < n; i++) {
      L[i] = toSort.get(left + i);
    }
    for (j = 0; j < m; j++) {
      R[j] = toSort.get(mid + 1 + j);
    }

    i = 0;
    j = 0;
    k = left;

    while (i < n && j < m) {
      if (L[i] <= R[j]) {
        toSort.set(k, L[i]);
        i++;
      } else {
        toSort.set(k, R[j]);
        j++;
      }
      k++;
    }

    while (i < n) {
      toSort.set(k, L[i]);
      i++;
      k++;
    }

    while (j < m) {
      toSort.set(k, R[j]);
      j++;
      k++;

    }
  }
}
