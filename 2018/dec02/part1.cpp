#include <iostream>
#include <fstream>
#include <stdlib.h>
#include <string>
#include <unordered_set>

class characterCounter
{
  private:
    int _counted;
    bool _countReached;

  public:
    characterCounter()
      : _counted(0),
        _countReached(false)
    {}

    void reset() {
      _countReached = false;
    }

    int operator++() {
      ++_counted;
      return _counted;
    }

    bool countReached() const {
      return _countReached;
    }

    void setCountReached(bool status) {
      _countReached = status;
    }

    int count() const {
      return _counted;
    }
};

void setArrayToZeros(int array[], int length)
{
  for (int i = 0; i < length; ++i) {
    array[i] = 0;
  }
}

int main(int argc, char** argv)
{
  if (argc != 2) {
    std::cerr << "Usage: `./part1 <input_file>`\n";
    exit(EXIT_FAILURE); 
  }

  std::ifstream in;
  in.open(argv[1]);

  characterCounter twoCounter;
  characterCounter threeCounter;

  int counts[26];
  setArrayToZeros(counts, 26);

  auto encounteredCharacters = std::unordered_set<char>{};

  std::string row;
  while (in >> row) {
    twoCounter.reset();
    threeCounter.reset();

    for (const char& c : row) {
      int idx = c - 'a';
      ++counts[idx];
      encounteredCharacters.insert(c);
      if (counts[idx] == 3 && !threeCounter.countReached()) {
        ++threeCounter;
        threeCounter.setCountReached(true);
      }
    }

    for (const char& c : encounteredCharacters) {
      int idx = c - 'a';
      if (counts[idx] == 2 && !twoCounter.countReached()) {
        ++twoCounter;
        twoCounter.setCountReached(true);
      }
    }

    setArrayToZeros(counts, 26);
    encounteredCharacters.clear();
    twoCounter.reset();
    threeCounter.reset();
  }

  std::cout << twoCounter.count() * threeCounter.count() << "\n";
}
