import { readFileSync } from 'fs';

const inputs: string[] = readFileSync("../input.txt")
  .toString()
  .split("\n")

let position: number = 0
let depth: number = 0
let aim: number = 0

for (let input of inputs) {
  let [command, value_str] = input.split(" ")
  let value: number = parseInt(value_str)
  switch (command) {
    case "forward":
      position += value
      depth += aim * value
      break;
    case "down":
      aim += value
      break;
    case "up":
      aim -= value
      break
  }
}

console.log(`Position: ${position}, Depth: ${depth}, Product: ${position * depth}`)