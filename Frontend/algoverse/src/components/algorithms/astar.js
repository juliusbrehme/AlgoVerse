export function astar(grid, startNode, finishNode) {
  const openSet = new PriorityQueue();
  startNode.distance = 0;
  startNode.totalDistance = 0 + heuristic(startNode, finishNode);
  openSet.enqueue(startNode, startNode.totalDistance);

  const visitedNodesInOrder = [];

  while (!openSet.isEmpty()) {
    const currentNode = openSet.dequeue().element;

    if (currentNode.isWall) continue;

    currentNode.isVisited = true;
    visitedNodesInOrder.push(currentNode);

    if (currentNode === finishNode) return visitedNodesInOrder;

    const neighbors = getUnvisitedNeighbors(currentNode, grid);
    for (const neighbor of neighbors) {
      const tempDistance = currentNode.distance + 1; // Assuming each edge has a weight of 1

      if (tempDistance < neighbor.distance) {
        neighbor.distance = tempDistance;
        neighbor.totalDistance = neighbor.distance + heuristic(neighbor, finishNode);
        neighbor.previousNode = currentNode;

        if (!openSet.includes(neighbor)) {
          openSet.enqueue(neighbor, neighbor.totalDistance);
        }
      }
    }
  }

  return visitedNodesInOrder;
}

function heuristic(nodeA, nodeB) {
  // Manhattan distance
  const distX = Math.abs(nodeA.col - nodeB.col);
  const distY = Math.abs(nodeA.row - nodeB.row);
  return distX + distY;
}

// PriorityQueue implementation (simple version)
class PriorityQueue {
  constructor() {
    this.elements = [];
  }

  enqueue(element, priority) {
    this.elements.push({ element, priority });
    this.elements.sort((a, b) => a.priority - b.priority);
  }

  dequeue() {
    return this.elements.shift();
  }

  isEmpty() {
    return this.elements.length === 0;
  }

  includes(element) {
    return this.elements.some(el => el.element === element);
  }
}

function getUnvisitedNeighbors(node, grid) {
  const neighbors = [];
  const {col, row} = node;
  if (row > 0) neighbors.push(grid[row - 1][col]);
  if (row < grid.length - 1) neighbors.push(grid[row + 1][col]);
  if (col > 0) neighbors.push(grid[row][col - 1]);
  if (col < grid[0].length - 1) neighbors.push(grid[row][col + 1]);
  return neighbors.filter(neighbor => !neighbor.isVisited);
}
// This function also remains the same
export function getNodesInShortestPathOrder(finishNode) {
  const nodesInShortestPathOrder = [];
  let currentNode = finishNode;
  while (currentNode !== null) {
    nodesInShortestPathOrder.unshift(currentNode);
    currentNode = currentNode.previousNode;
  }
  return nodesInShortestPathOrder;
}

// getUnvisitedNeighbors remains the same as in your DFS implementation
// getNodesInShortestPathOrder remains the same as well
