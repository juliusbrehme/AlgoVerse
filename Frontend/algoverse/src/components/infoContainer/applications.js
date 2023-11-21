import React from 'react';
import "./Applications.css";
import { useSearchParams } from "react-router-dom";
import algorithmTexts from "./applicationsText.json"


function Applications() {
  const [searchParams] = useSearchParams();
  const algorithm = searchParams.get("algorithm");

  
  const applicationsText = algorithmTexts[algorithm] || "Default";


  return (
    <>
        <div className='applicationsContainer'>
          {applicationsText}
        </div>
    </>
  );
}

export default Applications;

