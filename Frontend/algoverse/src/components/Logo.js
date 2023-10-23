import React from 'react';
import { useNavigate } from 'react-router-dom';
import "./Logo.css";

function Logo() {
  //const navigate = useNavigate();
  //// Import result is the URL of your image
  //const handleImageClick = () => {
  //  // Hier können Sie die gewünschte URL für die Weiterleitung angeben
  //  navigate('/home');
  //}

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

