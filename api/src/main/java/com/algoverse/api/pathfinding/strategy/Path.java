package com.algoverse.api.pathfinding.strategy;

import com.algoverse.api.pathfinding.board.Coordinates;

public record Path(Coordinates[] path, Coordinates[] visitedNodes) {
}
