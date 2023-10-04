# API Guide

- [Installation](#installation)
- [Getting Started](#getting-started)
- [API Documentation](#api-guide)
  - [Pathfinding](#pathfinding)
    - [Pathfinding request](#pathfinding-request)
      - [Example request](#example-request)
    - [Random board request](#random-board-request)
    - [Random nodes request](#random-nodes-request)
  - [Sorting](#sorting)
    - [Sorting request](#sorting-request)
      - [Example request](#example-request-1)
    - [Random numbers request](#random-numbers-request)

## Installation
To use the project gradle needs to be installed. Follow the [Installation Guide](https://gradle.org/install/) of the 
official gradle website.

## Getting Started
To start the bootstrap application run ``` gradle bootRun``` or ``` ./gradlew bootRun```. 
Start the browser and the API can be used with ``` localhost:8080```

## API Documentation
To make request to the API the application needs to be running.
For now, we have two types of algorithm implemented, Sorting and Pathfinding. 

### Pathfinding
The main part of the pathfinding is to find a path on a coordinate system. The user can set the starting point,
endpoint, obstacles and the size of the coordinate system. Also, the pathfinding strategy needs to be set.
Left the top corner is the null point (0,0) of the coordinate system. But there are other requests that can be made.

To make a request for pathfinding, it is always `.../pathfinding/<depending_on_request>`
#### Pathfinding request
To find the path between the starting and end node the URL will be `.../pathfinding/findpath?...` and the following 
parameters are needed:
```
strategy: DIJKSTRA (as for now) -> This sets the strategy that is beeing used
startpoint: int[2] -> Sets the starting node on the board
endpoint: int[2] -> Sets the ending node on the baord
wall (optional): int[][2] -> Sets obstacles, nodes that can not be used (if not given, no obstacles are set)
board: int[2] -> The size of the board.
```
The parameters are coordinates so `startpoint=1,2`, means starting point at (1,2). For a wall, it is possible to set more
points in an array like `wall=1,1&wall=3,3&... So a wall would be set at (1,1) and (3,3)


The API will return a JSON object with the parameter `path` and `visitedNodes`:
```
{"path":[{"x":0,"y":0},{"x":1,"y":0},...],"visitedNodes":[{"x":0,"y":0},{"x":1,"y":0},...]}
```
The path is the path from the given starting point to the end point and the visited nodes is a sequence in which the 
different nodes were visited.

##### Example request
The request:
```
http://localhost:8080/pathfinding/findpath?startpoint=0,0&endpoint=3,3&size=4,4&wall=2,2&wall=2,3&strategy=DIJKSTRA
```
Here the board size is set to a 3*3 board with the starting point in the upper left corner. The ending point is (3,3) 
and obstacles are found on (2,2) and (2,3). The strategy is set to DIJKSTRA

#### Random board request
// TODO

#### Random nodes request
// TODO

### Sorting
The main part of the sorting is to sort a set of numbers (integers). The user can set of numbers or request random
numbers, that should be sorted.
To make a request for sorting, it is always `.../sorting/<depending_on_request>`

#### Sorting request
To sort the input numbers the URL will be `.../sorting/sort?...` and the following
parameters are needed:
```
strategy: SELECTIONSORT (as for now) -> This sets the strategy that is beeing used
toSort: int[] -> input array of integers
```
The API will return a JSON object with nested arrays, each array after one iteration of the sorting algorithm:
```
[[3,235,52,324,54,23,2,12,234,3],[2,235,52,324,54,23,3,12,234,3],[2,3,52,324,54,23,235,12,234,3],...]
```

##### Example request
The request:
```
http://localhost:8080/sorting/sort?toSort=3,235,52,324,54,23,2,12,234,3&strategy=SELECTIONSORT
```
The input are integers in an array and the output is a nested array, with the last element beeing the sorted 
array.

#### Random numbers request
// TODO


