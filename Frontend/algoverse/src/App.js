import "./App.css";
import SideBar from "./components/SideBar";
import SearchBar from "./components/SearchBar";
import Home from "./components/pages/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Pathfinding from "./components/pages/Pathfinding";

function App() {
  return (
    <>

      <Router>
        <SearchBar />
        <Routes>
          <Route path="/" exact Component={Home} />
          <Route path="/home" exact Component={Home} />
          <Route path="/pathfinding" exact Component={Pathfinding} />
        </Routes>
        <SideBar />
      </Router>
      <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
      >
          Learn React
      </a>
    </>
  );
}

export default App;
