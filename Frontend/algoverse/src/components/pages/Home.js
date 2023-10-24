/* eslint-disable jsx-a11y/img-redundant-alt */
import "../../App.css";
import "../Home.css";
import React from "react";

function Home() {
  return (
    <div className="home">
      <div className="grid">
        <div className="sorting">
          <img
            src="images/Sorting-img.png"
            alt="description of image"
            width="200"
            height="200"
          />
        </div>
        <div className="treeSearch">
          <img
            src="images/TreeSearch-img.png"
            alt="description of image"
            width="200"
            height="200"
          />
        </div>
        <div className="pathFinding">
          <img
            src="images/PathFinding-img.png"
            alt="description of image"
            width="200"
            height="200"
          />
        </div>
      </div>
    </div>
  );
}

export default Home;
