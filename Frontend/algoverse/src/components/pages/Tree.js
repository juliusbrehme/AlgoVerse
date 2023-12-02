import React, { useEffect } from 'react';
import TreeApp from '../Treesearch/Tree_App';
import "./Tree.css";
import { useNavigate, useSearchParams } from "react-router-dom";
import PseudoCode from "../infoContainer/PseudoCode";
import Applications from "../infoContainer/Applications";

const Tree = () => {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const algorithm = searchParams.get("algorithm");

  useEffect(() => {
    // Check if the algorithm parameter is not present in the URL
    if (!algorithm) {
      // If not present, set it to the default value ("binarytree")
      handleButtonClick("Tree");
    }
  }, [algorithm]); // The effect runs whenever the algorithm parameter changes

  const handleButtonClick = (algorithm) => {
    // Update the URL with the selected algorithm parameter
    navigate(`?algorithm=${algorithm}`);
  };

  return (
    <div className="body">
      <div className="cutbody">
        <TreeApp />
        <div className="infoContainer">
          <Applications></Applications>
          <PseudoCode></PseudoCode>
        </div>
      </div>
    </div>
  );
}

export default Tree;
