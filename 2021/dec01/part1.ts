import { readFileSync } from 'fs';

const numbers: number[] = readFileSync("./input.txt")
  .toString()
  .split("\n")
  .map(x => parseInt(x));

let prev: number = NaN;
let curr: number = NaN;
let increments: number = 0;

for (let i = 0; i < numbers.length; i++) {
  prev = curr;
  curr = numbers[i];
  if (prev && curr && curr > prev) {
    ++increments;
  }
}

console.log(`${increments}`)