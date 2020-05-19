#include <string>
#include <iostream>

using namespace std;

/**************************************************************************************/
// Node struct. This struct will be the foundation of each node in the doubly linked list. 
struct node {
	char data;
	node *next;
	node *prev;
};


/**************************************************************************************/
// This class will hold the nodes of the doubly linked list, beginning with the head.
struct DoublyLinkedListStack {
	node *head = NULL;
	node *tail = NULL;

	void push(char);
	void pop();
	bool isEmpty();
	char top();
};

/**************************************************************************************/
// push() method inserts a node holding a data at the top of the doubly linked list stack.
void DoublyLinkedListStack::push(char ch)
{
	node *element = new node;
	element->data = ch;

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
// pop() method removes the node at the top of the doubly linked list stack.
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
// isEmpty() method checks whether the linked list is empty. If it is empty, this method
// will return a true, or otherwise return a false.
bool DoublyLinkedListStack::isEmpty()
{
	bool empty = false;

	if (head == NULL) {
		empty = true;
	}

	return empty;
}

/**************************************************************************************/
// top() method (or peek() method) returns the top char of the stack.
char DoublyLinkedListStack::top()
{
	char topVal;

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

	string data;
	int length;

	do {
		cout << "Enter a string: ";
		cin >> data;

		length = data.size();

		for (int i = 0; i < length; i++) {
			DLLS.push(data.at(i));
		}

		cout << "Outputting...  ";

		for (int i = 0; i < length; i++) {
			cout << DLLS.top();
			DLLS.pop();
		}

		cout << endl << endl;

	} while (true);

	return 0;
}


