#include <fstream>
#include <iostream>
#include <stdlib.h>
#include <string>
#include <vector>

int main(int argc, char** argv) {
  if (argc != 2) {
    std::cerr << "Usage: `./part2 <input_file>`\n";
    exit(EXIT_FAILURE);
  }
  std::ifstream in;
  in.open(argv[1]);

  std::string word;
  auto words = std::vector<std::string>{};
  while (in >> word) {
    words.push_back(word);
  } 

  for (int i = 0; i < words.size(); ++i) {
    const std::string& currentWord = words[i];
    for (int j = i + 1; j < words.size(); ++j) {
      const std::string& comparisonWord = words[j];

      int numberOfDifferences = 0;
      int differenceIndex = -1;
      for (int k = 0; k < currentWord.size(); ++k) {
        if (currentWord[k] != comparisonWord[k]) {
          ++numberOfDifferences;
          differenceIndex = k;
        }
      }

      if (numberOfDifferences == 1) {
        std::cout << "SUCCESS\n"; 
        std::cout << words[i].erase(differenceIndex, 1) << "\n";
        exit(EXIT_SUCCESS);
      }

      numberOfDifferences = 0;
    }
  }
  
}
