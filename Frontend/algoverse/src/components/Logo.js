import React from 'react';
import "./Logo.css";
import { Link } from 'react-router-dom';


function Logo() {
  return (
    <>
      <Link to="/home">
        <img
          src={"images/algo.svg"}
          alt="Logo"
          className={"HomeLogo"}
        />
      </Link>
    </>
  );
}

export default Logo;

