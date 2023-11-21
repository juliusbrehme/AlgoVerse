import React from 'react';
import "./PseudoCode.css";
import { useSearchParams } from "react-router-dom";
import pseudoCodeTexts from "./pseudoCodeText.json";

function PseudoCode() {  
  const [searchParams] = useSearchParams();
  const algorithm = searchParams.get("algorithm");

  
  const pseudoCode = pseudoCodeTexts[algorithm] || "Default";


  return (
    <>
        <div className='pseudoCodeContainer'>
          {pseudoCode}
        </div>
    </>
  );
}

export default PseudoCode;

