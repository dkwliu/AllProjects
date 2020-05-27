
#include <iostream>
#include <cstdio>
#include <cmath>

using namespace std;

/*****************************************************************************************************/
class FloatAverager {

	const static int allArrSize = 1000;
	float floats[allArrSize];
	bool arrayEmpty = true;

public:
	void numGen();
	float getFloats(int);
	void setFloats(int, float);
	void outputNumbers();
	void searchNumber();
	float sumInputs();
	float averageOutput();
	bool isEmpty();

	void quickSort(float[allArrSize], int, int);
	int partition(float[allArrSize], int, int);
};

/*****************************************************************************************************/
//UPDATED
// generates two arrays of 1000 floats and ints respectively between 1 and 1000
void FloatAverager::numGen() {
	for (int i = 0; i < allArrSize; i++) {
		setFloats(i, floor((((float)rand() / RAND_MAX) * 1000) + 1));
	}
	arrayEmpty = false;

	cout << "Array of floats between 1 - 1000 generated." << endl << endl;
}

/*****************************************************************************************************/
// returns a value from a specific index in the float array
float FloatAverager::getFloats(int index)
{
	return floats[index];
}

/*****************************************************************************************************/
// Sets a value into the float array
void FloatAverager::setFloats(int index, float value)
{
	floats[index] = value;
}

/*****************************************************************************************************/
// Returns the sum of the array values
float FloatAverager::sumInputs()
{
	float sum = 0;

	for (int i = 0; i < allArrSize; i++) {
		sum = sum + getFloats(i);
	}

	return sum;
}

/*****************************************************************************************************/
// returns the average of the array values
float FloatAverager::averageOutput()
{
	return sumInputs() / allArrSize;
}


/*****************************************************************************************************/
// returns all the values of the array
void FloatAverager::outputNumbers()
{
	if (arrayEmpty != true) {
		cout << "Outputting all array values ... " << endl;

		for (int i = 0; i < allArrSize; i++) {
			cout << getFloats(i) << endl;
		}

		cout << endl;
	}
	else {
		cout << "There are no values in the array ..." << endl << endl;
	}

}

/*****************************************************************************************************/
//The main quick sort method
void FloatAverager::quickSort(float floatArr[allArrSize], int low, int high) {
	if (low < high) {
		int partInd = partition(floatArr, low, high);

		quickSort(floatArr, low, partInd - 1);
		quickSort(floatArr, partInd + 1, high);
	}
}

/*****************************************************************************************************/
//The quick sort partitioning method
int FloatAverager::partition(float floatArr[allArrSize], int low, int high) {

	float tempNum;

	int pivot = floatArr[high];
	int ind = (low - 1);

	for (int i = low; i <= high - 1; i++) {
		if (floatArr[i] <= pivot) {
			ind++;
			tempNum = floatArr[ind];
			floatArr[ind] = floatArr[i];
			floatArr[i] = tempNum;
		}
	}
	tempNum = floatArr[ind + 1];
	floatArr[ind + 1] = floatArr[high];
	floatArr[high] = tempNum;

	return ind + 1;
}

/*****************************************************************************************************/
// Uses a modified bubble sort to sort the data set before a binary search is performed
// Uses binary search to search for a user input number in the array
void FloatAverager::searchNumber() {

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

	cout << endl << "Executing Quick Sort ..." << endl;
	/*****************************************************************************************************/
	// Recursive quick sort
	quickSort(sortedArray, 0, 999);

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
			index = index + arraySize;
			if (arraySize % 2 != 0) {
				arraySize++;
			}
		}
	} while (true);

	cout << endl << endl;
}

/*****************************************************************************************************/
// helper method that returns a boolean to indicate whether an array is empty
bool FloatAverager::isEmpty() {
	return arrayEmpty == true;
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
			"1) Fill Array" << endl <<
			"2) Return sum of array" << endl <<
			"3) Return average of array" << endl <<
			"4) return values in array" << endl <<
			"5) Perform Binary Search" << endl;

		cin >> option;

		cout << "You chose: " << option << endl << endl;

		if (option == 1) {
			cout << "Filling array..." << endl;
			FA.numGen();
		}
		else {
			if (option == 2 && FA.isEmpty() != true) {
				cout << "The array sum is: " << FA.sumInputs() << endl << endl;
			}
			else if (option == 3 && FA.isEmpty() != true) {
				cout << "The array average is: " << FA.averageOutput() << endl << endl;
			}
			else if (option == 4 && FA.isEmpty() != true) {
				FA.outputNumbers();
			}
			else if (option == 5 && FA.isEmpty() != true) {
				FA.searchNumber();
			}
			else if (option > 5 || option < 1) {
				cout << "No such option exist." << endl;
			}
			else {
				cout << "Array is empty." << endl;
			}
		}
	} while (loopMenu == true);

	return 0;
}

