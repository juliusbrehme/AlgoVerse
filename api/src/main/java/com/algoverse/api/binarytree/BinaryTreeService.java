package com.algoverse.api.binarytree;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * The Service to control the tree search.
 */
@Service
public class BinaryTreeService {

  /**
   * The Method to search for an element in a balanced binary tree.
   *
   * @param tree      The balanced binary tree as a list. (Tree is filled from left to right)
   * @param element   The value to search for
   * @param sttrategy The strategy being used
   * @return A list of the indexes of the elements that were visited
   */
  public ImmutableList<Integer> searchTree(List<Integer> tree, int element,
                                           BalancedBinaryTree.Strategy sttrategy) {
    BalancedBinaryTree binTree = BalancedBinaryTree.createBinaryTree(tree);
    return binTree.search(sttrategy, element);
  }

  /**
   * Control the api request for creating a tree.
   *
   * @param size The number of elements
   * @return A tree as a list
   */
  public ImmutableList<Integer> createSearchTree(int size) {
    BalancedBinaryTree tree = BalancedBinaryTree.createBinaryTree(size);
    return tree.traverseToList();
  }

}
