import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { useLocation } from "react-router-dom";
import BubbleSort from "../algorithms/BubbleSort";
import InsertionSort from "../algorithms/InsertionSort";
import QuickSort from "../algorithms/QuickSort";
import MergeSort from "../algorithms/MergeSort";
import SelectionSort from "../algorithms/SelectionSort";

import "./Visuals.css";

function Visuals() {
  const myState = useSelector((state) => state.updateProps);
  const dispatch = useDispatch();
  const color = myState.color;
  const range = myState.range;
  const location = useLocation();

  const useQuery = () => {
    return new URLSearchParams(location.search);
  };

  const query = useQuery();
  const algorithmQueryParam = query.get("algorithm");
  console.log(algorithmQueryParam);

  const changeValues = () => {
    let new_arr = [...myState.values];
    for (let i = 0; i < new_arr.length; i++)
      document.getElementById(i).style.transform = `translateX(${i * 11}px)`;

    dispatch({
      type: "CHANGE_VALUES",
    });
  };

  const handlePlayPause = (play) => {
    if (!myState.play) {
      switch (algorithmQueryParam) {
        case "MergeSort":
          myState.algorithm = "merge";
          break;
        case "BubbleSort":
          myState.algorithm = "bubble";
          break;
        case "InsertionSort":
          myState.algorithm = "insertion";
          break;
        case "QuickSort":
          myState.algorithm = "quick";
          break;
        case "SelectionSort":
          myState.algorithm = "selection";
          break;
        default:
          break;
      }
      document.getElementById("change-btn").disabled = true;
      document.getElementById("change-btn").style.backgroundColor = "grey";
      document.getElementById("play-btn").disabled = true;
      document.getElementById("play-btn").style.backgroundColor = "grey";
    } else {
      return;
    }
    dispatch({
      type: "PLAY_PAUSE",
      _play: play,
    });
  };

  useEffect(() => {
    // This will run only once when the component mounts
    changeValues();
    changeValues();
  }, []);

  useEffect(() => {
    if (!myState.play) {
      document.getElementById("play-btn").disabled = false;
      document.getElementById("play-btn").style.backgroundColor = "#237bc2";
      document.getElementById("change-btn").disabled = false;
      document.getElementById("change-btn").style.backgroundColor = "#2C98F0";
    }
  }, [myState.play]);

  let speed = myState.speed;
  if (myState.algorithm === "selection") speed *= 3;
  else if (myState.algorithm === "merge") speed *= 5;
  else if (myState.algorithm === "quick") speed *= 6;

  return (
    <div className="visuals">
      <div className="button_container">
        <button
          id="play-btn"
          className="button-accent"
          onClick={() => handlePlayPause(true)}
        >
          {algorithmQueryParam
            ? `Visualize ${algorithmQueryParam}`
            : "Visualize Dijkstra's Algorithm"}
        </button>
        <button id="change-btn" className="button-reset" onClick={changeValues}>
          New Array
        </button>
      </div>

      <div className="visualizer">
        {myState.algorithm === "quick" && (
          <div className="legend">
            <div className="legend__lable"></div> Pivot elements
          </div>
        )}
        {
          <div
            className="visual__items"
            style={{ width: `${myState.values.length * 11}px` }}
          >
            {myState.values.map((item) => {
              return (
                <div
                  className="visual__item"
                  key={item[1]}
                  id={item[1]}
                  style={{
                    transition: `${speed / 1000}s linear all`,
                    transform: `translateX(${item[1] * 11}px)`,
                  }}
                >
                  <h4>{item[0]}</h4>
                  <div
                    className="visual"
                    style={{
                      height: `${item[0] * 5.5}px`,
                      backgroundColor: color,
                      width: range < 35 ? "9px" : "7px",
                    }}
                  ></div>
                </div>
              );
            })}
          </div>
        }
      </div>

      <BubbleSort />
      <InsertionSort />
      <MergeSort />
      <QuickSort />
      <SelectionSort />
    </div>
  );
}

export default Visuals;
