import "./SideBar.css";
import Logo from "./Logo.js";
import { Link } from "react-router-dom";
import React, { useState } from "react";

function SideBar() {
  const [isSidebarHidden, setSidebarHidden] = useState(false);
  const [openAccordion, setOpenAccordion] = useState(null);

  const toggleSidebar = () => {
    setSidebarHidden(!isSidebarHidden);
  };
  const toggleAccordion = (accordionName) => {
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
              <Link to="/" onClick={() => toggleAccordion("pathfinding")}>
                Pathfinding
              </Link>
              <div className="arrow-container">
                <i
                  className={`arrow ${
                    openAccordion === "pathfinding" ? "up" : "down"
                  }`}
                ></i>
              </div>
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
              <Link to="/" onClick={() => toggleAccordion("sorting")}>
                Sorting
              </Link>
              <div className="arrow-container">
                <i
                  className={`arrow ${
                    openAccordion === "sorting" ? "up" : "down"
                  }`}
                ></i>
              </div>
            </div>
            <div
              className={`accordion-content ${
                openAccordion === "sorting" ? "open" : ""
              }`}
            >
              <div className="vertical-line"></div>
              <ul className="accordion-list">
                <li>
                  <a href="/sorting?algorithm=MergeSort">MergeSort</a>
                </li>
                <li>
                  <a href="/sorting?algorithm=SelectionSort">SelectionSort</a>
                </li>
                <li>
                  <a href="/sorting?algorithm=BubbleSort">BubbleSort</a>
                </li>
                <li>
                  <a href="/sorting?algorithm=InsertionSort">InsertionSort</a>
                </li>
                <li>
                  <a href="/sorting?algorithm=QuickSort">QuickSort</a>
                </li>
              </ul>
            </div>
          </li>

          <li className="algo-items">
            <div className="change-flex-dir">
              <div className="algo-side-icon tree">
                <img src="images/TreeSearch-img.png" alt="treesearch-icon" />
              </div>
              <Link to="/">Trees</Link>
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
/*<
SELECTIONSORT
BUBBLESORT
INSERTIONSORT
MERGESORT
QUICKSORT
*/
export default SideBar;
