package com.algoverse.api.binarytree;

import com.google.common.collect.ImmutableList;
import jakarta.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class BalancedBinaryTree {

  private static final Random RAND = new Random();

  private Node root;


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

  private BalancedBinaryTree() {
    this.root = null;
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
    ArrayList<Integer> visitedNodes = new ArrayList<>();
    return ImmutableList.copyOf(dfsRecursive(visitedNodes, root, value, 0));
  }

  private List<Integer> dfsRecursive(ArrayList<Integer> visitedNodes, Node node, int value, int index) {
    if (node == null) {
      return visitedNodes;
    }
    if (node.getValue() == value) {
      visitedNodes.add(index);
      System.out.println("Found");
      return visitedNodes;
    }
    visitedNodes.add(index);
    dfsRecursive(visitedNodes, node.getLeftNode(), value, 2*index+1);
    dfsRecursive(visitedNodes, node.getRightNode(), value, 2*index+2);
    return visitedNodes;
  }


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

    public Node getLeftNode() {
      return this.leftNode;
    }

    public Node getRightNode() {
      return this.rightNode;
    }

    public void setValue(int value) {
      this.value = value;
    }

    public void setLeftNode(@Nullable Node leftNode) {
      this.leftNode = leftNode;
    }

    public void setRightNode(@Nullable Node rightNode) {
      this.rightNode = rightNode;
    }
  }

  public enum Strategy {
    BFS, DFS
  }
}
