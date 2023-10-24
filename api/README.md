# API Guide

- [Installation](#installation)
- [Getting Started](#getting-started)
- [API Documentation](#api-guide)
  - [Pathfinding](#pathfinding)
    - [Pathfinding request](#pathfinding-request)
      - [Example request](#example-request)
    - [Random nodes request](#random-nodes-request)
      - [Example request](#example-request-1)
    - [Random maze request](#random-maze-request)
  - [Sorting](#sorting)
    - [Sorting request](#sorting-request)
      - [Example request](#example-request-2)
    - [Random numbers request](#random-numbers-request)
      - [Example request](#example-request-3)
  - [Tree search](#tree-search)
    - [Search request](#search-request)
      - [Example request](#example-request-4)
    - [Create Tree](#create-tree)
      - [Example request](#example-request-5)

## Installation
To use the project gradle needs to be installed. Follow the [Installation Guide](https://gradle.org/install/) of the 
official gradle website. The API is written with the [Spring framework](https://spring.io/) and Java 17 is used.

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
strategy: DIJKSTRA, DFS, BFS, ASTAR (case-insensitive) -> This sets the strategy that is beeing used
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
http://localhost:8080/pathfinding/findpath?startpoint=0,0&endpoint=3,3&size=4,4&wall=2,2&wall=2,3&strategy=dijkstra
```
Here the board size is set to a 4x4 board with the starting point in the upper left corner. The ending point is (3,3) 
and obstacles are found on (2,2) and (2,3). The strategy is set to DIJKSTRA

#### Random nodes request
This request will return two nodes, one is the starting node and one is the end node. The parameter `size: int[]` is 
the size of the board.

##### Example request
The request:
```
localhost:8080/pathfinding/random-nodes?size=10,10
```
will return the following `[{"x":6,"y":4},{"x":6,"y":0}]`, with the integers being randomized.

#### Random maze request
// TODO

### Sorting
The main part of the sorting is to sort a set of numbers (integers). The user can set of numbers or request random
numbers, that should be sorted.
To make a request for sorting, it is always `.../sorting/<depending_on_request>`

#### Sorting request
To sort the input numbers the URL will be `.../sorting/sort?...` and the following
parameters are needed:
```
strategy: SELECTIONSORT, INSERTIONSORT, BUBBLESORT, QUICKSORT, MERGESORT (case-insensitive) 
          -> This sets the strategy that is beeing used
toSort: int[] -> input array of integers
```
The API will return a JSON object with nested arrays, each array after one iteration of the sorting algorithm:
```
[[3,235,52,324,54,23,2,12,234,3],[2,235,52,324,54,23,3,12,234,3],[2,3,52,324,54,23,235,12,234,3],...]
```
Only for Quicksort the last entry of the JSON object will be an array of the used pivot elements used for every sorting 
step. This is done, so that it is possible to highlight the pivot element when visualizing. Second last element will be
the sorted array.

##### Example request
The request:
```
http://localhost:8080/sorting/sort?toSort=3,235,52,324,54,23,2,12,234,3&strategy=selectionsort
```
The input are integers in an array and the output is a nested array, with the last element being the sorted 
array.

#### Random numbers request
This request will return a random list of integers. The parameter `size: int` is the size of the list, that will be
generated.

##### Example request
The request:
```
http://localhost:8080/sorting/random-numbers?size=5
```
will return the following `[43,4,49,15,46]`, with the integers being randomized.

### Tree search
The main part of the tree search is to show how dfs and bfs work in a tree. For convenience, we only work with binary 
balanced trees where elements are inserted from left to right (like a heap). Therefore, it is possible to represent 
a tree as a list with i being the parent, 2*i+1 begin the left node and 2*i+2 being the right node.
Example for a tree representing as a list:
```
          3             
        /   \ 
       5     6      -> the list representation is as follows: [3, 5, 6, 2, 4]
      / \   / \
     2   4
```

The user can give a tree as a list and search for a value or make a request to create a random tree with a given size 
parameter.

#### Search request
The request is made with a URL `.../treesearch/search?...` and the following parameter:
```
tree: int[] -> input array of integers, which equal a tree
element: int -> the value to search in the tree
strategy: BFS, DFS (case-insensetive) -> the strategy used to search for the element
```
The API will return an array of integers. The integers are indexes of the visited elements. So the given list as a tree
should be saved by the user, because the API returns a list of indexes indicating in which sequence the value were 
visited. If there is not a match and the element is not in the tree, the returned list will have the same lenght and the
last element will not be the same as the input element to find. Therefore, the user should check for the last element,
if it is actually not found or just the last looked at element.

##### Example request:
The request:
```
http://localhost:8080/treesearch/search?tree=10,9,30,4,5,6&element=4&strategy=dfs
```
will return the `[0, 1, 3]`. That means, the first visited element was the 10, the second the 9 and the last one the 4.
The element was found, because the list is smaller than the given one. 

#### Create Tree:
The request is made with the URL `.../treesearch/create-tree?...` and the following parameter:
```
size: int -> How many elements the tree should contain
```
The API will return an array of integers representing a tree with the parent on index i, the left node on index 2*i+1 
and the right node on the index 2*i+2.

##### Example request:
The request:
```
http://localhost:8080/treesearch/search?size=10
```
will return a list containing 10 elements and represent a tree.


Idee ist nur balancierte Bäume zu haben und die Bäume von links nach rechts zu 
füllen, damit man es in einer Liste darstellen kann. Hoffentlich einfacher für frontend. Die idee bei search ist es 
dann eine Liste zurückzugeben, die in der Reihenfolge die indezies der elemente in der Liste zurück gibt.
Bsp:
Wir haben folgenden Baum  3            
                        /   \
                       5     6      -> also folgende List [3, 5, 6, 2, 4]
                      / \   / \
                     2   4

Damit wenn wir nun die 4 suchen und das ganze in BFS machen, geben wir folgendes zurück [0, 1, 2, 3, 4]. Also die 
Indizes der nacheinander besuchten Elemente. Bei DFS würde das ganze dann so aussehen: [0, 1, 3, 4].