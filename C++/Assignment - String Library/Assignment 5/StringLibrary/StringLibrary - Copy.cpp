#include <iostream>

using namespace std;

 /*function that determines if individual characters in a string are english
  alphabet letters, outs the location of non-alphabets, and outputs if the
  string contains any non-alphabetic characters */
void Isalpha(char input[]) {
	
	bool nonAlpha = false;
	int wordLength = 0;

	// Counts the length of string
	do {
		if (int(input[wordLength]) != -52) {
			wordLength++;
			cout << int(input[wordLength]) << endl;
		}
		else {
			wordLength = wordLength - 1;
			break;
		}
	} while (true);

	// Traverses characters in string and find alphabets and non alphabet characters
	// Updates when a char is alphabet or non alphabet
	for (int i = 0; i < wordLength; i++) {
		if (int(input[i]) < 65) {
			if (nonAlpha == false) {
				nonAlpha = true;
			}
			cout << "Non-alphabet char at " << i + 1 << endl;
		}
		else if (int(input[i]) > 122) {
			if (nonAlpha == false) {
				nonAlpha = true;
			}
			cout << "Non-alphabet char at " << i + 1 << endl;
		}
		else if (int(input[i]) < 97 && int(input[i]) > 90) {
			if (nonAlpha == false) {
				nonAlpha = true;
			}
			cout << "Non-alphabet char at " << i + 1 << endl;
		}
		else {
			cout << "Alphabet char at " << i + 1 << endl;
		}
	}

	if (nonAlpha == false) {
		cout << endl << "There are no non-alphabet char(s) in the string." << 
			endl << endl << endl;
	}
	else {
		cout << endl << "There are non-alphabet char(s) in the string." << 
			endl << endl << endl;
	}

}

// function that accepts a string and capitalizes all lowercase char 
void Toupper(char input[]) {
	char word[128];
	int wordLength = 0;

	// Counts the length of string
	do {
		if (int(input[wordLength]) != -52) {
			wordLength++;
		}
		else {
			wordLength = wordLength - 1;
			break;
		}
	} while (true);

	cout << "Output UpperCase: ";

	// Traverses characters in string, then finds alphabet letters and capitalizes them
	for (int i = 0; i < wordLength; i++) {
		if (int(input[i]) >= 97 && int(input[i]) <= 122) {
			word[i] = int(input[i]) - 32;
		}
		else {
			word[i] = input[i];
		}
	}
	
	for (int i = 0; i < wordLength; i++) {
		cout << word[i];
	}

	cout << endl << endl << endl;
}

// function that accepts a string and lowercase all capital char 
void Tolower(char input[]) {
	char word[128];
	int wordLength = 0;

	// Counts the length of string
	do {
		if (int(input[wordLength]) != -52) {
			wordLength++;
		}
		else {
			wordLength = wordLength - 1;
			break;
		}
	} while (true);

	cout << "Output LowerCase: ";

	// Traverses characters in string, then finds alphabet letters and lowercase them
	for (int i = 0; i < wordLength; i++) {
		if (int(input[i]) >= 65 && int(input[i]) <= 90) {
			word[i] = input[i] + 32;
		}
		else {
			word[i] = input[i];
		}
	}

	for (int i = 0; i < wordLength; i++) {
		cout << word[i];
	}

	cout << endl << endl << endl;
}

// accepts a string and then adds a char at the end
void push_back(char input[]) {
	char word[128];
	int wordLength = 0;
	char ch;

	// Counts the length of string
	do {
		if (int(input[wordLength]) != -52) {
			wordLength++;
		}
		else {
			wordLength = wordLength - 1;
			break;
		}
	} while (true);

	cout << "Enter character to add to the end of your word: ";
	cin >> ch;

	for (int i = 0; i < wordLength; i++) {
		word[i] = input[i];
	}

	word[wordLength] = ch;
	wordLength++;

	for (int i = 0; i < wordLength; i++) {
		cout << word[i];
	}

	cout << endl << endl << endl;
}

// main method
int main()
{
	char word[128];
	int option;
	// menu for user to chooes which function to call
	do {
		cout << "Choose string function: " << endl
			<< "1) Isalpha()" << endl << "2) Toupper()" << endl
			<< "3) Tolower()" << endl << "4) push_back" << endl << endl;
		cin >> option;

		if (option == 1) {
			cout << "Calling Isalpha() " << endl;
			cout << "Enter word: ";
			cin >> word;
			cout << endl;
			Isalpha(word);
		}
		else if (option == 2) {
			cout << "Calling Toupper() " << endl;
			cout << "Enter word: ";
			cin >> word;
			cout << endl;
			Toupper(word);
		}
		else if (option == 3) {
			cout << "Calling Tolower() " << endl;
			cout << "Enter word: ";
			cin >> word;
			cout << endl;
			Tolower(word);
		}
		else if (option == 4) {
			cout << "Calling push_back() " << endl;
			cout << "Enter word: ";
			cin >> word;
			cout << endl;
			push_back(word);
		}
		else {
			cout << endl << "Not an option." << endl;
		}
	} while (true);

	return 0;
}

