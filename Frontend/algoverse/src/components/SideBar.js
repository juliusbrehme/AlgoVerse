import "./SideBar.css";
import Logo from "./Logo.js"
import { Link } from 'react-router-dom';

function SideBar() {  

  const openDropDown = () => {
  }
  return (
    <>
    <div className="container">
      <div className="side-bar" >
        <Logo />
        <div className="algo-container">
          <nav className="algo-nav">
            <ul className="algo-list">
              <li className="algo-items" onClick={openDropDown()}>
              <div className="algo-side-icon">
                  <img 
                  src="images/PathFinding-img.png"
                  alt="pathfinding-icon"
                  height="30px"
                  width="30px"
                  className="path-icon"/>
                </div>
                <Link to="/Pathfinding">Pathfinding</Link>
                <div className="arrow-container">
                  <img 
                  src="images/ArrowDown.png"
                  alt="arrowdown"
                  height="30px"
                  width="30px"
                  className="expand-arrow"></img>
                </div>
              </li>
              <li className="algo-items">
                <div className="algo-side-icon">
                  <img 
                  src="images/Sorting-img.png"
                  alt="sort-icon"
                  height="30px"
                  width="30px"
                  className="sort-icon" />
                </div>
                <Link to="/Sort">Sorting</Link>
                <div className="arrow-container">
                  <img 
                  src="images/ArrowDown.png"
                  alt="arrowdown"
                  height="30px"
                  width="30px"
                  className="expand-arrow"></img>
                </div>
              </li>
              <li className="algo-items">
                <div className="algo-side-icon-tree">
                  <img 
                  src="images/TreeSearch-img.png"
                  alt="treesearch-icon"
                  height="40px"
                  width="30px"
                  className="tree-icon" />
                </div>
                <Link to="/Tree">Trees</Link>
                <div className="arrow-container">
                  <img 
                  src="images/ArrowDown.png"
                  alt="arrowdown"
                  height="30px"
                  width="30px"
                  className="expand-arrow"/>
                </div>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </div>
    </>
  );
}

export default SideBar;
