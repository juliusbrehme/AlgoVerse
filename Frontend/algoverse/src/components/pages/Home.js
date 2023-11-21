/* eslint-disable jsx-a11y/img-redundant-alt */
import "../../App.css";
import "./Home.css";
import React from "react";
import { Link } from "react-router-dom";

function Home() {
  return (
    <div className="home">
      <div className="grid">
        <div className="sorting">
          <Link to="/" style={{ textDecoration: "none" }}>
            <img
              className="image"
              src="images/Sorting-img.png"
              alt="description of image"
              width="200"
              height="200"
            />
            <p className="text">Sorting</p>
          </Link>
        </div>
        <div className="treeSearch">
          <Link to="/" style={{ textDecoration: "none" }}>
            <img
              className="image"
              src="images/TreeSearch-img.png"
              alt="description of image"
              width="200"
              height="220"
            />
            <p className="text">Tree Search</p>
          </Link>
        </div>

        <div className="pathFinding">
          <Link to="/" style={{ textDecoration: "none" }}>
            <img
              className="image"
              src="images/PathFinding-img.png"
              alt="description of image"
              width="200"
              height="200"
            />
            <p className="text">Path Finding</p>
          </Link>
        </div>
      </div>
    </div>
  );
}

export default Home;
