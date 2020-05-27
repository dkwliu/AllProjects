#include <iostream>

using namespace std;

/**************************************************************************************/
// Node struct. This struct will be the foundation of each node in the linked list. 
struct node {
	int data;
	node *next;
};


/**************************************************************************************/
// This class will hold the nodes of the linked list, beginning with the head.
struct LinkedList {
	node *head = NULL;

	int length(node *);
	void iterator();
	void push(node **, int);
};

/**************************************************************************************/
// length() method prints the length of the linked list.
int LinkedList::length(node *head)
{
	node *element = new node;
	element = head;
	int length = 0;

	while (element != NULL) {
		length++;
		element = element->next;
	}

	return length;
}

/**************************************************************************************/
// iterator() method prints out the values in each of the nodes in the linked list.
void LinkedList::iterator()
{
	node *element = new node;
	element = head;
	while (element != NULL) {
		cout << element->data << " ";
		element = element->next;
	}
}

/**************************************************************************************/
// push() method inserts a node holding a data at the head of the linked list.
void LinkedList::push(node ** headref, int num)
{
	node *element = new node;
	element->data = num;
	element->next = head;
	*headref = element;
}

/**************************************************************************************/
// main method
int main()
{
	LinkedList LL;

	int option;
	int data;

	cout << "OPTIONS" << endl;
	cout << "*******************************************" << endl;
	cout << "1) Push in node" << endl;
	cout << "2) Length of Linked List" << endl;
	cout << "3) Iterate Linked List" << endl;

	do {
		cout << "Choose an option: ";

		cin >> option;

		cout << endl;
		if (option == 1) {
			cout << "Enter an int: ";
			cin >> data;
			LL.push(&LL.head, data);
			cout << data << " was added into linked list." << endl << endl;
		}
		else if (option == 2) {
			cout << "Current length of linked list is " << LL.length(LL.head);
			cout << endl << endl;
		}
		else if (option == 3) {
			cout << "Current values in linked list is ";
			LL.iterator();
			cout << endl << endl;
		}
		else {
			cout << endl;
			cout << "Option does not exist." << endl;
		}

	} while (true);

	return 0;
}


