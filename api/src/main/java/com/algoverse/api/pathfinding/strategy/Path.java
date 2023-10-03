package com.algoverse.api.pathfinding.strategy;

import com.algoverse.api.pathfinding.board.Coordinates;
import com.google.common.collect.ImmutableList;

/**
 * Record that holds the information of the result of the pathfinding algorithm.
 *
 * @param path         Sequence of coordinates, which represent the path form starting to end node
 * @param visitedNodes Sequence of the visited nodes
 */
public record Path(ImmutableList<Coordinates> path, ImmutableList<Coordinates> visitedNodes) {
}
