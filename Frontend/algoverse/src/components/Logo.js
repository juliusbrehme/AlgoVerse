import React from 'react';
import "./Logo.css";

function Logo() {
  // Import result is the URL of your image
  return (
    <>
      <img 
      src={"images/algo.svg" }
      alt="Logo"
      className={"HomeLogo"}
      />;
    </>
  );
}

export default Logo;

