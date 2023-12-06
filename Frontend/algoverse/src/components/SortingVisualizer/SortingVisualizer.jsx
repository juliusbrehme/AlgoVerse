import React from 'react';
import {getMergeSortAnimations} from '../sortingAlgorithms/mergeSort.jsx';
import {getBubbleSortAnimations} from '../sortingAlgorithms/bubbleSort.jsx';

import './SortingVisualizer.css';

// Change this value for the speed of the animations.
const ANIMATION_SPEED_MS = 30;

// Change this value for the number of bars (value) in the array.
const NUMBER_OF_ARRAY_BARS = 33;

// This is the main color of the array bars.
const PRIMARY_COLOR = 'turquoise';

// This is the color of array bars that are being compared throughout the animations.
const SECONDARY_COLOR = 'red';

export default class SortingVisualizer extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      array: [],
    };
  }

  componentDidMount() {
    this.resetArray();
  }

  resetArray() {
    const array = [];
    for (let i = 0; i < NUMBER_OF_ARRAY_BARS; i++) {
      array.push(randomIntFromInterval(5, 730));
    }
    this.setState({array});
  };

  visualizeAlgorithm() {
    const urlParams = new URLSearchParams(window.location.search);
    const algorithm = urlParams.get('algorithm');
    var animations;

    switch (algorithm) {
      case "MergeSort":
        animations = getMergeSortAnimations(this.state.array);
        break;
      case "SelectionSort":
        animations = getMergeSortAnimations(this.state.array);
        break;
      case "BubbleSort":
        animations = getBubbleSortAnimations(this.state.array);
        break;
      case "InsertionSort":
        animations = getMergeSortAnimations(this.state.array);
        break;
        case "QuickSort":
         animations = getMergeSortAnimations(this.state.array);
        break;
      default:
        break;
    }

    for (let i = 0; i < animations.length; i++) {
      const arrayBars = document.getElementsByClassName('array-bar');
      const isColorChange = i % 3 !== 2;
      if (isColorChange) {
        const [barOneIdx, barTwoIdx] = animations[i];
        const barOneStyle = arrayBars[barOneIdx].style;
        const barTwoStyle = arrayBars[barTwoIdx].style;
        const color = i % 3 === 0 ? SECONDARY_COLOR : PRIMARY_COLOR;
        setTimeout(() => {
          barOneStyle.backgroundColor = color;
          barTwoStyle.backgroundColor = color;
        }, i * ANIMATION_SPEED_MS);
      } else {
        setTimeout(() => {
          const [barOneIdx, newHeight] = animations[i];
          const barOneStyle = arrayBars[barOneIdx].style;
          barOneStyle.height = `${newHeight}px`;
        }, i * ANIMATION_SPEED_MS);
      }
    }
  };

  render() {
    const {array} = this.state;
    const urlParams = new URLSearchParams(window.location.search);
    const algorithm = urlParams.get('algorithm');

    return (
        <> 
        <div className='button-container'>
          <button className="button-accent" onClick={() => this.visualizeAlgorithm()}>
        Visualize {algorithm} Algorithm
        </button>
        <button className="button-reset" onClick={() => this.resetArray()}>
          Reset Array
        </button>
      </div>
        <div className="array-container">
        {array.map((value, idx) => (
          <div
            className="array-bar"
            key={idx}
            style={{
              backgroundColor: PRIMARY_COLOR,
              height: `${value}px`,
            }}></div>
        ))}
        </div>

        
        </>
      
    );
  }
}

// From https://stackoverflow.com/questions/4959975/generate-random-number-between-two-numbers-in-javascript
function randomIntFromInterval(min, max) {
  // min and max included
  return Math.floor(Math.random() * (max - min + 1) + min);
}
