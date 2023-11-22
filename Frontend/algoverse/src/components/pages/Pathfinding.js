import "../../App.css";
import "./Pathfinding.css";
import React from "react";

import PathfindingVisualizer from "../PathfindingVisualizer/PathfindingVisualizer";
import PseudoCode from "../infoContainer/PseudoCode";
import Applications from "../infoContainer/Applications";

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
