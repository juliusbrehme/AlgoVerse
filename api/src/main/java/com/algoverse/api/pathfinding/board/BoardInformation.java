package com.algoverse.api.pathfinding.board;

import com.google.common.collect.ImmutableMap;

/**
 * This record holds the information about the board.
 *
 * @param startingNode The starting node
 * @param endingNode   The end node
 * @param wall         All obstacles on the board
 * @param boardSize    The size of the board
 */
public record BoardInformation(Coordinates startingNode,
                               Coordinates endingNode,
                               ImmutableMap<Coordinates, Integer> wall,
                               Coordinates boardSize) {
}
