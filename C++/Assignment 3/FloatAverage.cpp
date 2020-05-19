// FloatAverage.cpp : Defines the entry point for the console application.
//
#include <iostream>

using namespace std;

class FloatAverager {

	float inputs[100];
	int arraySize;

	public:
	float getInputs(int);
	void setInputs(int, float);
	void handleInput();
	float sumInputs();
	float averageOutput();
	void outputNumbers();
	float largestNum();
	float smallestNum();
};

// returns a value from a specific index in the array

float FloatAverager::getInputs(int index)
{

	return inputs[index];

}

// Sets a value into the array

void FloatAverager::setInputs(int index, float value)
{
	inputs[index] = value;
}

// Handles the entire process of inputting values into the array, summing
// the values, and averaging the values
void FloatAverager::handleInput()
{
	int arrayCounter;
	float value;

	cout << "How many values will be entered (Up to 100)? " << endl;
	cin >> arrayCounter;
	cout << "Input values now:" << endl;

	for (int i = 0; i < arrayCounter; i++) {
		cin >> value;
		setInputs(i, value);
	}

	arraySize = arrayCounter;

	cout << "The sum of the values is: " << sumInputs() << endl;
	cout << "The average of the values is: "<< averageOutput() << endl << endl;
}

// Returns the sum of the array values
float FloatAverager::sumInputs()
{
	float sum = 0;

	for (int i = 0; i < arraySize; i++) {
		sum = sum + getInputs(i);
	}

	return sum;
}

// returns the average of the array values
float FloatAverager::averageOutput()
{
	return sumInputs()/arraySize;
}

// returns all the values of the array
void FloatAverager::outputNumbers()
{
	if (arraySize > 0) {
		cout << "Outputting all array values: " << endl;

		for (int i = 0; i < arraySize; i++) {
			cout << getInputs(i) << endl;
		}

		cout << endl;
	}
	else {
		cout << "There are no values in the array." << endl;
	}

}

// method that returns the largest number in the array
float FloatAverager::largestNum() {

	float largest = getInputs(0);

	for (int i = 1; i < arraySize; i++) {
		if (largest <= getInputs(i)) {
			largest = getInputs(i);
		}
	}

	return largest;
}

// method htat returns the smallest number in the array
float FloatAverager::smallestNum() {

	float smallest = getInputs(0);

	for (int i = 1; i < arraySize; i++) {
		if (smallest >= getInputs(i)) {
			smallest = getInputs(i);
		}
	}

	return smallest;
}

// Main method
int main()
{
	FloatAverager FA;
	bool loopMenu = true;
	int option;

	do
	{
		cout << "Choose an option: "  << endl <<
			"1) return sum and average of array values" << endl <<
			"2) return values in array" << endl <<
			"3) find biggest and smallest value in array" << endl;

		cin >> option;

		cout << "You chose: " << option << endl << endl;

		if (option == 1) {
			FA.handleInput();
		}
		else if (option == 2) {
			FA.outputNumbers();
		}
		else if (option == 3) {
			cout << "The largest number in the array is: " << FA.largestNum() << endl <<
			"The smallest number in the array is: " << FA.smallestNum() << endl << endl;
		}
		else {
			cout << "No such option exist." << endl;
		}

	} while (loopMenu == true);

	return 0;
}

