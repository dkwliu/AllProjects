#include "stdafx.h"
#include <iostream>

using namespace std;


int main()
{
	char** twoDChar;     // pointer that will become a dynamic 2D array
	char* innerArray;
	char** twoDCharTemp;

	int twoDArraySize = 1;
	int innerArraySize = 3;
	char element;
	twoDChar = new char*[twoDArraySize];

	/* The following codes initialize and increase the size
	of a 2D dynamic array that starts as a pointer. */
	do {
		cout << endl << "Add to array: ";

		innerArray = new char[innerArraySize];

		for (int i = 0; i < innerArraySize; i++) {
			cin >> element;
			innerArray[i] = element;
			cout << innerArray[i] << endl;
		}

		twoDChar[twoDArraySize - 1] = innerArray;
		twoDCharTemp = new char*[twoDArraySize];

		for (int i = 0; i < twoDArraySize; i++) {
			twoDCharTemp[i] = twoDChar[i];
		}

		twoDArraySize = twoDArraySize + 1;
		twoDChar = new char*[twoDArraySize];

		for (int i = 0; i < twoDArraySize - 1; i++) {
			twoDChar[i] = twoDCharTemp[i];
		}

		for (int i = 0; i < twoDArraySize - 1; i++) {
			for (int j = 0; j < innerArraySize; j++) {
				cout << twoDChar[i][j] << " ";
			}
			cout << endl;
		}
	} while (true);

    return 0;
}

