#include <cstdlib>
#include <fstream>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>

std::vector<int64_t> loadIntcode(std::string fileName) {
  auto in = std::ifstream{fileName};
  auto delimitedIntcode = std::string{};
  std::getline(in, delimitedIntcode);

  auto IntcodeElements = std::vector<int64_t>{};

  std::istringstream inStream(delimitedIntcode);
  while (inStream) {
    auto intString = std::string{};
    if (!getline(inStream, intString, ',')) {
      break;
    }
    IntcodeElements.push_back(std::stoi(intString));
  }
  return IntcodeElements;
}

void executeOpcode1(std::vector<int64_t>& Intcode, int opcodeIndex) {
  int firstIndex = Intcode[opcodeIndex + 1];
  int64_t firstValue = Intcode[firstIndex];
  int secondIndex = Intcode[opcodeIndex + 2];
  int64_t secondValue = Intcode[secondIndex];

  int sumIndex = Intcode[opcodeIndex + 3];

  Intcode[sumIndex] = firstValue + secondValue;
}

void executeOpcode2(std::vector<int64_t>& Intcode, int opcodeIndex) {
  int firstIndex = Intcode[opcodeIndex + 1];
  int64_t firstValue = Intcode[firstIndex];
  int secondIndex = Intcode[opcodeIndex + 2];
  int64_t secondValue = Intcode[secondIndex];
  int64_t product = firstValue * secondValue;
  
  int productIndex = Intcode[opcodeIndex + 3];

  Intcode[productIndex] = product;
}

int64_t executeOpcode99(const std::vector<int64_t>& Intcode) {
  return Intcode[0];
}

int64_t executeIntcodeProgram(std::vector<int64_t>& Intcode) {
  int currentOpcodeIndex = 0;
  while (true) {
    int opcode = Intcode[currentOpcodeIndex];
    switch(opcode) {
      case 1:
        executeOpcode1(Intcode, currentOpcodeIndex);
        break;
      case 2:
        executeOpcode2(Intcode, currentOpcodeIndex);
        break;
      case 99:
        return executeOpcode99(Intcode);
        break;
    }
    currentOpcodeIndex += 4;
  }
}

constexpr int64_t DESIRED_OUTPUT = 19690720;

int main(int argc, char** argv) {
  if (argc != 2) {
    std::cout << "Usage: ./IntcodeMachine <input_file>\n";
    exit(1);
  }

  auto Intcode = loadIntcode(argv[1]);
  for (int i = 0; i < 100; ++i) {
    for (int j = 0; j < 100; ++j) {
      auto intcode = Intcode;
      intcode[1] = i;
      intcode[2] = j;
      if (executeIntcodeProgram(intcode) == DESIRED_OUTPUT) {
        std::cout << "Noun: " << i << "\n"
                  << "Verb: " << j << "\n";
        exit(0);
      }
    }
  }
}
