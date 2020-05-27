#include <string>
#include <iostream>

using namespace std;

class Stack {
	char stackArray[1000]; // static size array in place of a stack. 
						   // size chosen is arbitrary
	int index = -1;
public:
	void push(char);
	void pop();
	char peek();
	bool empty();
};

/***********************************************************************************************/
// hard coded push method for the static char array
void Stack::push(char ch)
{
	if (index < 1000) {
		index++;
		stackArray[index] = ch;
	}
}

/***********************************************************************************************/
// hard coded pop method for the static char array
void Stack::pop()
{
	if (empty() == false) {
		index--;
	}
}

/***********************************************************************************************/
// hard coded peek, or top, method for the static char array
char Stack::peek()
{
	if (empty() == false) {
		return stackArray[index];
	}
}

/***********************************************************************************************/
// hard coded empty method for the static char array
bool Stack::empty() {

	bool isempty;

	if (index < 0) {
		isempty = true;
	}
	else {
		isempty = false;
	}
	return isempty;
}

/***************************************************************************************************/
//main method
int main()
{
	Stack stk;
	string input;
	int size;

	do {
		cout << "******* Static Array *********" << endl;
		cout << "Enter a word: ";
		cin >> input;
		size = input.length();

		for (int i = 0; i < size; i++) {
			stk.push(input.at(i));
		}

		cout << "Outputting word in reverse...   ";
		for (int i = 0; i < size; i++) {
			cout << stk.peek();
			stk.pop();
		}

		cout << endl << endl;

	} while (true);

	return 0;
}

