#include <iostream>
#include <string>
#include <queue>

using namespace std;

/***************************************************************************************/
// main method
int main()
{
	queue <char> Q;
	string input;
	int stringLength;

	cout << "***********Queue using the <queue> class*************" << endl << endl;

	do {
		cout << "Please input string: ";
		cin >> input;
		stringLength = input.length();

		// for loop that push() each char in the input string into the queue
		for (int i = 0; i < stringLength; i++) {
			Q.push(input.at(i));
		}

		// for loop that outputs the front char two times, 
		// and then pop() the front char out of the queue
		for (int i = 0; i < stringLength; i++) {
			cout << Q.front() << Q.front();
			Q.pop();
		}

		cout << endl << endl;

	} while (true);

    return 0;
}

