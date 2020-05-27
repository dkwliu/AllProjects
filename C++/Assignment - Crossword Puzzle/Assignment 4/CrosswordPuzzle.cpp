
#include <iostream>

using namespace std;

// CrosswordPuzzle Class holds the values for the crossword
// puzzle board, as well as the library for all the words
// on the board

class CrosswordPuzzle {

	char puzzle[4][4]{ { 'f', 'k', 's', 'a' },
	{ 'l', 'u', 'o', 'w' } ,{ 'y', 'o', 'n', 'a' } ,
	{ 'x', 't', 'o', 'y' } };

	char wordInput[4];

	char wordLibrary[4][4]{ {'t','o','y'},{ 'f','l','y' } 
	,{ 'f','u','n' } ,{ 's','o','n' } };

public:
	void createPuzzle();
	void wordSearcher();
	void setWordInput(int ind, char ch);
	char getWordInput(int);
	char getLibrary(int, int);
};

// prints out the crossward puzzle board

void CrosswordPuzzle::createPuzzle() {
	for (int i = 0; i < 4; i++) {
		for (int y = 0; y < 4; y++) {
			if (y < 3) {
				cout << puzzle[i][y] << " ";
			}
			else {
				cout << puzzle[i][y] << endl;
			}
		}
	}
}

// wordSearcher method takes a user input and checks it against the
// existing words in the crossword puzzle to check whether the word
// exist

void CrosswordPuzzle::wordSearcher() {
	char input[4];
	int matches = 0;

	cout << "Input a word that you want to find: ";
	cin >> input;

	for (int i = 0; i < 4; i++) {

		matches = 0;

		for (int y = 0; y < 4; y++) {
			if (getLibrary(i, y) == input[y]) {
				matches++;
				setWordInput(y, input[y]);
				if (matches == 4) {
					i = 4;               // used to artificially break the for loop once
					y = 4;				// there is a match
				}
			}
			else {
				matches = 0;
			}
		}
	}
	if (matches == 4) {
		cout << "The word, " << getWordInput(0) << getWordInput(1)
			<< getWordInput(2) << getWordInput(3) << ", exist." << endl << endl;
	}
	else {
		cout << "Your word either does not exist on the crossword puzzle, or is only a part of a full word."
			<< endl << endl;
	}
}

// sets the characters of the user input into its own array

void CrosswordPuzzle::setWordInput(int ind, char ch) {
	wordInput[ind] = ch;
}

// Returns a chosen character of the user input

char CrosswordPuzzle::getWordInput(int ind) {
	return wordInput[ind];
}

// Returns a chosen character of a word in the crossword
//puzzle word library

char CrosswordPuzzle::getLibrary(int ind, int ind2) {
	return wordLibrary[ind][ind2];
}

// main method

int main()
{
	CrosswordPuzzle XP;

	XP.createPuzzle();

	do {
		XP.wordSearcher();
	} while (true);

    return 0;
}

