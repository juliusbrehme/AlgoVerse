import "../../App.css";
import "./Sort.css";
import React from "react";
import Applications from "../infoContainer/Applications";

import SortingVisualizer from "../SortingVisualizer/SortingVisualizer";

function Sort() {
  return (
    <div className="body">
      <div className="sortBody">
        <div className="buttonHeader"></div>
        <div className="cutBody">
          <SortingVisualizer></SortingVisualizer>
          <div className="infoContainer">
            <Applications></Applications>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Sort;
