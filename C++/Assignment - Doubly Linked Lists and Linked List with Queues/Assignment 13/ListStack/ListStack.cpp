#include <list>
#include <iostream>
#include <string>

using namespace std;

/**********************************************************************************************/
//Main method. All of this program's functions will be in the main method.
//This program will replicate the stack functions.
//Like the linked list stack example, this program will allow a user to enter a string and
// the string will be printed out in reverse.
int main()
{
	list<char> charList;
	int length;
	string input;

	do {
		cout << "Enter a string: ";
		cin >> input;
		length = input.size();

		for (int i = 0; i < length; i++) {
			charList.push_back(input.at(i));
		}

		cout << "Outputting...  ";

		for (int i = 0; i < length; i++) {
			cout << charList.back();
			charList.pop_back();
		}

		cout << endl << endl;
	} while (true);


	return 0;
}

