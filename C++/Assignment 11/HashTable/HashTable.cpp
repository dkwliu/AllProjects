#include <iostream>
#include <string>
#include <vector>

using namespace std;

/*******************************************************************************/
// KeyValuePair class used to created KeyValuePair objects which contain
// a first and a last name.
class KeyValuePair {
	string first;
	vector<string> last;
public:
	string getFirst();
	vector<string> getLast();
	void addLast(string);
	void setFirst(string);
	void deleteFirst();
	void deleteLast();
	KeyValuePair() {
	}
	KeyValuePair(string f, string l) {
		first = f;
		last.push_back(l);
	}
};

// A get method that returns the key value of a key value pair
string KeyValuePair::getFirst() {
	return first;
}

// a get method that returns the value of a key value pair
vector<string> KeyValuePair::getLast() {
	return last;
}

// a set method that sets the key
void KeyValuePair::setFirst(string f) {
	first = f;
}

// a set method that adds a value to a key, incase a last name value shares the
// same key as another last name value
void KeyValuePair::addLast(string l) {
	last.push_back(l);
}

// A method that deletes the first name, or key, of a key value pair, replacing it with
// "" as a string
void KeyValuePair::deleteFirst() {
	cout << "Key " << first << " deleted" << endl;
	first = "";
}

// A method that deletes the last names, or values, of a key value pair, replacing them with
// "" as a string
void KeyValuePair::deleteLast() {
	int lastLen = last.size();

	for (int i = 0; i < lastLen; i++) {
		cout << "Value " << last.at(i) << " deleted" << endl;
		last.at(i) = "";
	}
}

/*******************************************************************************/
// HashTable class that has an array that will contain the KeyValuePair objects,...
// each of which will hold a key value pair.
class HashTable {
	KeyValuePair hashArray[1000];
public:
	void insert(string, string);
	void Findvalue(string);
	void Delete(string);
	int hash(string);
	HashTable() {
	}
};

// Inserts a key value pair into the hash table
void HashTable::insert(string f, string l) {
	int hashnum = hash(f);

	if (f == hashArray[hashnum].getFirst()) {
		hashArray[hashnum].addLast(l);
	}
	else {
		KeyValuePair kvp(f, l);
		hashArray[hashnum] = kvp;
	}
}

// Finds the value paired to a key
void HashTable::Findvalue(string f) {
	int hashnum = hash(f);
	int size = hashArray[hashnum].getLast().size();

	cout << "Output....   " << endl;
	if (f == hashArray[hashnum].getFirst()) {
		for (int i = 0; i < size; i++) {
			cout << hashArray[hashnum].getLast().at(i) << endl;
		}
	}
	else {
		cout << "Key does not exist" << endl;
	}
}

// Deletes a key value pair
void HashTable::Delete(string f) {
	int hashnum = hash(f);

	if (hashArray[(hashnum)].getFirst() == f) {
		hashArray[(hashnum)].deleteFirst();
		hashArray[(hashnum)].deleteLast();
	}
	else {
		cout << "Key does not exist" << endl;
	}
	
}

// Hash function that hashes the key to a location in the hash table
int HashTable::hash(string f) {
	int hashnum = 0;
	int flength = f.length();

	for (int i = 0; i < flength; i++) {
		hashnum = hashnum + int(f.at(i));
	}

	hashnum = hashnum * int(f.at(0));

	hashnum = hashnum * f.length() / 5000;

	return hashnum;
}

/********************************************************************************/
// main method
int main()
{
	HashTable ht;
	string input;
	int option;

	// Keys and values added into the hash table.
	ht.insert("Jane", "Smith");
	ht.insert("John", "Do");
	ht.insert("Susan", "Collins");
	ht.insert("Bill", "Rodgers");
	ht.insert("Eric", "Jones");
	ht.insert("Bill", "Wright");
	ht.insert("Mike", "Bader");

	
	do {
		cout << "1) Find value" << endl
			<< "2) Delete key-value" << endl
			<< "Choose an option: ";
		cin >> option;
		cout << endl;

		if (option == 1) {
			cout << "Enter a key: ";
			cin >> input;
			ht.Findvalue(input);
		}
		else if (option == 2) {
			cout << "Enter a key: ";
			cin >> input;
			ht.Delete(input);
		}
		else {
			cout << "No such option" << endl;
		}

		cout << endl << endl;

	} while (true);

    return 0;
}

