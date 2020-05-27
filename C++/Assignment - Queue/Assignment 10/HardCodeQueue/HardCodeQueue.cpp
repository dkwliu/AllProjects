#include <iostream>
#include <string>
using namespace std;

class Queue {

	int index = -1; // the index variable represents the empty index at
					// the top of the queue

	char queueArray[128];
public:
	char getChar(int);
	void setChar(int, char);
	void enqueue(char);
	void dequeue();
};

/*************************************************************************************/
// get methods for the enqueue and dequeue methods to interact with the 
// hard coded queue array.
char Queue::getChar(int ind) {
	return queueArray[ind];
}

/*************************************************************************************/
// set methods for the enqueue and dequeue methods to interact with the 
// hard coded queue array.
void Queue::setChar(int ind, char ch) {
	queueArray[ind] = ch;
}

/*************************************************************************************/
// the enqueue method will insert a char to the end, or the top, of the queue.
void Queue::enqueue(char ch) {
	if (index < 127) {
		index++;
		setChar(index, ch);
	}
	else {
		cout << "Out of bound. " << endl;
	}
}

/*************************************************************************************/
// the dequeue method will remove a char element at the front of the queue.
void Queue::dequeue() {
	if (index > -1) {
		for (int i = 1; i <= index; i++) {
			setChar(i - 1, getChar(i));
		}
		index--;
	}
	else {
		cout << "No elements in queue. " << endl;
	}
}

/*************************************************************************************/
// main method
int main()
{
	Queue Q;
	string input;
	int inputLength;

	cout << "**********Hard coded queue array***********" << endl << endl;

	do {
		cout << "Please input string: ";
		cin >> input;
		inputLength = input.length();

		// for loop will enqueue (push) all char in the string into the queue
		for (int i = 0; i < inputLength; i++) {
			Q.enqueue(input.at(i));
		}

		// for loop will print the front char in the string two times, and then
		// dequeue the front char.
		for (int i = 0; i < inputLength; i++) {
			cout << Q.getChar(0) << Q.getChar(0);
			Q.dequeue();
		}

		cout << endl << endl;

	} while (true);

	return 0;
}

