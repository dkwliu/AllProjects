import sys

data = 19

def sizeof(v):
	print("the size of the int value, ", v, ", is ", sys.getsizeof(int(v)), " in bytes")
	print("the size of the int value, ", v, ", is ", sys.getsizeof(float(v)), " in bytes")
	print("the size of the int value, ", v, ", is ", sys.getsizeof(long(v)), " in bytes")

sizeof(v = data)
input()