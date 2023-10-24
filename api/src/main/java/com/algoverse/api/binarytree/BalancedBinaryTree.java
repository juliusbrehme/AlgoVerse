package com.algoverse.api.binarytree;

import com.google.common.collect.ImmutableList;
import jakarta.annotation.Nullable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * The class for a binary tree, where elements are inserted from right to left.
 */
public class BalancedBinaryTree {

  private static final Random RAND = new Random();

  private Node root;


  private BalancedBinaryTree() {
    this.root = null;
  }

  /**
   * Static constructor to create a tree with a number of random elements.
   *
   * @param size The number of elements in the tree, if size is smaller 0, return empty tree
   * @return Return a BalancedBinaryTree
   */
  public static BalancedBinaryTree createBinaryTree(int size) {
    BalancedBinaryTree tree = new BalancedBinaryTree();
    for (int i = 0; i < size; i++) {
      tree.addNode(RAND.nextInt(0, 100));
    }
    return tree;
  }

  /**
   * Static constructor to create a tree from a list of integers. The values will be inserted from
   * left to right.
   *
   * @param tree A list of integers.
   * @return Return a BalancedBinaryTree
   */
  public static BalancedBinaryTree createBinaryTree(List<Integer> tree) {
    BalancedBinaryTree binTree = new BalancedBinaryTree();
    for (int i = 0; i < tree.size(); i++) {
      binTree.addNode(tree.get(i));
    }
    return binTree;
  }

  /**
   * The method to add values into the tree.
   *
   * @param value The added value
   */
  public void addNode(int value) {
    if (root == null) {
      root = new Node(value);
      return;
    }

    Queue<Node> nodes = new LinkedList<>();
    nodes.add(root);

    while (!nodes.isEmpty()) {
      Node node = nodes.remove();
      if (node.getLeftNode() != null) {
        nodes.add(node.getLeftNode());
      } else {
        node.setLeftNode(new Node(value));
        return;
      }
      if (node.getRightNode() != null) {
        nodes.add(node.getRightNode());
      } else {
        node.setRightNode((new Node(value)));
        return;
      }
    }

  }

  /**
   * Convert a tree back to a list. BFS is used.
   *
   * @return A list of values representing the tree
   */
  public ImmutableList<Integer> traverseToList() {

    List<Integer> tree = new ArrayList<>();

    if (root == null) {
      return ImmutableList.of();
    }

    Queue<Node> nodes = new LinkedList<>();
    nodes.add(root);

    while (!nodes.isEmpty()) {

      Node node = nodes.remove();

      tree.add(node.getValue());

      if (node.getLeftNode() != null) {
        nodes.add(node.getLeftNode());
      }

      if (node.getRightNode() != null) {
        nodes.add(node.getRightNode());
      }
    }
    return ImmutableList.copyOf(tree);
  }

  /**
   * The search method to find a value in the tree. If there are more occurrences it will only find
   * the first occurrence.
   *
   * @param strategy The strategy that is being used
   * @param value    The value to search for
   * @return A list of the indexes of the elements that were visited
   */
  public ImmutableList<Integer> search(Strategy strategy, int value) {
    if (strategy.equals(Strategy.BFS)) {
      return bfs(value);
    } else {
      return dfs(value);
    }
  }

  /**
   * The BFS search method.
   *
   * @param value The value to search for
   * @return A list of the indexes of the elements that were visited
   */
  private ImmutableList<Integer> bfs(int value) {
    List<Integer> visitedNodes = new ArrayList<>();

    int index = 0;

    if (root.getValue() == value) {
      return ImmutableList.of(index);
    }

    Queue<Node> nodes = new LinkedList<>();
    nodes.add(root);

    while (!nodes.isEmpty()) {

      Node node = nodes.remove();

      visitedNodes.add(index++);

      if (node.getValue() == value) {
        return ImmutableList.copyOf(visitedNodes);
      }

      if (node.getLeftNode() != null) {
        nodes.add(node.getLeftNode());
      }

      if (node.getRightNode() != null) {
        nodes.add(node.getRightNode());
      }
    }
    return ImmutableList.copyOf(visitedNodes);
  }

  /**
   * The DFS search method.
   *
   * @param value The value to search for
   * @return A list of the indexes of the elements that were visited
   */
  private ImmutableList<Integer> dfs(int value) {
    List<Integer> visitedNodes = new ArrayList<>();

    if (root.getValue() == value) {
      return ImmutableList.of(0);
    }

    List<AbstractMap.SimpleEntry<Node, Integer>> nodes = new ArrayList<>();
    nodes.add(new AbstractMap.SimpleEntry<>(root, 0));

    while (!nodes.isEmpty()) {

      AbstractMap.SimpleEntry<Node, Integer> nodePair = nodes.remove(nodes.size() - 1);
      visitedNodes.add(nodePair.getValue());

      if (nodePair.getKey().getValue() == value) {
        return ImmutableList.copyOf(visitedNodes);
      }
      if (nodePair.getKey().getRightNode() != null) {
        nodes.add(new AbstractMap.SimpleEntry<>(nodePair.getKey().getRightNode(),
            2 * nodePair.getValue() + 2));
      }
      if (nodePair.getKey().getLeftNode() != null) {
        nodes.add(new AbstractMap.SimpleEntry<>(nodePair.getKey().getLeftNode(),
            2 * nodePair.getValue() + 1));
      }
    }
    return ImmutableList.copyOf(visitedNodes);
  }

  /**
   * The strategies for searching.
   */
  @SuppressWarnings("unused")
  public enum Strategy {
    BFS, DFS
  }

  /**
   * The Node of a tree.
   */
  @SuppressWarnings("unused")
  private static class Node {
    private int value;
    @Nullable
    private Node leftNode;

    @Nullable
    private Node rightNode;

    /**
     * Constructor of a node with left and right node being null.
     *
     * @param value The value
     */
    public Node(int value) {
      this.value = value;
      this.leftNode = null;
      this.rightNode = null;
    }

    /**
     * Getter method.
     *
     * @return Value
     */
    public int getValue() {
      return this.value;
    }

    /**
     * Setter method.
     *
     * @param value The value to set
     */
    public void setValue(int value) {
      this.value = value;
    }


    /**
     * Getter method.
     *
     * @return Left node
     */
    public Node getLeftNode() {
      return this.leftNode;
    }

    /**
     * Setter method.
     *
     * @param leftNode The node to set the left node
     */
    public void setLeftNode(@Nullable Node leftNode) {
      this.leftNode = leftNode;
    }

    /**
     * Getter method.
     *
     * @return Right node
     */
    public Node getRightNode() {
      return this.rightNode;
    }

    /**
     * Setter method.
     *
     * @param rightNode The node to set the right node
     */
    public void setRightNode(@Nullable Node rightNode) {
      this.rightNode = rightNode;
    }
  }
}
