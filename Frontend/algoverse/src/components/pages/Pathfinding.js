
import "../../App.css";
import "./Pathfinding.css";
import React from "react";

import PathfindingVisualizer from '../PathfindingVisualizer/PathfindingVisualizer';
import PathfindingVisualizerNew from '../PathfindingVisualizer/PathfindingVisualizerNew';


function Pathfinding() {
    return (
    <div className="body">
        <div className="pathfindingBody">
            <div className="buttonHeader"></div>
            <div className="cutBody">
                <PathfindingVisualizerNew></PathfindingVisualizerNew>
            </div>
        </div>
    </div>
    );
  }
  
  export default Pathfinding;
  