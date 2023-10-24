package com.algoverse.api;

import static com.google.common.truth.Truth.assertThat;

import com.algoverse.api.binarytree.BalancedBinaryTree;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/**
 * Testing the BalancedBinaryTree class.
 */
public class TreeSearchTest {

  private static final int NUMBEER_OF_TESTS = 10;
  private static final Random RAND = new Random();
  private BalancedBinaryTree tree;

  /**
   * Creating a random list used as a tree as list.
   *
   * @return An immutable list used a tree
   */
  public ImmutableList<Integer> randomList() {
    int size = RAND.nextInt(1, 10);
    List<Integer> treeAsList = new ArrayList<>();
    for (int j = 0; j < size; j++) {
      int element = RAND.nextInt(0, 100);
      treeAsList.add(element);
    }
    return ImmutableList.copyOf(treeAsList);
  }

  /**
   * Test the createBinaryTree method with input int size.
   */
  @Test
  public void createBinaryTreeSizeTest() {
    for (int i = 0; i < NUMBEER_OF_TESTS; i++) {
      int size = RAND.nextInt(0, 100);
      tree = BalancedBinaryTree.createBinaryTree(size);
      assertThat(tree.traverseToList().size()).isEqualTo(size);
    }
  }

  /**
   * Test to create an empty tree.
   */
  @Test
  public void createBinaryTreeEmptyTest() {
    tree = BalancedBinaryTree.createBinaryTree(0);
    assertThat(tree.traverseToList()).isEqualTo(List.of());

    tree = BalancedBinaryTree.createBinaryTree(-10);
    assertThat(tree.traverseToList()).isEqualTo(List.of());

    tree = BalancedBinaryTree.createBinaryTree(List.of());
    assertThat(tree.traverseToList()).isEqualTo(List.of());
  }

  /**
   * Test the createBinaryTree method with a list as input.
   */
  @Test
  public void createBinaryTreeFromListTest() {
    for (int i = 0; i < NUMBEER_OF_TESTS; i++) {
      ImmutableList<Integer> treeAsList = randomList();
      assertThat(treeAsList)
          .isEqualTo(BalancedBinaryTree.createBinaryTree(treeAsList).traverseToList());
    }
  }

  /**
   * Testing the search method with a valid value.
   *
   * @param strategy The strategy that is being used
   */
  @ParameterizedTest(name = "{0}")
  @EnumSource(BalancedBinaryTree.Strategy.class)
  public void searchTest(BalancedBinaryTree.Strategy strategy) {
    for (int i = 0; i < NUMBEER_OF_TESTS; i++) {
      ImmutableList<Integer> treeAsList = randomList();
      tree = BalancedBinaryTree.createBinaryTree(treeAsList);
      int searchValueIndex = RAND.nextInt(0, treeAsList.size());
      ImmutableList<Integer> visitedNode = tree.search(strategy, treeAsList.get(searchValueIndex));

      // Testing that the last index is the right index of the to search element
      assertThat(visitedNode.get(visitedNode.size() - 1)).isEqualTo(searchValueIndex);
      // Testing that it is actually the right element
      assertThat(treeAsList.get(visitedNode.get(visitedNode.size() - 1)))
          .isEqualTo(treeAsList.get(searchValueIndex));
      // Testing that the first element is the first element
      assertThat(0).isEqualTo(visitedNode.get(0));
    }
  }

  /**
   * Testing the search method with a value not in the tree.
   *
   * @param strategy The strategy that is being used
   */
  @ParameterizedTest(name = "{0}")
  @EnumSource(BalancedBinaryTree.Strategy.class)
  public void searchElementNotInTreeTest(BalancedBinaryTree.Strategy strategy) {
    for (int i = 0; i < NUMBEER_OF_TESTS; i++) {
      ImmutableList<Integer> treeAsList = randomList();
      tree = BalancedBinaryTree.createBinaryTree(treeAsList);
      ImmutableList<Integer> visitedNode = tree.search(strategy, 200);

      assertThat(200).isNotEqualTo(visitedNode.get(visitedNode.size() - 1));
      assertThat(treeAsList.size()).isEqualTo(visitedNode.size());
    }
  }

  /**
   * Testing just bfs.
   */
  @Test
  public void searchBfsTest() {
    for (int i = 0; i < NUMBEER_OF_TESTS; i++) {
      ImmutableList<Integer> treeAsList = randomList();
      tree = BalancedBinaryTree.createBinaryTree(treeAsList);
      ImmutableList<Integer> visitedNode = tree.search(
          BalancedBinaryTree.Strategy.BFS, treeAsList.get(treeAsList.size() - 1));
      List<Integer> actual = new ArrayList<>();
      for (Integer j : visitedNode) {
        actual.add(treeAsList.get(j));
      }
      assertThat(treeAsList).isEqualTo(actual);
    }
  }

  /**
   * Testing just dfs. This test is not good and should be updated.
   */
  @Test
  public void searchDfsTest() {
    tree = BalancedBinaryTree.createBinaryTree(3);
    List<Integer> treeAsList = tree.traverseToList();
    ImmutableList<Integer> visitedNodes = tree.search(BalancedBinaryTree.Strategy.DFS,
        treeAsList.get(1));
    List<Integer> actual = new ArrayList<>();
    for (Integer i : visitedNodes) {
      actual.add(treeAsList.get(i));
    }
    assertThat(actual).isEqualTo(List.of(treeAsList.get(0), treeAsList.get(1)));
  }

}
