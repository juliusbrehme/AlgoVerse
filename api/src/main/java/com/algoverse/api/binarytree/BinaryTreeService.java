package com.algoverse.api.binarytree;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BinaryTreeService {

  public ImmutableList<Integer> searchTree(List<Integer> tree, int element, BalancedBinaryTree.Strategy sttrategy) {
    BalancedBinaryTree binTree = BalancedBinaryTree.createBinaryTree(tree);
    return null;
  }

  public ImmutableList<Integer> createSearchTree(int size) {
    BalancedBinaryTree tree = BalancedBinaryTree.createBinaryTree(size);
    return tree.traverseToList();
  }

}
