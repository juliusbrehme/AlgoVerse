import React from 'react';
import "./Cell.css"; // Stellen Sie sicher, dass die CSS korrekt verlinkt ist

const Cell = ({ row, col, isWall, onClick }) => {
  // Fügen Sie zusätzliche Klassen basierend auf den Eigenschaften hinzu
  const additionalClass = isWall ? 'cell-wall' : '';

  return (
    <div
      className={`grid-cell ${additionalClass}`}
      data-row={row}
      data-col={col}
      onClick={() => onClick(row, col)}
    />
  );
};

export default Cell;