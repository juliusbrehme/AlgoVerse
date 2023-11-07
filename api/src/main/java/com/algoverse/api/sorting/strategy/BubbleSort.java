package com.algoverse.api.sorting.strategy;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of bubble sort.
 */
public class BubbleSort implements SortingStrategy {

  private BubbleSort() {

  }

  /**
   * Static Factory method.
   *
   * @return Return a BubbleSort object
   */
  public static BubbleSort createBubbleSort() {
    return new BubbleSort();
  }

  @Override
  public ImmutableList<ImmutableList<Integer>> sort(List<Integer> toSort) {

    List<ImmutableList<Integer>> sortedLists = new ArrayList<>();
    sortedLists.add(ImmutableList.copyOf(toSort));

    for (int i = toSort.size(); i > 0; i--) {
      int index = 1;
      int max = 0;
      while (index < i) {
        if (toSort.get(max) < toSort.get(index)) {
          max = index;
        }
        index++;
      }
      swap(toSort, max, index - 1);
      sortedLists.add(ImmutableList.copyOf(toSort));
    }
    return ImmutableList.copyOf(sortedLists);
  }
}
