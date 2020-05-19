
#include <iostream>
#include <cstdio>
#include <ctime>
#include <cmath>

using namespace std;

/*****************************************************************************************************/
class FloatAverager {

	const static int allArrSize = 10000;
	float floats[allArrSize];
	int ints[allArrSize];
	bool arrayEmpty = true;

public:
	void numGen();
	float getFloats(int);
	int getInts(int);
	void setFloats(int, float);
	void setInts(int, int);
	void outputNumbers();
	void searchNumber(int);
	void linearSearch(int);
};

/*****************************************************************************************************/
//UPDATED
// generates two arrays of 100000 floats and ints respectively between 1 and 1000
void FloatAverager::numGen() {
	for (int i = 0; i < allArrSize; i++) {
		setFloats(i, floor((((float)rand() / RAND_MAX) * 1000) + 1));
		setInts(i, floor((int)rand()%1000)  + 1);
	}
	arrayEmpty = false;

	cout << "Array of floats between 1 - 1000 generated." << endl << endl;
	cout << "Array of ints between 1 - 1000 generated." << endl << endl;
}

/*****************************************************************************************************/
// returns a value from a specific index in the float array
float FloatAverager::getFloats(int index)
{
	return floats[index];
}

/*****************************************************************************************************/
// returns a value from a specific index in the int array
int FloatAverager::getInts(int index)
{
	return ints[index];
}

/*****************************************************************************************************/
// Sets a value into the float array
void FloatAverager::setFloats(int index, float value)
{
	floats[index] = value;
}

/*****************************************************************************************************/
// Sets a value into the int array
void FloatAverager::setInts(int index, int value)
{
	ints[index] = value;
}

/*****************************************************************************************************/
// returns all the values of the array
void FloatAverager::outputNumbers()
{
	if (arrayEmpty != true) {
		cout << "Outputting all array values: " << endl;

		for (int i = 0; i < allArrSize; i++) {
			cout << getFloats(i) << "     " << getInts(i) << endl;
		}

		cout << endl;
	}
	else {
		cout << "There are no values in the array." << endl;
	}

}

/*****************************************************************************************************/
// Uses a modified bubble sort to sort the data set before a binary search is performed
// Uses binary search to search for a user input number in the array
void FloatAverager::searchNumber(int option) {

	double duration;
	clock_t timeStart;
	clock_t timeEnd;
	int opt = option;

	float input;
	float numTemp;
	float sortedArray[allArrSize];
	
	bool sortFlag = false;

	int index = 0;
	int arraySize = allArrSize;
	int midpoint = allArrSize;

	cout << "Enter a number to search: ";
	cin >> input;

	for (int i = 0; i < allArrSize; i++) {
		sortedArray[i] = getFloats(i);
	}

	timeStart = clock();		// The timer will start as soon as the sort is executed

	if (opt == 1) {
		cout << endl << "Executing Bubble Sort ..." << endl;
		/*****************************************************************************************************/
		// Modified bubble sort
		do {

			sortFlag = true;

			for (int i = 0; i < allArrSize - 1; i++) {
				if (sortedArray[i] > sortedArray[i + 1]) {
					numTemp = sortedArray[i + 1];
					sortedArray[i + 1] = sortedArray[i];
					sortedArray[i] = numTemp;
					sortFlag = false;
				}
			}
		} while (sortFlag == false);
	}
	else {
		cout << endl << "Executing Insertion Sort ..." << endl;
		/*****************************************************************************************************/
		// Insertion sort
		for (int i = 0; i < allArrSize - 1; i++) {
			if (sortedArray[i] > sortedArray[i + 1]) {
				numTemp = sortedArray[i + 1];

				for (int j = i; j > -1; j = j - 1) {
					if (sortedArray[j] > numTemp) {
						sortedArray[j + 1] = sortedArray[j];
					}
					else {
						sortedArray[j + 1] = numTemp;
						break;
					}
				}
			}
		}
	}

	/*****************************************************************************************************/
	// performs binary search

	cout << "Executing Binary Search ..." << endl;

	do {

		arraySize = arraySize / 2;
		midpoint = index + arraySize - 1;

		if (arraySize == 1 && sortedArray[midpoint] != input) {
			cout << endl << "Your input does not exist in the data set." << endl;
			break;
		}
		else if (sortedArray[midpoint] == input) {
			cout << "The input exist in the array at index: ";
			for (int i = 0; i < allArrSize; i++) {
				if (input == getFloats(i)) {
					cout << i << " ";
				}
			}
			break;
		}
		else if (input < sortedArray[midpoint]) {
			if (arraySize % 2 != 0) {
				arraySize++;
			}
		}
		else if (input > sortedArray[midpoint]) {
			if (arraySize % 2 != 0) {
				arraySize++;
			}
			index = index + arraySize;
		}
	} while (true);

	timeEnd = clock();		// The timer will record the end time once the sort and search is complete

	duration = double (timeEnd - timeStart)/(CLOCKS_PER_SEC/1000);

	cout << endl << "******** Process duration ********** " << endl;
	cout << duration << " milliseconds. " << endl << endl << endl;
}

