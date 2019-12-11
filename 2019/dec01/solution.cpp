// Compile with g++ -std=c++11 solution.cpp -o calculateFuelRequirement
#include <cstdlib>
#include <fstream>
#include <functional>
#include <iostream>

int part1FuelCalculationFunction(int mass) {
  int fuelRequirement = (mass / 3) - 2;
  return fuelRequirement > 0 ? fuelRequirement : 0;
}

int part2FuelCalculationFunction(int mass) {
  int currentMass = mass;
  int fuelRequirement = 0;
  while (currentMass != 0) {
    currentMass = part1FuelCalculationFunction(currentMass);
    fuelRequirement += currentMass;
  }
  return fuelRequirement;
}

int calculateFuelRequirementSum(const char* inputFile, std::function<int(int)> fuelCalculationFunction) {
  std::ifstream in(inputFile);
  int fuelRequirementSum = 0;
  int currentMass = 0;

  while (in >> currentMass) {
    fuelRequirementSum += fuelCalculationFunction(currentMass);
  }

  return fuelRequirementSum;
}

int main(int argc, char** argv) {
  if (argc != 2) {
    std::cout << "Usage: ./calculateFuelRequirement <input_file>\n";
    exit(1);
  }

  std::cout << "Part 1 solution: " << calculateFuelRequirementSum(argv[1], part1FuelCalculationFunction) << "\n";
  std::cout << "Part 2 solution: " << calculateFuelRequirementSum(argv[1], part2FuelCalculationFunction) << "\n";
}
