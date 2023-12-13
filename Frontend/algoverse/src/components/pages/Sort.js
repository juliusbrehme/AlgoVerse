import "../../App.css";
import "./Sort.css";
import React from "react";
import Applications from "../infoContainer/Applications";
import PseudoCode from "../infoContainer/PseudoCode";
import SortingVisualizer from "../SortingVisualizer/Visuals";

function Sort() {
  return (
    <div className="body">
      <div className="cutbody" style={{ marginTop: "250px" }}>
        <SortingVisualizer></SortingVisualizer>
        <div className="infoContainer">
          <Applications></Applications>
          <PseudoCode></PseudoCode>
        </div>
      </div>
    </div>
  );
}

export default Sort;
