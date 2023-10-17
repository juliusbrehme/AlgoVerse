package com.algoverse.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.algoverse.api.sorting.SortingFactory;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
    List<Integer> sortArray = SortingFactory.createRandomArray(5);
    List<Integer> expected = new java.util.ArrayList<>(List.copyOf(sortArray));
    Collections.sort(expected);
    if (strategies.equals(SortingFactory.Strategies.QUICKSORT)) {
      ImmutableList<ImmutableList<Integer>> actual = factory.sort(sortArray);
      assertEquals(sortArray,
          actual.get(actual.size() - 2));
    } else {
      assertEquals(expected,
          Iterables.getLast(factory.sort(sortArray)));
    }
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
    List<Integer> sortArray = SortingFactory.createRandomArray(10);
    List<Integer> expected = new java.util.ArrayList<>(List.copyOf(sortArray));
    Collections.sort(expected);
    if (strategies.equals(SortingFactory.Strategies.QUICKSORT)) {
      ImmutableList<ImmutableList<Integer>> actual = factory.sort(sortArray);
      assertEquals(sortArray,
          actual.get(actual.size() - 2));
    } else {
      assertEquals(expected,
          Iterables.getLast(factory.sort(sortArray)));
    }
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
    List<Integer> sortArray = SortingFactory.createRandomArray(15);
    List<Integer> expected = new java.util.ArrayList<>(List.copyOf(sortArray));
    Collections.sort(expected);
    if (strategies.equals(SortingFactory.Strategies.QUICKSORT)) {
      ImmutableList<ImmutableList<Integer>> actual = factory.sort(sortArray);
      assertEquals(sortArray,
          actual.get(actual.size() - 2));
    } else {
      assertEquals(expected,
          Iterables.getLast(factory.sort(sortArray)));
    }
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
    List<Integer> sortArray = SortingFactory.createRandomArray(20);
    List<Integer> expected = new java.util.ArrayList<>(List.copyOf(sortArray));
    Collections.sort(expected);
    if (strategies.equals(SortingFactory.Strategies.QUICKSORT)) {
      ImmutableList<ImmutableList<Integer>> actual = factory.sort(sortArray);
      assertEquals(sortArray,
          actual.get(actual.size() - 2));
    } else {
      assertEquals(expected,
          Iterables.getLast(factory.sort(sortArray)));
    }
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
    List<Integer> sortArray = SortingFactory.createRandomArray(25);
    List<Integer> expected = new java.util.ArrayList<>(List.copyOf(sortArray));
    Collections.sort(expected);
    if (strategies.equals(SortingFactory.Strategies.QUICKSORT)) {
      ImmutableList<ImmutableList<Integer>> actual = factory.sort(sortArray);
      assertEquals(sortArray,
          actual.get(actual.size() - 2));
    } else {
      assertEquals(expected,
          Iterables.getLast(factory.sort(sortArray)));
    }
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
    List<Integer> sortArray = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0);
    List<Integer> expected = new ArrayList<>(List.copyOf(sortArray));
    Collections.sort(expected);
    if (strategies.equals(SortingFactory.Strategies.QUICKSORT)) {
      ImmutableList<ImmutableList<Integer>> actual = factory.sort(sortArray);
      assertEquals(expected,
          actual.get(actual.size() - 2));
    } else {
      assertEquals(expected,
          Iterables.getLast(factory.sort(sortArray)));
    }
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
    List<Integer> sortArray = List.of();
    List<Integer> expected = new ArrayList<>(List.copyOf(sortArray));
    Collections.sort(expected);
    if (strategies.equals(SortingFactory.Strategies.QUICKSORT)) {
      ImmutableList<ImmutableList<Integer>> actual = factory.sort(sortArray);
      assertEquals(expected,
          actual.get(actual.size() - 2));
    } else {
      assertEquals(expected,
          Iterables.getLast(factory.sort(sortArray)));
    }
  }
}
