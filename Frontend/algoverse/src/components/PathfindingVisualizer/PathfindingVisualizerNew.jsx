import React from "react";
import Cell from "./Cell/Cell";
import { useState } from 'react';

import "./PathfindingVisualizerNew.css"; // Stellen Sie sicher, dass die CSS korrekt verlinkt ist

const PathfindingVisualizerNew = () => {
  const rows = 20;
  const cols = 40;
  const [grid, setGrid] = useState(
    new Array(rows).fill(null).map(() => new Array(cols).fill({ isWall: false }))
  );

  // handle click on cell
  const handleCellClick = (row, col) => {
    console.log(`Zelle geklickt: Zeile ${row}, Spalte ${col}`);
    
      // Erstelle eine tiefe Kopie des Grids
      const newGrid = grid.map(row => row.map(cell => ({ ...cell })));
      
      // Toggle the isWall property of the clicked cell
      newGrid[row][col].isWall = !newGrid[row][col].isWall;
      
      // Aktualisiere den Zustand mit dem neuen Grid
      setGrid(newGrid);
  };

  const renderGrid = () => {
    const gridElements = grid.map((rowArray, rowIdx) => {
      return rowArray.map((cell, colIdx) => {
        return (
          <Cell
            key={`${rowIdx}-${colIdx}`}
            row={rowIdx}
            col={colIdx}
            isWall={cell.isWall}
            onClick={() => handleCellClick(rowIdx, colIdx)}
          />
        );
      });
    });
  
    return <div className="grid-container">{gridElements}</div>;
  };


  return renderGrid();
};
export default PathfindingVisualizerNew;
