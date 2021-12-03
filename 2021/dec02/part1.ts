import { readFileSync } from 'fs';

const inputs: string[] = readFileSync("../input.txt")
  .toString()
  .split("\n")

let position: number = 0
let depth: number = 0

for (let input of inputs) {
  let [command, value] = input.split(" ")
  switch (command) {
    case "forward":
      position += parseInt(value)
      break;
    case "down":
      depth += parseInt(value)
      break;
    case "up":
      depth -= parseInt(value)
      break
  }
}

console.log(`Position: ${position}, Depth: ${depth}, Product: ${position * depth}`)