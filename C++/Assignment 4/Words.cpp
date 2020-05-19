
#include <string>
#include <iostream>

using namespace std;

//wordhandler uses a string as an argument and counts the number
//of vowels and consonants in the word

void wordhandler(string word) {
	int vowelCount = 0;

	for (int i = 0; i < word.length(); i++) {
		if (word.at(i) == 'a' || word.at(i) == 'e' || word.at(i) == 'i'
			|| word.at(i) == 'o' || word.at(i) == 'u' || word.at(i) == 'y') {
			vowelCount++;
		}
	}

	cout << vowelCount << " vowels and " << word.length() - vowelCount 
		<< " consonants." << endl << endl;
}

// main method

int main()
{
	string word;

	do {
		cout << "Enter a word: " << endl;
		cin >> word;
		wordhandler(word);
	} while (true);

    return 0;
}

