import sys

data

def sizeof():
	print("the size of the variable ", data, " is ", sys.getsizeof(data))

data = input("enter data")
sizeof()