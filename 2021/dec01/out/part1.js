"use strict";
exports.__esModule = true;
var fs_1 = require("fs");
var numbers = fs_1.readFileSync("./input.txt")
    .toString()
    .split("\n")
    .map(function (x) { return parseInt(x); });
var prev = NaN;
var curr = NaN;
var increments = 0;
for (var i = 0; i < numbers.length; i++) {
    prev = curr;
    curr = numbers[i];
    if (prev && curr && curr > prev) {
        ++increments;
    }
}
console.log("" + increments);
