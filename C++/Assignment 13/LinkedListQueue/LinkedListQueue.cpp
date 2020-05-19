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
struct DoublyLinkedListQueue {
	node *head = NULL;
	node *tail = NULL;

	void enqueue(char);
	void dequeue();
	bool isEmpty();
	char front();
};

/**************************************************************************************/
// enqueue() method inserts a node holding a data at the top of the linked list.
void DoublyLinkedListQueue::enqueue(char ch)
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
// dequeue() method removes the node at the front of the doubly linked list.
void DoublyLinkedListQueue::dequeue()
{
	if (isEmpty() == false) {
		head = head->next;

		if (head != NULL) {
			head->prev = NULL;
		}
		else {
			tail = NULL;
		}
	}
}

/**************************************************************************************/
// isEmpty() method checks whether the list is empty. It it is empty, this method
// will return a true, and return a false if otherwise.
bool DoublyLinkedListQueue::isEmpty()
{
	bool empty = false;

	if (head == NULL) {
		empty = true;
	}

	return empty;
}

/**************************************************************************************/
// front() method returns the front char of the queue.
char DoublyLinkedListQueue::front()
{
	char frontVal;

	if (isEmpty() == false) {
		frontVal = head->data;
		return frontVal;
	}

}

/**************************************************************************************/
// main method
int main()
{
	DoublyLinkedListQueue DLLQ;

	string data;
	int length;

	do {
		cout << "Enter a string: ";
		cin >> data;

		length = data.size();

		for (int i = 0; i < length; i++) {
			DLLQ.enqueue(data.at(i));
		}

		cout << "Outputting...  ";

		for (int i = 0; i < length; i++) {
			cout << DLLQ.front() << DLLQ.front();
			DLLQ.dequeue();
		}

		cout << endl << endl;
	} while (true);

	return 0;
}


