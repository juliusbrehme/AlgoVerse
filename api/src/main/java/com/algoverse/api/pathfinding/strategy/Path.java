package com.algoverse.api.pathfinding.strategy;

import com.algoverse.api.pathfinding.board.Coordinates;
import com.google.common.collect.ImmutableList;

public record Path(ImmutableList<Coordinates> path, ImmutableList<Coordinates> visitedNodes) {
}
