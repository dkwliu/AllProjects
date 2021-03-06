#include "stdafx.h"
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

/***********************************************************************************/
// base structure of a node
struct node {
	int data;
	struct node* leftChild;
	struct node* rightChild;
};

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
// values will be printed out in sorted order
// Used class materials as reference
void printTree(struct node *root)
{
	if (root != NULL) {
		printTree(root->leftChild);
		cout << root->data << " ";
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
	// retrieves data from text file "10000Ints.txt"

	ifstream myfile("10000Ints.txt");

	string number;
	int num;
	while (myfile >> number) {		// while loop sorts the text data into binary tree
		if (root == NULL && atoi(number.c_str()) != 0) {		
			number.pop_back();
			num = atoi(number.c_str());	// data is converted from string to int
			root = insert(root, num);	// Adds first root node
		}
		else if (number.at(0) == '{') { // condition for when a "{" is detected on a number
			string numstr = "";			// This function gets rid of the "{"
			for (int i = 1; i < number.size(); i++) {
				numstr = numstr + number.at(i);
			}
			numstr.pop_back();
			num = atoi(numstr.c_str());	// data is converted from string to int
			insert(root, num);
		}
		else if (atoi(number.c_str()) != 0) {	// regular if conditional that adds in a node
			number.pop_back();					// to the binary search tree
			num = atoi(number.c_str());	// data is converted from string to int
			insert(root, num);
		}
	}

	printTree(root);

	cin >> var;

	return 0;
}
