package com.algoverse.api.binarytree;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

  @GetMapping("treesearch/search")
  public ImmutableList<Integer> searchTree(@RequestParam(value = "tree") List<Integer> tree,
                                           @RequestParam(value = "element") int element,
                                           @RequestParam(value = "strategy")
                                           BalancedBinaryTree.Strategy strategy) {
    return searchTreeService.searchTree(tree, element, strategy);
  }

  @GetMapping("treesearch/create-tree")
  public ImmutableList<Integer> createSearchTree(@RequestParam(value = "size") int size) {
    return searchTreeService.createSearchTree(size);
  }


}
