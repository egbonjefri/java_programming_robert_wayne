var primes = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97];


function bs(arr, target, lo = 0, hi = arr.length - 1){
  if(lo > hi){
    return lo
  }
  let mid = lo + ((lo + hi)/2)
  if(target == mid){
    return mid
  }
  else if(target < mid){
    return bs(arr, target, lo, mid - 1)
  }
  else{
    return bs(arr, target, mid + 1, hi)
  }
}

console.log(bs(primes, 67))