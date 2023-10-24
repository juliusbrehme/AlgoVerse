import "./App.css";
import SideBar from "./components/SideBar";
import SearchBar from "./components/SearchBar";
import Home from "./components/pages/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

function App() {
  return (
    <>
      <Router>
        <SearchBar />
        <Routes>
          <Route path="/" exact Component={Home} />
          <Route path="/home" exact Component={Home} />
        </Routes>
        <SideBar />
      </Router>
    </>
  );
}

export default App;
