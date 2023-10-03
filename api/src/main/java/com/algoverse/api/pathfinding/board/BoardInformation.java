package com.algoverse.api.pathfinding.board;

import java.util.HashMap;

public record BoardInformation(Coordinates startingNode,
                               Coordinates endingNode,
                               HashMap<Coordinates, Integer> wall,
                               Coordinates boardSize) {
}
