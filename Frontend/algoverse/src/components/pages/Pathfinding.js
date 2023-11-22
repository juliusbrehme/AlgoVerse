import "../../App.css";
import "./Pathfinding.css";
import React from "react";

import PathfindingVisualizer from "../PathfindingVisualizer/PathfindingVisualizer";
import Applications from "../infoContainer/Applications.js";
import PseudoCode from "../infoContainer/PseudoCode.js";

function Pathfinding() {
  return (
    <div className="body">
      <div className="cutbody">
        <PathfindingVisualizer></PathfindingVisualizer>
        <div className="infoContainer">
          <Applications></Applications> 
          <PseudoCode></PseudoCode>
        </div>
      </div>
    </div>
  );
}

export default Pathfinding;
