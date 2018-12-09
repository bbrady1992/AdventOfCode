#include <fstream>
#include <iostream>
#include <string>
#include <vector>

class Tree
{
  private:
    struct Node
    {
      std::vector<int> metadata;
      std::vector<Node*> children;
      Node* parent;

      Node(Node* parent = nullptr)
      : metadata(std::vector<int>{}),
        children(std::vector<Node*>{}),
        parent(parent)
      {}

      ~Node()
      {
        for (auto child : children) {
          delete child;
        }
      }

      int CalculateNodeValue() 
      {
        int nodeValue = 0;
        if (children.size() == 0) {
          for (const auto& metadataField : metadata) {
            nodeValue += metadataField;
          }
        } else {
          for (const auto& metadataField : metadata) {
            int idx = metadataField - 1;
            if (idx > - 1 && idx < children.size()) {
              nodeValue += children[idx]->CalculateNodeValue();
            } 
          }
        }
        return nodeValue;
      }
    };

    Node* root = nullptr;

  public:
    Tree(std::string fileName)
    {
      std::ifstream in(fileName);
      root = CreateNewNode(in, nullptr);
    }

    Node* CreateNewNode(std::ifstream& in, Node* parent)
    {
      Node* newNode = new Node(parent);
      if (parent) {
        parent->children.push_back(newNode);
      }

      int numberOfChildren = 0;
      int numberOfMetadataFields = 0;
      in >> numberOfChildren >> numberOfMetadataFields;

      for (int i = 0; i < numberOfChildren; ++i) {
        CreateNewNode(in, newNode);
      }

      int metadataField = 0;
      for (int i = 0; i < numberOfMetadataFields; ++i) {
        in >> metadataField;
        newNode->metadata.push_back(metadataField);
      }

      return newNode;
    }

    int CalculateTreeValue() const
    {
      return root->CalculateNodeValue();
    }
};


int main(int argc, char** argv)
{
  if (argc != 2) {
    std::cerr << "Usage: ./part2 <input_file>\n";
    exit(1);
  }

  std::cout << "Initializing tree value calculation...\n";
  Tree t(argv[1]);
  std::cout << "Value of tree: " << t.CalculateTreeValue() << "\n";

  return 0;
}
