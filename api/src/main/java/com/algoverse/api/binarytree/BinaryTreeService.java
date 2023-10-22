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
   * @param tree The balanced binary tree as a list. (Tree is filled from left to right)
   * @param element The value to search
   * @param sttrategy The strategy being used
   * @return A list of visited indexes
   */
  public ImmutableList<Integer> searchTree(List<Integer> tree, int element,
                                           BalancedBinaryTree.Strategy sttrategy) {
    BalancedBinaryTree binTree = BalancedBinaryTree.createBinaryTree(tree);
    return binTree.search(sttrategy, element);
  }

  public ImmutableList<Integer> createSearchTree(int size) {
    BalancedBinaryTree tree = BalancedBinaryTree.createBinaryTree(size);
    return tree.traverseToList();
  }

}
