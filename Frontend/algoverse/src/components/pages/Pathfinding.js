
import "../../App.css";
import "./Pathfinding.css";
import React from "react";

import PathfindingVisualizer from '../PathfindingVisualizer/PathfindingVisualizer';

function Pathfinding() {
    return (
    <div className="body">
        <div className="cutbody">
            <PathfindingVisualizer></PathfindingVisualizer>
        </div>
    </div>
    );
  }
  
  export default Pathfinding;
  