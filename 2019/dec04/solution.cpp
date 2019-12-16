#include <iostream>
#include <cstdlib>
#include <optional>
#include <vector>

bool passesDecreasingCheck(int passwordCandidate) {
  int lastUnitsPlace = 9;
  while (passwordCandidate != 0) {
    int unitsPlace = passwordCandidate % 10;
    passwordCandidate /= 10;
    if (unitsPlace > lastUnitsPlace) {
      return false;
    }
    lastUnitsPlace = unitsPlace;
  }
  return true;
}

bool passesAdjacentDigitsCheck(int passwordCandidate) {
  auto digits = std::vector<int>{};
  auto digitCounts = std::vector<int>{};

  while (passwordCandidate != 0) {
    int unitsPlace = passwordCandidate % 10;
    passwordCandidate /= 10;

    if (digits.empty() || unitsPlace == digits.front()) {
      digits.push_back(unitsPlace);
    } else {
      digitCounts.push_back(digits.size());
      digits.clear();
      digits.push_back(unitsPlace);
    }
  }
  digitCounts.push_back(digits.size());

  for (auto count: digitCounts) {
    if (count == 2) {
      return true;
    }
  }
  return false;
}

bool isValidPassword(int passwordCandidate) {
  return passesDecreasingCheck(passwordCandidate) && passesAdjacentDigitsCheck(passwordCandidate);
}

int main(int argc, char** argv) {
  constexpr int rangeStart = 236491;
  constexpr int rangeEnd = 713787;

  int validPasswords = 0;
  for (int i = rangeStart; i < rangeEnd; ++i) {
    if (isValidPassword(i)) {
      ++validPasswords;
    }
  }
  std::cout << "Valid passwords: " << validPasswords << "\n";
}
