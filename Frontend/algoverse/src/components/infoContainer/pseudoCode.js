import React from 'react';
import "./PseudoCode.css";
import { useSearchParams } from "react-router-dom";
import pseudoCodeTexts from "./pseudoCodeText.json";

function PseudoCode() {  
  const [searchParams] = useSearchParams();
  const algorithm = searchParams.get("algorithm");
  
  let pseudoCodeSteps = pseudoCodeTexts[algorithm]?.steps || "Default";

  return (
    <>
        <div className='pseudoCodeContainer'>
          <h1> PseudoCode </h1>
          {pseudoCodeSteps.map((step, index) => (
        <p key={index}>{step}</p>
      ))}
        </div>
    </>
  );
}

export default PseudoCode;

