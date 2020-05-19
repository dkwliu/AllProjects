#include <iostream>
#include <stack>
#include <string>

using namespace std;

// main method contains all codes
/*****************************************************************************************************/
int main()
{
	stack<char> charStack;
	string input;
	int size;

	do {
		cout << "******* Stack *********" << endl;
		cout << "Enter a word: ";
		cin >> input;
		size = input.length();

		for (int i = 0; i < size; i++) {
			charStack.push(input.at(i));
		}

		cout << "Outputting word in reverse...   ";
		for (int i = 0; i < size; i++) {
			cout << charStack.top();
			charStack.pop();
		}

		cout << endl << endl;
	} while (true);

	return 0;
}

