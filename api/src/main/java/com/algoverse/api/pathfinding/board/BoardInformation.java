package com.algoverse.api.pathfinding.board;

import com.google.common.collect.ImmutableMap;


public record BoardInformation(Coordinates startingNode,
                               Coordinates endingNode,
                               ImmutableMap<Coordinates, Integer> wall,
                               Coordinates boardSize) {
}
