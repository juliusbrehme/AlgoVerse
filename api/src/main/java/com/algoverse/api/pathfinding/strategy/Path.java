package com.algoverse.api.pathfinding.strategy;

import com.algoverse.api.pathfinding.board.Coordinates;
import java.util.List;

public record Path(List<Coordinates> path, List<Coordinates> visitedNodes) {
}