/*****************************************************************************************************/
// Uses a linear search to search for a user input number in either the float or int array
void FloatAverager::linearSearch(int option) {

	double duration;
	clock_t timeStart;
	clock_t timeEnd;
	int opt = option;
	bool inputExist = false;

	int intInput;
	float floatInput;

	timeStart = clock();		// The timer will start as soon as the sort is executed

	/****************************************************************************************************/
	// Performs a linear search for an int
	if (opt == 1) {

		cout << "Enter an int to search: ";
		cin >> intInput;

		for (int i = 0; i < allArrSize; i++) {
			if (intInput == getInts(i)) {
				inputExist = true;
			}
		}

		if (inputExist == true) {
			cout << "The input exist in the array at index: ";
			for (int i = 0; i < allArrSize; i++) {
				if (intInput == getInts(i)) {
					cout << i << " ";
				}
			}
		}
		else {
			cout << "Input does not exist." << endl;
		}
	}
	else {
		/****************************************************************************************************/
		// Performs a linear search for a float
		cout << "Enter a float to search: ";
		cin >> floatInput;

		cout << "The input exist in the array at index: ....." << endl;

		for (int i = 0; i < allArrSize; i++) {
			if (floatInput == getFloats(i)) {
				inputExist = true;
			}
		}

		if (inputExist == true) {
			cout << "The input exist in the array at index: ";
			for (int i = 0; i < allArrSize; i++) {
				if (floatInput == getFloats(i)) {
					cout << i << " ";
				}
			}
		}
		else {
			cout << "Input does not exist." << endl;
		}
	}

	timeEnd = clock();		// The timer will record the end time once the sort and search is complete

	duration = double(timeEnd - timeStart) / (CLOCKS_PER_SEC / 1000);

	cout << endl << "******** Process duration ********** " << endl;
	cout << duration << " milliseconds. " << endl << endl << endl;

}


/*****************************************************************************************************/
// Main method
int main()
{
	FloatAverager FA;
	bool loopMenu = true;
	int option;

	do
	{
		cout << "Choose an option: " << endl <<
			"1) Generate new random array" << endl <<
			"2) return values in array" << endl <<
			"3) Perform Binary Search with Bubble Sort" << endl <<
			"4) Perform Binary Search with Insertion Sort" << endl <<
			"5) Perform a Linear Search with an int array" << endl <<
			"6) Perform a Linear Search with an float array" << endl;

		cin >> option;

		cout << "You chose: " << option << endl << endl;

		if (option == 1) {
			FA.numGen();
		}
		else if (option == 2) {
			FA.outputNumbers();
		}
		else if (option == 3) {
			FA.searchNumber(1);
		}
		else if (option == 4) {
			FA.searchNumber(2);
		}
		else if (option == 5) {
			FA.linearSearch(1);
		}
		else if (option == 6) {
			FA.linearSearch(2);
		}
		else {
			cout << "No such option exist." << endl;
		}

	} while (loopMenu == true);

	return 0;
}

