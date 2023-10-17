package com.algoverse.api.sorting.strategy;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implementation of quick sort.
 */
public class QuickSort implements SortingStrategy {

  private static final Random RAND = new Random();

  private QuickSort() {

  }

  /**
   * Static factory method.
   *
   * @return Return an object of QuickSort
   */
  public static QuickSort createQuickSort() {
    return new QuickSort();
  }

  @Override
  public ImmutableList<ImmutableList<Integer>> sort(List<Integer> toSort) {

    List<ImmutableList<Integer>> sortedLists = new ArrayList<>();
    sortedLists.add(ImmutableList.copyOf(toSort));

    if (toSort.isEmpty()) {
      sortedLists.add(ImmutableList.of());
      return ImmutableList.copyOf(sortedLists);
    }

    List<Integer> pivotElements = new ArrayList<>();
    pivotElements.add(-1);

    int[] stack = new int[toSort.size()];
    int top = -1;

    stack[++top] = 0;
    stack[++top] = toSort.size() - 1;

    while (top >= 0) {
      int right = stack[top--];
      int left = stack[top--];

      List<ImmutableList<Integer>> steps = partition(toSort, left, right);
      int p = steps.get(steps.size() - 1).get(0);
      int index = 0;
      while (index < steps.size() - 1) {
        sortedLists.add(steps.get(index));
        pivotElements.add(p);
        index++;
      }

      if (p - 1 > left) {
        stack[++top] = left;
        stack[++top] = p - 1;
      }
      if (p + 1 < right) {
        stack[++top] = p + 1;
        stack[++top] = right;
      }
    }

    sortedLists.add(ImmutableList.copyOf(pivotElements));
    return ImmutableList.copyOf(sortedLists);
  }

  private List<ImmutableList<Integer>> partition(List<Integer> toSort, int left, int right) {
    List<ImmutableList<Integer>> progress = new ArrayList<>();

    int pivot = toSort.get(right);
    int index = (left - 1);
    for (int j = left; j <= right - 1; j++) {
      if (toSort.get(j) <= pivot) {
        index++;
        swap(toSort, j, index);
        progress.add(ImmutableList.copyOf(toSort));
      }
    }
    swap(toSort, index + 1, right);
    progress.add(ImmutableList.copyOf(toSort));
    progress.add(ImmutableList.of(index + 1));
    return progress;
  }
}
