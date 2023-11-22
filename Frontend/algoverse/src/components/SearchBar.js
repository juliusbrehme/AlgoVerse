import "./SearchBar.css";
import { FaSearch } from "react-icons/fa";

function SearchBar() {
  return (
    <div className="container">
      <div className="search-bar">
        <FaSearch id="search-icon" />
        <input type="text" placeholder="Search" />
      </div>
    </div>
  );
}

export default SearchBar;
