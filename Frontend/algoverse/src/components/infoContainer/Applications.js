import React from "react";
import "./Applications.css";
import { useSearchParams } from "react-router-dom";
import algorithmApplicationsTexts from "./applicationsText.json";

function Applications() {
  const [searchParams] = useSearchParams();
  const algorithm = searchParams.get("algorithm");

  let applicationsText =
    algorithmApplicationsTexts[algorithm]?.applications || "Default";

  let bigOText = algorithmApplicationsTexts[algorithm]?.bigO || "Default";

  return (
    <>
      <div className="applicationsContainer">
        <h1> Useful Applications </h1>
        {applicationsText.map((step, index) => (
          <p key={index}>{step}</p>
        ))}
        <h1>Computational Complexity in O Notation</h1>
        <p>{bigOText}</p>
      </div>
    </>
  );
}

export default Applications;
