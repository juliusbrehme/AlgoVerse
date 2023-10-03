package com.algoverse.api.pathfinding.board;

public record BoardInformation(Coordinates startingNode,
                               Coordinates endingNode,
                               Coordinates[] wall,
                               Coordinates boardSize) {
}
