import "../../App.css";
import "./Sort.css";
import React from "react";
import Applications from "../infoContainer/Applications";
import PseudoCode from "../infoContainer/PseudoCode";

function Sort() {
  return (
    <div className="body">
      <div className="cutbody">
        <div className="infoContainer">
          <Applications></Applications>
          <PseudoCode></PseudoCode>
        </div>
      </div>
    </div>
  );
}

export default Sort;
