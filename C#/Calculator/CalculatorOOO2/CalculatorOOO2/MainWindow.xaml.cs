using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows;
using System.Windows.Input;
using System.Windows.Controls;
using GalaSoft.MvvmLight.CommandWpf;

namespace WPF_calculator
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>

    public partial class MainWindow : Window
    {
        List<string> equation = new List<string>();
        string num = "";
        bool enableOp = false;
        bool solved = false;

        public MainWindow()
        {
            DataContext = this;
            Button1 = "1";
            Button2 = "2";
            Button3 = "3";
            Button4 = "4";
            Button5 = "5";
            Button6 = "6";
            Button7 = "7";
            Button8 = "8";
            Button9 = "9";
            Button0 = "0";
            add = "add";
            subtract = "subtract";
            multiply = "multiply";
            divide = "divide";
            clearBut = "Reset";
            equalBut = "equals";

            ClickOne = new RelayCommand(() => this.One());
            ClickTwo = new RelayCommand(() => this.Two());
            ClickThree = new RelayCommand(() => this.Three());
            ClickFour = new RelayCommand(() => this.Four());
            ClickFive = new RelayCommand(() => this.Five());
            ClickSix = new RelayCommand(() => this.Six());
            ClickSeven = new RelayCommand(() => this.Seven());
            ClickEight = new RelayCommand(() => this.Eight());
            ClickNine = new RelayCommand(() => this.Nine());
            ClickZero = new RelayCommand(() => this.Zero());
            ClickAdd = new RelayCommand(() => this.Add());
            ClickSubtract = new RelayCommand(() => this.Subtract());
            ClickMultiply = new RelayCommand(() => this.Multiply());
            ClickDivide = new RelayCommand(() => this.Divide());
            ClickClearBut = new RelayCommand(() => this.ClearBut());
            ClickEqual = new RelayCommand(() => this.Equal());

            InitializeComponent();
        }

        /********************************************************/
        // button text data binds
        public string Button1 { get; set; }
        public string Button2 { get; set; }
        public string Button3 { get; set; }
        public string Button4 { get; set; }
        public string Button5 { get; set; }
        public string Button6 { get; set; }
        public string Button7 { get; set; }
        public string Button8 { get; set; }
        public string Button9 { get; set; }
        public string Button0 { get; set; }
        public string add { get; set; }
        public string subtract { get; set; }
        public string multiply { get; set; }
        public string divide { get; set; }
        public string clearBut { get; set; }
        public string equalBut { get; set; }

        /********************************************************/
        // button events data binds

        public RelayCommand ClickOne { get; private set; }     
        public RelayCommand ClickTwo { get; private set; }
        public RelayCommand ClickThree { get; private set; }
        public RelayCommand ClickFour { get; private set; }
        public RelayCommand ClickFive { get; private set; }
        public RelayCommand ClickSix { get; private set; }
        public RelayCommand ClickSeven { get; private set; }
        public RelayCommand ClickEight { get; private set; }
        public RelayCommand ClickNine { get; private set; }
        public RelayCommand ClickZero { get; private set; }
        public RelayCommand ClickAdd { get; private set; }
        public RelayCommand ClickSubtract { get; private set; }
        public RelayCommand ClickMultiply { get; private set; }
        public RelayCommand ClickDivide { get; private set; }
        public RelayCommand ClickClearBut { get; private set; }
        public RelayCommand ClickEqual { get; private set; }

        /****************************************************************************************************/
        // buttons clicked will print their corresponding numbers on the calculator screen
        //
        // solved variable indicates whether a previous equation was solved before entering a new number
        // in your equation. If the program detects that an equation have just been solved, then entering a new
        // number will clear all data in the calculator program.
        //
        // enableOp variable determines whether an operator can be entered into your equation, without any
        // numbers entered, the program will not allow an operator to be entered.

        private void One()
        {
            numberHandler("1");
        }

        private void Two()
        {
            numberHandler("2");
        }

        private void Three()
        {
            numberHandler("3");
        }

        private void Four()
        {
            numberHandler("4");
        }

        private void Five()
        {
            numberHandler("5");
        }

        private void Six()
        {
            numberHandler("6");
        }

        private void Seven()
        {
            numberHandler("7");
        }

        private void Eight()
        {
            numberHandler("8");
        }

        private void Nine()
        {
            numberHandler("9");
        }

        private void Zero()
        {
            numberHandler("0");
        }

        /*******************************************************************************************************/
        // Method for helping the number click methods to print out a number on calculator screen
        public void numberHandler(string num)
        {
            if (solved == true)
            {
                clear();
                solved = false;
                NumText.Text += num;
                if (enableOp == false)
                {
                    enableOp = true;
                }
            }
            else
            {
                NumText.Text += num;
                if (enableOp == false)
                {
                    enableOp = true;
                }
            }
        }

        /*******************************************************************************************************/
        // operator buttons, when clicked will print an operator into your equation on your calculator
        //
        // enableOp becomes false after entering an operator, indicating that you cannot enter another operator
        // after one was just entered.
        private void Multiply()
        {
            if (enableOp == true)
            {
                NumText.Text += "x";
                enableOp = false;
            }
        }

        private void Divide()
        {
            if (enableOp == true)
            {
                NumText.Text += "/";
                enableOp = false;
            }
        }

        private void Add()
        {
            if (enableOp == true)
            {
                NumText.Text += "+";
                enableOp = false;
            }
        }

        private void Subtract()
        {
            if (enableOp == true)
            {
                NumText.Text += "-";
                enableOp = false;
            }
        }

        /*************************************************************************************************/
        // Equal button solves the equation
        //
        // The program first converts all text on calculator screen into text. The double strings and operator
        // strings are each put into an element in a list.
        //
        // double strings are converted to doubles when operations are taking place.
        private void Equal()
        {
            // equation will only be solved if the last input is a number and not an operation symbol
            if (enableOp == true)
            {
                solved = true;

                NumText.Text += "=";
                enableOp = false;

                for (int i = 0; i < NumText.Text.Length; i++)
                {
                    if (NumText.Text[i] != 'x' && NumText.Text[i] != '/' && NumText.Text[i] != '+' && NumText.Text[i] != '-'
                        && NumText.Text[i] != '=')
                    {
                        num += NumText.Text[i];
                    }
                    else
                    {
                        equation.Add(num);
                        num = "";
                        equation.Add(NumText.Text[i].ToString());
                    }
                }

                for (int i = 0; i < equation.Count(); i++)
                {
                    if (equation.ElementAt(i) == "x")
                    {
                        equation[i - 1] = (Convert.ToDouble(equation.ElementAt(i - 1)) * Convert.ToDouble(equation.ElementAt(i + 1))).ToString();
                        i--;
                        equation.RemoveAt(i + 1);
                        equation.RemoveAt(i + 1);
                    }
                    else if (equation.ElementAt(i) == "/")
                    {
                        equation[i - 1] = (Convert.ToDouble(equation.ElementAt(i - 1)) / Convert.ToDouble(equation.ElementAt(i + 1))).ToString();
                        i--;
                        equation.RemoveAt(i + 1);
                        equation.RemoveAt(i + 1);
                    }
                }

                for (int i = 0; i < equation.Count(); i++)
                {
                    if (equation.ElementAt(i) == "+")
                    {
                        equation[i - 1] = (Convert.ToDouble(equation.ElementAt(i - 1)) + Convert.ToDouble(equation.ElementAt(i + 1))).ToString();
                        i--;
                        equation.RemoveAt(i + 1);
                        equation.RemoveAt(i + 1);
                    }
                    else if (equation.ElementAt(i) == "-")
                    {
                        equation[i - 1] = (Convert.ToDouble(equation.ElementAt(i - 1)) - Convert.ToDouble(equation.ElementAt(i + 1))).ToString();
                        i--;
                        equation.RemoveAt(i + 1);
                        equation.RemoveAt(i + 1);
                    }
                }

                NumText.Text += equation.ElementAt(0);
            }
        }

        /********************************************************************************************************************************/
        // resets all data
        private void clear()
        {
            NumText.Text = String.Empty;
            enableOp = false;
            equation.Clear();
            num = "";
        }

        private void ClearBut()
        {
            clear();
        }

        private void TextBox_TextChanged(object sender, TextChangedEventArgs e)
        {

        }
    }
}

