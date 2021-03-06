#include "stdafx.h"
#include <iostream>

using namespace std;

/**************************************************************************************/
// Node struct. This struct will be the foundation of each node in the doubly linked list. 
struct node {
	int data;
	node *next;
	node *prev;
};


/**************************************************************************************/
// This class will hold the nodes of the doubly linked list, beginning with the head.
struct DoublyLinkedListStack {
	node *head = NULL;
	node *tail = NULL;

	void push(int);
	void pop();
	void iterator();
	bool isEmpty();
	int top();
};

/**************************************************************************************/
// iterator() method prints out the values in each of the nodes in the linked list.
void DoublyLinkedListStack::iterator()
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
void DoublyLinkedListStack::push(int num)
{
	node *element = new node;
	element->data = num;

	if (head == NULL) {
		element->next = tail;
		element->prev = head;
		tail = element;
		head = tail;
	}
	else {
		element->prev = tail;
		element->prev->next = element;
		element->next = NULL;
		tail = element;
	}
}

/**************************************************************************************/
// pop() method removes the node at the top of the doubly linked list.
void DoublyLinkedListStack::pop()
{
	if (isEmpty() == false) {
		tail = tail->prev;

		if (tail == NULL) {
			head = NULL;
		}
		else {
			tail->next = NULL;
		}
	}
}

/**************************************************************************************/
// isEmpty() method checks whether the list is empty. It it is empty, this method
// will return a true;
bool DoublyLinkedListStack::isEmpty()
{
	bool empty = false;

	if (head == NULL) {
		empty = true;
	}

	return empty;
}

/**************************************************************************************/
// top() method (or peek() method) returns the top value of the stack.
int DoublyLinkedListStack::top()
{
	int topVal = NAN;

	if (isEmpty() == false) {
		topVal = tail->data;
		return topVal;
	}
}

/**************************************************************************************/
// main method
int main()
{
	DoublyLinkedListStack DLLS;

	int option;
	int data;

	cout << "OPTIONS" << endl;
	cout << "*******************************************" << endl;
	cout << "1) Push in node" << endl;
	cout << "2) Pop a node from the top of the stack" << endl;
	cout << "3) Return the value of the top node in the stack" << endl;
	cout << "4) Iterate Linked List" << endl;

	do {
		cout << "Choose an option: ";

		cin >> option;

		cout << endl;
		if (option == 1) {
			cout << "Enter an int: ";
			cin >> data;
			DLLS.push(data);
			cout << data << " was added into linked list." << endl << endl;
		}
		else if (option == 2) {
			if (DLLS.isEmpty() == false) {
				cout << DLLS.tail->data << " will be popped from the stack.";
				DLLS.pop();
				cout << endl << endl;
			}
			else {
				cout << "The list is empty" << endl;
				cout << endl;
			}
		}
		else if (option == 3) {
			cout << "Top value is " << DLLS.top() << endl;
			cout << endl;
		}
		else if (option == 4) {
			cout << "Current values in linked list is ";
			DLLS.iterator();
			cout << endl << endl;
		}
		else {
			cout << "Option does not exist." << endl;
		}

	} while (true);

	return 0;
}


