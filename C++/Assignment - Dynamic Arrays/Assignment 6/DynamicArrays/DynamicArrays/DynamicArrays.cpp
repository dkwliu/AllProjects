#include "stdafx.h"
#include <iostream>

using namespace std;

int main()
{
	int* oneDInt;        // pointer that will become a dynamic array
	int* oneDIntTemp;
	int size = 1;
	int element;
	oneDInt = new int[size];

	/* The following codes initialize and increase the size 
	of a dynamic array that starts as a pointer. */
	do {
		cout << endl << "Add to array: ";
		cin >> element;
		oneDInt[size - 1] = element;
		oneDIntTemp = new int[size];

		for (int i = 0; i < size; i++) {
			oneDIntTemp[i] = oneDInt[i];
		}

		size = size + 1;
		oneDInt = new int[size];

		for (int i = 0; i < size - 1; i++) {
			oneDInt[i] = oneDIntTemp[i];
		}

		for (int i = 0; i < size - 1; i++) {
			cout << oneDInt[i] << ", " ;
		}
	} while (true);

    return 0;
}

