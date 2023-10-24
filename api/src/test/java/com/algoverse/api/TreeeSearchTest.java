package com.algoverse.api;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import com.algoverse.api.binarytree.BalancedBinaryTree;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class TreeeSearchTest {

  private static final int NUMBEER_OF_TESTS = 10;
  private static final Random RAND = new Random();
  private BalancedBinaryTree tree;

  public ImmutableList<Integer> randomList() {
    int size = RAND.nextInt(1, 10);
    List<Integer> treeAsList = new ArrayList<>();
    for (int j = 0; j < size; j++) {
      int element = RAND.nextInt(0, 100);
      treeAsList.add(element);
    }
    return ImmutableList.copyOf(treeAsList);
  }

  @Test
  public void createBinaryTreeSizeTest() {
    for (int i = 0; i < NUMBEER_OF_TESTS; i++) {
      int size = RAND.nextInt(0, 100);
      tree = BalancedBinaryTree.createBinaryTree(size);
      assertThat(tree.traverseToList().size()).isEqualTo(size);
    }
  }

  @Test
  public void createBinaryTreeFromListTest() {
    for (int i = 0; i < NUMBEER_OF_TESTS; i++) {
      ImmutableList<Integer> treeAsList = randomList();
      assertThat(treeAsList)
          .isEqualTo(BalancedBinaryTree.createBinaryTree(treeAsList).traverseToList());
    }
  }

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

  @Test
  public void searchBfsTest() {
    for (int i = 0; i < NUMBEER_OF_TESTS; i++) {
      ImmutableList<Integer> treeAsList = randomList();
      tree = BalancedBinaryTree.createBinaryTree(treeAsList);
      ImmutableList<Integer> visitedNode = tree.search(
          BalancedBinaryTree.Strategy.BFS, treeAsList.get(treeAsList.size() - 1));
      visitedNode.replaceAll(int -> treeAsList.get(int));

      assertThat(treeAsList).isEqualTo(visitedNode);
    }
  }

}
