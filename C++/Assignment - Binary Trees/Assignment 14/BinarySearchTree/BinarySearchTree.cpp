#include <iostream>

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
// Passes a pointer to the root of a binary search tree 
// and then traverses and counts each branch, returning the 
// max depth
int maxDepth(struct node* node)
{
	int leftCount;
	int rightCount;

	if (node == NULL) {
		return 0;
	}
	else {
		leftCount = maxDepth(node->leftChild);
		rightCount = maxDepth(node->rightChild);

		if (leftCount > rightCount) {
			return leftCount + 1;
		}
		else {
			return rightCount + 1;
		}
	}
}

/***********************************************************************************/
// Passes a pointer to the root of a binary search tree 
// and then traverses and counts each branch, returning the smallest depth
int minValue(struct node* node) {

	int leftCount;
	int rightCount;

	if (node == NULL) {
		return 0;
	}
	else {
		leftCount = minValue(node->leftChild);
		rightCount = minValue(node->rightChild);

		if (leftCount < rightCount) {
			return leftCount + 1;
		}
		else {
			return rightCount + 1;
		}
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
// Passes a pinter to the root of a binary search tree 
// and then traverses and counts then number of nodes in tree
// Used class materials as reference
int size(struct node* node)
{
	int count = 0;

	if (node == NULL) {
		return 0;
	}
	else {
		count = size(node->leftChild) + 1 + size(node->rightChild);
		return count;
	}
}

/***********************************************************************************/
// build123() is a test method that passes a root pointer to, and then calls, 
// insert() method three times to build the tree
void build123() {
	struct node *test = NULL;
	test = insert(test, 3);
	test = insert(test, 2);
	test = insert(test, 1);

	printTree(test);
}

/***********************************************************************************/
// main method
int main()
{
	int var;
	struct node *root = NULL;

	//builds a test binary search tree to demonstrate that the
	// insert method works
	build123();

	cout << endl;
	cout << endl;
	cout << endl;

	// Inserted nodes and data for the purpose of this assignment
	root = insert(root, 40);
	insert(root, 15);
	insert(root, 65);
	insert(root, 26);
	insert(root, 55);
	insert(root, 154);
	insert(root, 160);
	insert(root, 166);

	printTree(root);

	// Tests the lookup method by checking whether the integers 50 and 20
	// are in the list. 50 is not in the list, but 26 is in the list.
	cout << "executing Lookup method for the integer, 50. " << endl;
	Lookup(root, 50);
	cout << "executing Lookup method for the integer, 26. " << endl;
	Lookup(root, 26);

	cout << "Size of tree is: " << size(root) << endl;
	cout << "Max depth is: " << maxDepth(root) << endl;
	cout << "Minimum depth is: " << minValue(root) << endl;

	cin >> var;

	return 0;
}

