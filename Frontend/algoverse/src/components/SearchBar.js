import "./SearchBar.css";
import { FaSearch } from "react-icons/fa";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const pages = [
  { name: "Mainpage", path: "/" },
  { name: "Dijkstra", path: "/pathfinding?algorithm=Dijkstra" },
  { name: "pathfinding", path: "/pathfinding" },
  // other pages
];

function SearchBar() {
  const navigate = useNavigate();
  const [inputValue, setInputValue] = useState("");

  const handleChange = (e) => {
    setInputValue(e.target.value);
  };

  const handleSelect = () => {
    const selectedPage = pages.find(
      (page) => page.name.toLowerCase() === inputValue.toLowerCase()
    );
    if (selectedPage) {
      navigate(selectedPage.path);
    }
  };

  const handleKeyDown = (e) => {
    if (e.key === "Enter") {
      handleSelect();
    }
  };

  return (
    <div className="container">
      <div className="search-bar">
        <button
          onClick={handleSelect}
          style={{
            border: "none",
            background: "none",
            marginLeft: "10px",
            marginTop: "5px",
          }}
        >
          <FaSearch />
        </button>
        <input
          className="search-input"
          list="pages"
          value={inputValue}
          onChange={handleChange}
          onBlur={handleSelect}
          onKeyDown={handleKeyDown}
        />
        <datalist id="pages">
          {pages.map((page, index) => (
            <option key={index} value={page.name} />
          ))}
        </datalist>
      </div>
    </div>
  );
}

export default SearchBar;
