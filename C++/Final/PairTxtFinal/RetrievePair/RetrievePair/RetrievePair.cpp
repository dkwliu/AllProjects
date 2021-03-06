#include "stdafx.h"
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

/***************************************************************************/
//the pairConter struct is the foundation for containers
// that holds key value pairs
struct pairContainer{
	int key;
	char value;
} element; // pairContainer initialized as element

/***************************************************************************/
//table class contains the pairContainer statis array. The static array holds 
// pairContainer structs, each of which contains a key and a value
class table {
	pairContainer pCArray[1000]; // pCArray array contains the parContainers
public:
	void addPair(int,char);
	int hash(int);
	char findChar(int);
};

/***************************************************************************/
//the findChar() method takes a key, or integer as an argument. It then 
// hashes the key and returns its respective array address in order to locate
// the specific key value pair and return the value
char table::findChar(int key) {
	return pCArray[hash(key)].value;
}

/***************************************************************************/
//the addPair() method takes a key and value, or integer and char, as 
// arguments and initializes a struct with aforementioned integer
// and char as a key value pair
void table::addPair(int key, char val) {
	element.key = key;
	element.value = val;
	pCArray[hash(key)] = element;
}

/***************************************************************************/
// very simple hash function that divides any key integers by 100 and returns
// the respective array index address
int table::hash(int key) {
	return key / 100;
}

/***************************************************************************/
// main method
int main()
{
	int var;
	string line;
	table t;
	int num;
	char ch;

	ifstream pairs("1000Pairs.txt");

	while (getline(pairs, line)) {				// while loop sorts all text data into the hash table
		string numstr;
		for (int i = 1; i < line.size(); i++) { // for loop separates the "{,},,", (the useless chars)
			if (line.at(i) == ',') {			// from the line retrieved from the text file
				break;
			}
			numstr = numstr + line.at(i);
		}
		num = atoi(numstr.c_str());				// converts the string numbers in text file to int
		ch = line.at((line.size() - 3));		// picks out the char value in the string
		t.addPair(num, ch);
	}

	// Returning char between 100 - 900
	cout << "Retrieving char from between 100 - 900." << endl;
	cout << "Retrieving char corresponding to 400: " << t.findChar(400) << endl;
	cout << endl;
	// Returning char between 3500 - 5000
	cout << "Retrieving char from between 3500 - 5000." << endl;
	cout << "Retrieving char corresponding to 4300: " << t.findChar(4300) << endl;
	cout << endl;
	// Returning char between 7000 - 9000
	cout << "Retrieving char from between 7000 - 9000." << endl;
	cout << "Retrieving char corresponding to 7700: " << t.findChar(7700) << endl;
	cout << endl;
	// Returning char between 11500 - 15000
	cout << "Retrieving char from between 11500 - 15000." << endl;
	cout << "Retrieving char corresponding to 13500: " << t.findChar(13500) << endl;
	cout << endl;
	// Returning char between 80000 - 100000
	cout << "Retrieving char from between 80000 - 100000." << endl;
	cout << "Retrieving char corresponding to 98900: " << t.findChar(98900) << endl;
	cout << endl;

	cin >> var;
    return 0;
}

