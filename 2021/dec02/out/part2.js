"use strict";
exports.__esModule = true;
var fs_1 = require("fs");
var inputs = fs_1.readFileSync("../input.txt")
    .toString()
    .split("\n");
var position = 0;
var depth = 0;
var aim = 0;
for (var _i = 0, inputs_1 = inputs; _i < inputs_1.length; _i++) {
    var input = inputs_1[_i];
    var _a = input.split(" "), command = _a[0], value_str = _a[1];
    var value = parseInt(value_str);
    switch (command) {
        case "forward":
            position += value;
            depth += aim * value;
            break;
        case "down":
            aim += value;
            break;
        case "up":
            aim -= value;
            break;
    }
}
console.log("Position: " + position + ", Depth: " + depth + ", Product: " + position * depth);
