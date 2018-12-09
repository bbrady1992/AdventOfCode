#include <cstdint>
#include <fstream>
#include <iostream>
#include <list>
#include <string>
#include <sstream>
#include <unordered_map>
#include <vector>

class MarbleGame
{
  private:
    int32_t lastMarble;
    int32_t numberOfPlayers;
    std::list<int32_t> marbles;
    std::list<int32_t>::iterator currentMarble;
    std::unordered_map<int32_t, int64_t> playerScores;
    int32_t winningPlayer;
    int64_t highestScore;

    void incrementCurrentMarble()
    {
      currentMarble++;
      if (currentMarble == marbles.end()) {
        currentMarble = marbles.begin();
      }
    }

    void decrementCurrentMarble()
    {
      currentMarble--;
      if (currentMarble == --marbles.begin()) {
        currentMarble = --marbles.end();
      }
    }

  public:
    MarbleGame(int32_t numberOfPlayers, int32_t lastMarble)
      : numberOfPlayers(numberOfPlayers),
        lastMarble(lastMarble),
        marbles(std::list<int32_t>{0}),
        currentMarble(marbles.begin()),
        playerScores(std::unordered_map<int32_t, int64_t>{}),
        winningPlayer(0),
        highestScore(0)
    {
    }

    void scoringMove(int32_t playerID, int32_t marbleValue)
    {
      playerScores[playerID] += marbleValue;
      for (int32_t moves = 0; moves < 7; ++moves) {
        decrementCurrentMarble();
      }
      playerScores[playerID] += *currentMarble;
      currentMarble = marbles.erase(currentMarble);
      if (playerScores[playerID] > highestScore) {
        highestScore = playerScores[playerID];
        winningPlayer = playerID;
      }
    }

    void nonScoringMove(int32_t marbleValue)
    {
      incrementCurrentMarble();
      incrementCurrentMarble();
      currentMarble = marbles.insert(currentMarble, marbleValue);
    }

    void displayMarbles()
    {
      for (auto itr = marbles.begin(); itr != marbles.end(); ++itr) {
        if (itr == currentMarble) {
          std::cout << "(" << *itr << ") ";
        } else {
          std::cout << *itr << " ";
        }
      }
      std::cout << "\n";
    }

    void run(bool verbose = false)
    {
      int32_t currentPlayer = 0;
      for (int32_t marble = 1; marble < lastMarble + 1; ++marble) {
        if (marble % 23 == 0) {
          scoringMove(currentPlayer, marble);
        } else {
          nonScoringMove(marble);
        }
        currentPlayer = (currentPlayer + 1) % numberOfPlayers;
        //displayMarbles();
      }
    }

    void reportWinner()
    {
      std::cout << "Played a game with " << numberOfPlayers 
        << " players and a last marble worth " << lastMarble
        << " points.\n";
      std::cout << "Winner: Player " << winningPlayer <<  " - "
        << highestScore << " points\n";
    }


};

std::pair<int32_t, int32_t> parseInput(std::string fileName)
{
  auto words = std::vector<std::string>{};  
  std::ifstream in;
  in.open(fileName);
  std::string word;
  while (std::getline(in, word, ' ')) {
    words.push_back(word);
  }
  int32_t numberOfPlayers = std::stoi(words[0]);
  int32_t lastMarble = std::stoi(words[6]);

  return {numberOfPlayers, lastMarble};
}

int main(int argc, char** argv)
{
  if (argc != 2) {
    std::cerr << "Usage: ./solution <input_file>\n";
    exit(1);
  }
  auto gameInput = parseInput(argv[1]);
  const int32_t& numberOfPlayers = gameInput.first;
  const int32_t& lastMarble = gameInput.second;
  MarbleGame game(numberOfPlayers, lastMarble);
  game.run();
  game.reportWinner();

  return 0;
}
