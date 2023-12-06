export function getBubbleSortAnimations(array) {
  const animations = [];
  if (array.length <= 1) return array;
  const auxiliaryArray = array.slice();
  bubbleSortHelper(array, auxiliaryArray, animations);
  return animations;
}

function bubbleSortHelper(
  mainArray,
  auxiliaryArray,
  animations
) {
  let swapped = true;
  let n = mainArray.length;
  while (swapped) {
    swapped = false;
    for (let i = 0; i < n - 1; i++) {
      // These are the values that we're comparing; we push them once
      // to change their color.
      animations.push([i, i + 1]);
      // These are the values that we're comparing; we push them a second
      // time to revert their color.
      animations.push([i, i + 1]);
      if (auxiliaryArray[i] > auxiliaryArray[i + 1]) {
        // We swap the values at index i and i + 1 in the auxiliary array.
        animations.push([i, auxiliaryArray[i + 1]]);
        animations.push([i + 1, auxiliaryArray[i]]);
        let temp = auxiliaryArray[i];
        auxiliaryArray[i] = auxiliaryArray[i + 1];
        auxiliaryArray[i + 1] = temp;
        swapped = true;
      } else {
        // We do not swap the values, so we push the same values twice.
        animations.push([i, auxiliaryArray[i]]);
        animations.push([i + 1, auxiliaryArray[i + 1]]);
      }
    }
    // We decrement n by 1, since the last element is already sorted.
    n--;
  }
  // We copy the sorted auxiliary array to the main array.
  for (let i = 0; i < mainArray.length; i++) {
    mainArray[i] = auxiliaryArray[i];
  }
}
