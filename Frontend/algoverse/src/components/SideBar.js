import "./SideBar.css";
import Logo from "./Logo.js"
import { Link } from 'react-router-dom';

function SideBar() {  

  const openDropDown = () => {
    alert("I'm an alert");
  }

  return (
    <div className="container">
      <nav className="side-bar">
        <Logo />
        <button className="menu-btn">
            <div className="menu-icon">
                <div className="menu-line"></div>
                <div className="menu-line"></div>
                <div className="menu-line"></div>
            </div>
        </button>
        <ul className="algo-list">
          <li className="algo-items">
            <div className="algo-side-icon">
              <img src="images/PathFinding-img.png" alt="pathfinding-icon" />
            </div>
            <Link to="/Pathfinding">Pathfinding</Link>
            <div className="arrow-container" onClick={openDropDown}>
              <i className="arrow down"></i>
            </div>
          </li>
          <li className="algo-items">
            <div className="algo-side-icon">
              <img src="images/Sorting-img.png" alt="sort-icon" />
            </div>
            <Link to="/Sort">Sorting</Link>
            <div className="arrow-container">
              <i className="arrow down"></i>
            </div>
          </li>
          <li className="algo-items">
            <div className="algo-side-icon tree">
              <img src="images/TreeSearch-img.png" alt="treesearch-icon" />
            </div>
            <Link to="/Tree">Trees</Link>
            <div className="arrow-container">
              <i className="arrow down"></i>
            </div>
          </li>
        </ul>
      </nav>
    </div>
  );
}

export default SideBar;