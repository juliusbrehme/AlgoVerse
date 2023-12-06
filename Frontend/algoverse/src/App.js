import "./App.css";
import SideBar from "./components/SideBar";
import SearchBar from "./components/SearchBar";
import Home from "./components/pages/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Pathfinding from "./components/pages/Pathfinding";
import Tree from "./components/pages/Tree";

function App() {
  return (
    <>

      <Router>
        <SearchBar />
        <Routes>
          <Route path="/" exact Component={Home} />
          <Route path="/home" exact Component={Home} />
          <Route path="/pathfinding" exact Component={Pathfinding} />
          <Route path="/tree" exact Component={Tree} />
        </Routes>
        <SideBar />
      </Router>
    </>
  );
}

export default App;
