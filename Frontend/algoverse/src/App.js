import "./App.css";
import SideBar from "./components/SideBar";
import SearchBar from "./components/SearchBar";
import Home from "./components/pages/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Sorting from "./components/pages/Sort";

function App() {
  return (
    <>
      <Router>
        <SearchBar />
        <Routes>
          <Route path="/" exact Component={Home} />
          <Route path="/home" exact Component={Home} />
          <Route path="/sorting" exact Component={Sorting} />
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
