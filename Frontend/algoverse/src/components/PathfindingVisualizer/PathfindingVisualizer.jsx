import React, { useState, useEffect } from "react";
import { useSearchParams } from "react-router-dom";
import Node from "./Node/Node";
import { dijkstra, getNodesInShortestPathOrder } from "../algorithms/dijkstra";
import { astar } from "../algorithms/astar";
import { bfs } from "../algorithms/bfs";
import { dfs } from "../algorithms/dfs";

import "./PathfindingVisualizer.css";

const START_NODE_ROW = 10;
const START_NODE_COL = 12;
const FINISH_NODE_ROW = 10;
const FINISH_NODE_COL = 35;

const PathfindingVisualizer = () => {
  const [searchParams] = useSearchParams();
  const algorithm = searchParams.get("algorithm");
  const [grid, setGrid] = useState([]);
  const [mouseIsPressed, setMouseIsPressed] = useState(false);
  const [shortestPathNodes, setShortestPathNodes] = useState([]);

  useEffect(() => {
    setGrid(getInitialGrid());
  }, []);

  const handleMouseDown = (row, col) => {
    const newGrid = getNewGridWithWallToggled(grid, row, col);
    setGrid(newGrid);
    setMouseIsPressed(true);
  };

  const handleMouseEnter = (row, col) => {
    if (!mouseIsPressed) return;
    const newGrid = getNewGridWithWallToggled(grid, row, col);
    setGrid(newGrid);
  };

  const handleMouseUp = () => {
    setMouseIsPressed(false);
  };

  const selectPathfindingAlgorithm = (algorithm) => {
    switch (algorithm) {
      case dijkstra:
        break;
      case bfs:
        break;
      case astar:
        break;
      case dfs:
        break;
      default:
        break;
    }
  };

  const animateDijkstra = (visitedNodesInOrder, nodesInShortestPathOrder) => {
    for (let i = 0; i < visitedNodesInOrder.length; i++) {
      setTimeout(() => {
        const node = visitedNodesInOrder[i];
        if (node) {
          document.getElementById(`node-${node.row}-${node.col}`).className =
            "node node-visited";
        }
      }, 10 * i);
    }
    setTimeout(() => {
      animateShortestPath(nodesInShortestPathOrder);
    }, 10 * visitedNodesInOrder.length);
  };

  const animateShortestPath = (nodesInShortestPathOrder) => {
    for (let i = 0; i < nodesInShortestPathOrder.length; i++) {
      setTimeout(() => {
        const node = nodesInShortestPathOrder[i];
        document.getElementById(`node-${node.row}-${node.col}`).className =
          "node node-shortest-path";
        setShortestPathNodes((spNodes) => [...spNodes, node]);
      }, 50 * i);
    }
  };

  const visualizeDijkstra = () => {
    const startNode = grid[START_NODE_ROW][START_NODE_COL];
    const finishNode = grid[FINISH_NODE_ROW][FINISH_NODE_COL];
    const visitedNodesInOrder = dijkstra(grid, startNode, finishNode);
    const nodesInShortestPathOrder = getNodesInShortestPathOrder(finishNode);
    animateDijkstra(visitedNodesInOrder, nodesInShortestPathOrder);
  };

  const resetGrid = () => {
    // Create a new grid based on the initial configuration
    const newGrid = getInitialGrid();

    // Additionally, reset any state that might have been changed in each node
    for (let row of newGrid) {
      for (let node of row) {
        // Reset the node properties that might have changed
        node.isVisited = false;
        node.distance = Infinity;
        node.previousNode = null;

        // Reset any UI changes made to the nodes
        const nodeElement = document.getElementById(
          `node-${node.row}-${node.col}`
        );
        if (nodeElement) {
          nodeElement.className = "node";
          // Add additional classes as needed, e.g., for start and finish nodes
          if (node.isStart) {
            nodeElement.classList.add("node-start");
          } else if (node.isFinish) {
            nodeElement.classList.add("node-finish");
          } else if (node.isWall) {
            nodeElement.classList.add("node-wall");
          }
        }
      }
    }

    // Update the state of the grid with the reset grid
    setGrid(newGrid);
  };

  return (
    <>
      <button className="button-accent" onClick={visualizeDijkstra}>
        {algorithm
          ? `Visualize ${algorithm}`
          : "Visualize Dijkstra's Algorithm"}
      </button>
      <button className="button-reset" onClick={resetGrid}>
        Reset Grid
      </button>
      <div className="gridContainer">
        <div className="gridA">
          {grid.map((row, rowIdx) => (
            <div key={rowIdx}>
              {row.map((node, nodeIdx) => {
                const { row, col, isFinish, isStart, isWall } = node;
                return (
                  <Node
                    key={nodeIdx}
                    col={col}
                    isFinish={isFinish}
                    isStart={isStart}
                    isWall={isWall}
                    mouseIsPressed={mouseIsPressed}
                    onMouseDown={() => handleMouseDown(row, col)}
                    onMouseEnter={() => handleMouseEnter(row, col)}
                    onMouseUp={handleMouseUp}
                    row={row}
                  />
                );
              })}
            </div>
          ))}
        </div>
      </div>
      <div className="shortestPathNodesContainer">
      <h3>Shortest Path Nodes (in order):</h3>
      <div>
        {shortestPathNodes.map((node, index) => (
          <span key={index} style={{ margin: '0 5px' }}>
            ({node.row}, {node.col})
          </span>
        ))}
      </div>
    </div>
    </>
  );
};

const getInitialGrid = () => {
  const grid = [];
  for (let row = 0; row < 20; row++) {
    const currentRow = [];
    for (let col = 0; col < 48; col++) {
      currentRow.push(createNode(col, row));
    }
    grid.push(currentRow);
  }
  return grid;
};

const createNode = (col, row) => {
  return {
    col,
    row,
    isStart: row === START_NODE_ROW && col === START_NODE_COL,
    isFinish: row === FINISH_NODE_ROW && col === FINISH_NODE_COL,
    distance: Infinity,
    isVisited: false,
    isWall: false,
    previousNode: null,
  };
};

const getNewGridWithWallToggled = (grid, row, col) => {
  const newGrid = grid.slice();
  const node = newGrid[row][col];
  const newNode = {
    ...node,
    isWall: !node.isWall,
  };
  newGrid[row][col] = newNode;
  return newGrid;
};
export default PathfindingVisualizer;
