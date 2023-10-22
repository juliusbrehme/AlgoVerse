package com.algoverse.api.binarytree;

import com.google.common.collect.ImmutableList;
import jakarta.annotation.Nullable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class BalancedBinaryTree {

  private static final Random RAND = new Random();

  private Node root;


  private BalancedBinaryTree() {
    this.root = null;
  }

  public static BalancedBinaryTree createBinaryTree(int size) {
    BalancedBinaryTree tree = new BalancedBinaryTree();
    for (int i = 0; i < size; i++) {
      tree.addNode(RAND.nextInt(0, 100));
    }
    return tree;
  }

  public static BalancedBinaryTree createBinaryTree(List<Integer> tree) {
    BalancedBinaryTree binTree = new BalancedBinaryTree();
    for (int i = 0; i < tree.size(); i++) {
      binTree.addNode(tree.get(i));
    }
    return binTree;
  }

  public void addNode(int value) {
    if (root == null) {
      root = new Node(null, value, null);
      return;
    }

    Queue<Node> nodes = new LinkedList<>();
    nodes.add(root);

    while (!nodes.isEmpty()) {
      Node node = nodes.remove();
      if (node.getLeftNode() != null) {
        nodes.add(node.getLeftNode());
      } else {
        node.setLeftNode(new Node(null, value, null));
        return;
      }
      if (node.getRightNode() != null) {
        nodes.add(node.getRightNode());
      } else {
        node.setRightNode((new Node(null, value, null)));
        return;
      }
    }

  }

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

  public ImmutableList<Integer> search(Strategy strategy, int value) {
    if (strategy.equals(Strategy.BFS)) {
      return bfs(value);
    } else {
      return dfs(value);
    }
  }

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

  private ImmutableList<Integer> dfs(int value) {
    List<Integer> visitedNodes = new ArrayList<>();

    if (root.getValue() == value) {
      return ImmutableList.of(0);
    }

    List<AbstractMap.SimpleEntry<Node, Integer>> nodes = new ArrayList<>();
    nodes.add(new AbstractMap.SimpleEntry<Node, Integer>(root, 0));

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

  public enum Strategy {
    BFS, DFS
  }

  @SuppressWarnings("unused")
  public static class Node {
    private int value;
    @Nullable
    private Node leftNode;

    @Nullable
    private Node rightNode;

    public Node(Node leftNode, int value, Node rightNode) {
      this.value = value;
      this.leftNode = leftNode;
      this.rightNode = rightNode;
    }

    public int getValue() {
      return this.value;
    }

    public void setValue(int value) {
      this.value = value;
    }

    public Node getLeftNode() {
      return this.leftNode;
    }

    public void setLeftNode(@Nullable Node leftNode) {
      this.leftNode = leftNode;
    }

    public Node getRightNode() {
      return this.rightNode;
    }

    public void setRightNode(@Nullable Node rightNode) {
      this.rightNode = rightNode;
    }
  }
}
