function mergeSort(array) {
    // Base case: an array of length 0 or 1 is already sorted
    if (array.length <= 1) {
      return array;
    }
  
    // Find the middle index
    const mid = Math.floor(array.length / 2);
  
    // Split the array into two halves
    const left = array.slice(0, mid);
    const right = array.slice(mid);
  
    // Recursively sort both halves
    const sortedLeft = mergeSort(left);
    const sortedRight = mergeSort(right);
  
    // Merge the sorted halves and return the result
    return merge(sortedLeft, sortedRight);
  }
  
  function merge(left, right) {
    let result = [];
    let leftIndex = 0;
    let rightIndex = 0;
  
    // Merge the arrays while there are elements in both
    while (leftIndex < left.length && rightIndex < right.length) {
      if (left[leftIndex] <= right[rightIndex]) {
        result.push(left[leftIndex]);
        leftIndex++;
      } else {
        result.push(right[rightIndex]);
        rightIndex++;
      }
    }
  
    // Concatenate any remaining elements
    return result.concat(left.slice(leftIndex)).concat(right.slice(rightIndex));
  }
  


  mergeSort([2,4,5,1]);