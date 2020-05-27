
#include <cmath>
#include <iostream>

using namespace std;

class FloatAverager {

	float floats[1000];
	bool arrayEmpty = true;

public:
	void floatGen();
	float getFloats(int);
	void setFloats(int, float);
	void handleFloats();
	float sumInputs();
	float averageOutput();
	void outputNumbers();
	void searchNumber();
};

// generates an array of 1000 floats between 1 and 100

void FloatAverager::floatGen() {
	for (int i = 0; i < 1000; i++) {
		setFloats(i, floor((((float)rand() / RAND_MAX) * 100) + 1));
	}
}

// returns a value from a specific index in the array

float FloatAverager::getFloats(int index)
{
	return floats[index];
}

// Sets a value into the array

void FloatAverager::setFloats(int index, float value)
{
	floats[index] = value;
}

/* handles the operations. Runs the methods that generates numbers for the array,
	sum the array values, and average the array values */
void FloatAverager::handleFloats()
{
	floatGen();

	arrayEmpty = false;

	cout << "The sum of the values is: " << sumInputs() << endl;
	cout << "The average of the values is: " << averageOutput() << endl << endl;
}

// Returns the sum of the array values
float FloatAverager::sumInputs()
{
	float sum = 0;

	for (int i = 0; i < 1000; i++) {
		sum = sum + getFloats(i);
	}

	return sum;
}

// returns the average of the array values
float FloatAverager::averageOutput()
{
	return sumInputs() / 1000;
}

// returns all the values of the array
void FloatAverager::outputNumbers()
{
	if (arrayEmpty != true) {
		cout << "Outputting all array values: " << endl;

		for (int i = 0; i < 1000; i++) {
			cout << getFloats(i) << endl;
		}

		cout << endl;
	}
	else {
		cout << "There are no values in the array." << endl;
	}

}

// Uses binary search to search for a user input number in the array
void FloatAverager::searchNumber() {
	
	float input;
	float numTemp;
	float* sortedArray;
	float* sortedArrayTemp;
	bool foundInput = false;

	int arraySize = 1000;
	int midpoint = 1000;

	cout << endl << "Enter a number to search: ";
	cin >> input;

	sortedArray = new float[arraySize];
	
	for (int i = 0; i < 1000; i++) {
		sortedArray[i] = getFloats(i);
	}

	// sorts the random float array from least to greatest
	for (int i = 0; i < 999; i++) {
		for (int j = i + 1; j < 1000; j++) {
			if (sortedArray[i] > sortedArray[j]) {
				numTemp = sortedArray[j];
				sortedArray[j] = sortedArray[i];
				sortedArray[i] = numTemp;
			}
		}
	}

	do {
		midpoint = floor(midpoint / 2);

		if (midpoint == 1) {
			if (input != sortedArray[0] && input != sortedArray[1]) {
				cout << "The input does not exist in the data set." << endl;
			}
			else {
				cout << "The input exist in the array at index: ";
				for (int i = 0; i < 1000; i++) {
					if (input == getFloats(i)) {
						cout << i << " ";
					}
				}

				cout << endl << endl;

				foundInput = true;
			}
		}
		else if (input == sortedArray[midpoint]) {
			cout << "The input exist in the array at index: ";
			for (int i = 0; i < 1000; i++) {
				if (input == getFloats(i)) {
					cout << i << " ";
				}
			}
			
			cout << endl << endl;

			foundInput = true;
		}
		else if (input < sortedArray[midpoint]) {

			arraySize = midpoint - 1;
			midpoint = arraySize;

			sortedArrayTemp = new float[arraySize];

			for (int i = 0; i < arraySize; i++) {
				sortedArrayTemp[i] = sortedArray[i];
			}

			sortedArray = new float[arraySize];

			for (int i = 0; i < arraySize; i++) {
				sortedArray[i] = sortedArrayTemp[i];
			}

		}
		else if (input > sortedArray[arraySize]) {

			arraySize = midpoint;

			sortedArrayTemp = new float[arraySize];

			for (int i = arraySize; i < (2 *arraySize); i++) {
				sortedArrayTemp[i] = sortedArray[i];
			}

			sortedArray = new float[arraySize];

			for (int i = arraySize; i < (2 * arraySize); i++) {
				sortedArray[i] = sortedArrayTemp[i];
			}
		}
	} while (foundInput == false);
}


// Main method
int main()
{
	FloatAverager FA;
	bool loopMenu = true;
	int option;

	do
	{
		cout << "Choose an option: " << endl <<
			"1) return sum and average of array values" << endl <<
			"2) return values in array" << endl <<
			"3) Search for a number in the array" << endl;

		cin >> option;

		cout << "You chose: " << option << endl << endl;

		if (option == 1) {
			FA.handleFloats();
		}
		else if (option == 2) {
			FA.outputNumbers();
		}
		else if (option == 3) {
			FA.searchNumber();
		}
		else {
			cout << "No such option exist." << endl;
		}

	} while (loopMenu == true);

	return 0;
}

