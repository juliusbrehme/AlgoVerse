import "./SideBar.css";
import Logo from "./Logo.js"
import { Link } from 'react-router-dom';
import React, { useState } from 'react';

function SideBar() {  

  const [isSidebarHidden, setSidebarHidden] = useState(false);
  const [openAccordion, setOpenAccordion] = useState(null);

  const toggleSidebar = () => {
      setSidebarHidden(!isSidebarHidden);
  };
  const toggleAccordion = (e, accordionName) => {
    e.preventDefault();
    if (openAccordion === accordionName) {
        setOpenAccordion(null);
    } else {
        setOpenAccordion(accordionName);
    }
};
  

  return (
    <div className="container">
      <nav className={`side-bar ${isSidebarHidden ? "hidden" : ""}`}>
        <Logo />
        
        <button className="menu-btn" onClick={toggleSidebar}>
          <div className="menu-icon">
            <div className="menu-line"></div>
            <div className="menu-line"></div>
            <div className="menu-line"></div>
          </div>
        </button>
        
        <ul className="algo-list">
          <li className="algo-items">
            <div className="change-flex-dir">
              <div className="algo-side-icon">
                <img src="images/PathFinding-img.png" alt="pathfinding-icon" />
              </div>
              <Link onClick={(e) => toggleAccordion(e, "pathfinding")}>
                Pathfinding
              
              <div className="arrow-container">
                <i
                  className={`arrow ${
                    openAccordion === "pathfinding" ? "up" : "down"
                  }`}
                ></i>
              </div>
              </Link>
            </div>
            <div
              className={`accordion-content ${
                openAccordion === "pathfinding" ? "open" : ""
              }`}
            >
              <div className="vertical-line"></div>
              <ul className="accordion-list">
                <li>
                  <a href="/pathfinding?algorithm=Dijkstra">Dijkstra</a>
                </li>
                <li>
                  <a href="/pathfinding?algorithm=BFS">BFS</a>
                </li>
                <li>
                  <a href="/pathfinding?algorithm=DFS">DFS</a>
                </li>
                <li>
                  <a href="/pathfinding?algorithm=AStar">A*</a>
                </li>
              </ul>
            </div>
          </li>
          <li className="algo-items">
            <div className="change-flex-dir">
              <div className="algo-side-icon">
                <img src="images/Sorting-img.png" alt="sort-icon" />
              </div>
              <Link to="/Sort">Sorting</Link>
              <div className="arrow-container">
                <i className="arrow down"></i>
              </div>
            </div>
          </li>

          <li className="algo-items">
            <div className="change-flex-dir">
              <div className="algo-side-icon tree">
                <img src="images/TreeSearch-img.png" alt="treesearch-icon" />
              </div>
              <Link to="/Tree">Trees</Link>
              <div className="arrow-container">
                <i className="arrow down"></i>
              </div>
            </div>
          </li>
        </ul>
      </nav>
    </div>
  );
}

export default SideBar;