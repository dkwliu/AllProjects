// CurrencyConverterConsole.cpp : Defines the entry point for the console application.
//

#include <iostream>

using namespace std;

// The CurrencyConverter class contains the functions for converting
// currencies from USD to the ones listed below
class CurrencyConverter {
public:
	double convertYen(double);
	double convertEuro(double);
	double convertPeso(double);
	void convertAll(double);
};

// function for converting USD to yen
double CurrencyConverter::convertYen(double amount) {
	return amount * 108.7;
}

// function for converting USD to euro
double CurrencyConverter::convertEuro(double amount) {
	return amount * 0.80;
}

// function for converting USD to peso
double CurrencyConverter::convertPeso(double amount) {
	return amount * 18.63;
}

// function for converting USD to all of the
// listed currencies
void CurrencyConverter::convertAll(double amount) {
	cout << "$" << amount << " = " << convertYen(amount) << " yens, " <<
		convertEuro(amount) << " euros, and " << convertPeso(amount) << 
		" pesos.\n" << endl;
}

// main contains the menu for currency conversion
int main()
{
	CurrencyConverter curConv;
	double amount;
	int option;
	bool loop = true;

	do {
		cout << "Input a dollar amount: ";
		cin >> amount;
		cout << "Your amount is: " << amount << endl;


		if (amount > 0) {
			cout << "Convert into: \n"
				"1. Yen\n"
				"2. Euro\n"
				"3. Peso\n"
				"4. All three currencies\n"
				"input option: ";
			cin >> option;

			if (option == 1) {
				cout << "$" << amount << " = " << curConv.convertYen(amount) << " yens.\n" << endl;
			}
			else if (option == 2) {
				cout << "$" << amount << " = " << curConv.convertEuro(amount) << " euros.\n" << endl;
			}
			else if (option == 3) {
				cout << "$" << amount << " = " << curConv.convertPeso(amount) << " pesos.\n" << endl;
			}
			else if (option == 4) {
				curConv.convertAll(amount);
			}
			else {
				cout << "No such option exist.\n" << endl;
			}
		}
		else {
			cout << "Amount of $ is too low.";
			loop = false;
		}
	} while (loop == true);

	return 0;
}

