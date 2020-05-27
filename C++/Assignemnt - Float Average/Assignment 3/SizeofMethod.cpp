#include <iostream>

using namespace std;

int main()
{
	bool loop = true;
	int option;

	// variables for holding the different data types
	double doubledata;
	int intdata;
	short shortdata;
	long longdata;
	float floatdata;
	char str;
	wchar_t wt;

	// menu returns the the size of all the mentioned data types 
	do {
		cout << "Find the size of: " << endl <<
			"1) char " << endl << "2) int" << endl <<
			"3) short int" << endl << "4) long int" << endl
			<< "5) float" << endl << "6) double" << endl <<
			"7) wchart-t" << endl;
		cout << "Choose an option" << endl;
		cin >> option;

		if (option == 1) {
			cout << "Enter data: " << endl;
			cin >> str;
			cout << "The size of the char is " << sizeof(str) << endl << endl;
		}
		else if (option == 2) {
			cout << "Enter data: " << endl;
			cin >> intdata;
			cout << "The size of the char is " << sizeof(intdata) << endl << endl;
		}
		else if (option == 3) {
			cout << "Enter data: " << endl;
			cin >> shortdata;
			cout << "The size of the char is " << sizeof(shortdata) << endl << endl;
		}
		else if (option == 4) {
			cout << "Enter data: " << endl;
			cin >> longdata;
			cout << "The size of the char is " << sizeof(longdata) << endl << endl;
		}
		else if (option == 5) {
			cout << "Enter data: " << endl;
			cin >> floatdata;
			cout << "The size of the char is " << sizeof(floatdata) << endl << endl;
		}
		else if (option == 6) {
			cout << "Enter data: " << endl;
			cin >> doubledata;
			cout << "The size of the char is " << sizeof(doubledata) << endl << endl;
		}
		else if (option == 7) {
			cout << "Enter data: " << endl;
			wcin >> wt;
			cout << "The size of the char is " << sizeof(wt) << endl << endl;
		}
		else {
			cout << "No such option." << endl;
		}

	} while (loop = true);

    return 0;
}

