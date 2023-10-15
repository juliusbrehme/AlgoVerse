package com.algoverse.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.algoverse.api.sorting.SortingFactory;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;
import java.util.Arrays;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/**
 * Testcases to check the sorting algorithms.
 */
public class SortingTests {

  private SortingFactory factory;

  /**
   * Sort array of size 5.
   *
   * @param strategies The strategy that is being used
   */
  @ParameterizedTest(name = "{0}")
  @EnumSource(SortingFactory.Strategies.class)
  public void sortingArrayOfSize5(SortingFactory.Strategies strategies) {
    factory = SortingFactory.createSortingStrategy(strategies);
    int[] sortArray = Ints.toArray(SortingFactory.createRandomArray(5));
    int[] sortedArray = Arrays.copyOf(sortArray, sortArray.length);
    Arrays.sort(sortedArray);
    assertEquals(ImmutableList.copyOf(Arrays.stream(sortedArray).boxed().toList()),
        Iterables.getLast(factory.sort(sortArray)));
  }

  /**
   * Sort array of size 10.
   *
   * @param strategies The strategy that is being used
   */
  @ParameterizedTest(name = "{0}")
  @EnumSource(SortingFactory.Strategies.class)
  public void sortingArrayOfSize10(SortingFactory.Strategies strategies) {
    factory = SortingFactory.createSortingStrategy(strategies);
    int[] sortArray = Ints.toArray(SortingFactory.createRandomArray(10));
    int[] sortedArray = Arrays.copyOf(sortArray, sortArray.length);
    Arrays.sort(sortedArray);
    assertEquals(ImmutableList.copyOf(Arrays.stream(sortedArray).boxed().toList()),
        Iterables.getLast(factory.sort(sortArray)));
  }

  /**
   * Sort array of size 15.
   *
   * @param strategies The strategy that is being used
   */
  @ParameterizedTest(name = "{0}")
  @EnumSource(SortingFactory.Strategies.class)
  public void sortingArrayOfSize15(SortingFactory.Strategies strategies) {
    factory = SortingFactory.createSortingStrategy(strategies);
    int[] sortArray = Ints.toArray(SortingFactory.createRandomArray(15));
    int[] sortedArray = Arrays.copyOf(sortArray, sortArray.length);
    Arrays.sort(sortedArray);
    assertEquals(ImmutableList.copyOf(Arrays.stream(sortedArray).boxed().toList()),
        Iterables.getLast(factory.sort(sortArray)));
  }

  /**
   * Sort array of size 20.
   *
   * @param strategies The strategy that is being used
   */
  @ParameterizedTest(name = "{0}")
  @EnumSource(SortingFactory.Strategies.class)
  public void sortingArrayOfSize20(SortingFactory.Strategies strategies) {
    factory = SortingFactory.createSortingStrategy(strategies);
    int[] sortArray = Ints.toArray(SortingFactory.createRandomArray(20));
    int[] sortedArray = Arrays.copyOf(sortArray, sortArray.length);
    Arrays.sort(sortedArray);
    assertEquals(ImmutableList.copyOf(Arrays.stream(sortedArray).boxed().toList()),
        Iterables.getLast(factory.sort(sortArray)));
  }

  /**
   * Sort array of size 25.
   *
   * @param strategies The strategy that is being used
   */
  @ParameterizedTest(name = "{0}")
  @EnumSource(SortingFactory.Strategies.class)
  public void sortingArrayOfSize25(SortingFactory.Strategies strategies) {
    factory = SortingFactory.createSortingStrategy(strategies);
    int[] sortArray = Ints.toArray(SortingFactory.createRandomArray(25));
    int[] sortedArray = Arrays.copyOf(sortArray, sortArray.length);
    Arrays.sort(sortedArray);
    assertEquals(ImmutableList.copyOf(Arrays.stream(sortedArray).boxed().toList()),
        Iterables.getLast(factory.sort(sortArray)));
  }

  /**
   * Sort array of same elements.
   *
   * @param strategies The strategy that is being used
   */
  @ParameterizedTest(name = "{0}")
  @EnumSource(SortingFactory.Strategies.class)
  public void sortingOfSameIntegers(SortingFactory.Strategies strategies) {
    factory = SortingFactory.createSortingStrategy(strategies);
    int[] sortArray = {0, 0, 0, 0, 0, 0, 0};
    int[] sortedArray = Arrays.copyOf(sortArray, sortArray.length);
    Arrays.sort(sortedArray);
    assertEquals(ImmutableList.copyOf(Arrays.stream(sortedArray).boxed().toList()),
        Iterables.getLast(factory.sort(sortArray)));
  }

  /**
   * Sort empty array.
   *
   * @param strategies The strategy that is being used
   */
  @ParameterizedTest(name = "{0}")
  @EnumSource(SortingFactory.Strategies.class)
  public void sortingEmptyArray(SortingFactory.Strategies strategies) {
    factory = SortingFactory.createSortingStrategy(strategies);
    int[] sortArray = {};
    int[] sortedArray = Arrays.copyOf(sortArray, sortArray.length);
    Arrays.sort(sortedArray);
    assertEquals(ImmutableList.copyOf(Arrays.stream(sortedArray).boxed().toList()),
        Iterables.getLast(factory.sort(sortArray)));
  }


}
