package com.algoverse.api.binarytree;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Controller to control the api request.
 */
@SuppressWarnings("unused")
@RestController
public class BinaryTreeController {

  private final BinaryTreeService searchTreeService;

  /**
   * Constructor to be able to use SearchTreeService.
   *
   * @param searchTreeService The SearchTreeService
   */
  public BinaryTreeController(BinaryTreeService searchTreeService) {
    this.searchTreeService = searchTreeService;
  }

  /**
   * Control the api request for searching a value in a tree.
   *
   * @param tree     A tree as a list. Value to tree is inserted from left to right
   * @param element  The value to search for
   * @param strategy The strategy being used
   * @return A list of the indexes of the elements that were visited
   */
  @GetMapping("treesearch/search")
  public ImmutableList<Integer> searchTree(@RequestParam(value = "tree") List<Integer> tree,
                                           @RequestParam(value = "element") int element,
                                           @RequestParam(value = "strategy")
                                           BalancedBinaryTree.Strategy strategy) {
    return searchTreeService.searchTree(tree, element, strategy);
  }

  /**
   * Control the api request for creating a tree.
   *
   * @param size The number of elements
   * @return A tree as a list
   */
  @GetMapping("treesearch/create-tree")
  public ImmutableList<Integer> createSearchTree(@RequestParam(value = "size") int size) {
    return searchTreeService.createSearchTree(size);
  }


}
