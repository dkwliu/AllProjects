#include <iostream>
#include <fstream>
#include <string>
#include <chrono>

using namespace std;

/***********************************************************************************/
// base structure of a node
struct node {
	int data;
	struct node* leftChild;
	struct node* rightChild;
};

/***********************************************************************************/
// if the binary tree is not empty, recurs through
// the tree by comparing target data with nodes data and
// then deciding whether to go down right or left child
// until target data is determined to be found on a node
// Used class materials as reference
void Lookup(struct node* node, int target) {

	if (node == NULL) {
		cout << target << " Does not exist" << endl;
	}
	else {
		if (target == node->data) {
			cout << target << " Exist" << endl;
		}
		else {
			if (target < node->data) {
				return Lookup(node->leftChild, target);
			}
			else {
				return Lookup(node->rightChild, target);
			}
		}
	}
}

/***********************************************************************************/
// makes a new node with a data point
// as well as left and right child node references
// set to NULL
// Used class materials as reference
struct node* newNode(int data) {
	struct node* node = new struct node;
	node->data = data;
	node->leftChild = NULL;
	node->rightChild = NULL;

	return node;
}

/***********************************************************************************/
// inserts new node into a tree. Method recursively checks, sorts, and inserts
// a new node into the correct location on the binary search tree.
// Used class materials as reference
struct node* insert(struct node* node, int data) {
	if (node == NULL) {
		return newNode(data);
	}
	else {
		if (data <= node->data) {
			node->leftChild = insert(node->leftChild, data);
		}
		else {
			node->rightChild = insert(node->rightChild, data);
		}
		return node;
	}
}

/***********************************************************************************/
// Passes a pinter to the root of a binary search tree 
// and then traverses and prints out the data in each node recursively
// starting from the left node branch
// Used class materials as reference
void printTree(struct node *root)
{
	if (root != NULL) {
		printTree(root->leftChild);
		cout << root->data << endl;
		printTree(root->rightChild);
	}
}

/***********************************************************************************/
// main method
int main()
{
	int var;
	struct node *root = NULL;

	/*******************************************************************/
	// retrieves data from text file

	ifstream myfile("10000Ints.txt");

	string number;
	int num;
	while (myfile >> number) {
		if (root == NULL) {
			number.pop_back();
			num = atoi(number.c_str());
			root = insert(root, num);
		} else if (number.at(0) == '{') {
			string numstr = "";
			for (int i = 1; i < number.size(); i++) {
				numstr = numstr + number.at(i);
			}
			numstr.pop_back();
			num = atoi(numstr.c_str());
			insert(root,num);
		} else if(atoi(number.c_str()) != 0) {
			number.pop_back();
			num = atoi(number.c_str());
			insert(root, num);
		}
	}

	printTree(root);

	cout << endl;
	
	// Testing the lookup() method 3 times to see how long it takes each time it runs
	// for a lookup

	// run 1
	auto start = chrono::high_resolution_clock::now();
	cout << "executing Lookup method for the integer, 71020. " << endl;
	Lookup(root, 71020);
	auto finish = chrono::high_resolution_clock::now();
	cout << chrono::duration_cast<std::chrono::nanoseconds>(finish - start).count() << "ns" << endl << endl;

	// run 2
	auto start2 = chrono::high_resolution_clock::now();
	cout << "executing Lookup method for the integer, 78250. " << endl;
	Lookup(root, 78250);
	auto finish2 = chrono::high_resolution_clock::now();
	cout << chrono::duration_cast<std::chrono::nanoseconds>(finish2 - start2).count() << "ns" << endl << endl;
	
	// run 3
	auto start3 = chrono::high_resolution_clock::now();
	cout << "executing Lookup method for the integer, 503400. " << endl;
	Lookup(root, 503400);
	auto finish3 = chrono::high_resolution_clock::now();
	cout << chrono::duration_cast<std::chrono::nanoseconds>(finish3 - start3).count() << "ns" << endl << endl;
	cin >> var;

	return 0;
}
