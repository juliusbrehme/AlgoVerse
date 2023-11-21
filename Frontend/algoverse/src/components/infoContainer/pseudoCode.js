import React from "react";
import "./PseudoCode.css";
import { useSearchParams } from "react-router-dom";
import pseudoCodeTexts from "./pseudoCodeText.json";

function PseudoCode() {
  const [searchParams] = useSearchParams();
  const algorithm = searchParams.get("algorithm");

  let pseudoCodeSteps = pseudoCodeTexts[algorithm]?.steps || "Default";

  function renderStep(step, level = 0) {
    // Check if the step is an array
    if (Array.isArray(step)) {
      return (
        <ul key={level} className="nestedList">
          {step.map((subStep, index) => renderStep(subStep, level + 1))}
        </ul>
      );
    } else {
      return (
        <li key={level} style={{ marginLeft: `${level * 20}px` }}>{step}</li>
      );
    }
  }

  return (
    <>
      <div className="pseudoCodeContainer">
        <h1> PseudoCode </h1>
        <ul>
          {pseudoCodeSteps.map((step, index) => renderStep(step, 0))}
        </ul>
      </div>
    </>
  );
}

export default PseudoCode;
