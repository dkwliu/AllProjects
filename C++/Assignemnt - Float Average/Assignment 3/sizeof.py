import sys
print(sys.getsizeof(object))

data = 10
print(type(data))
print(sys.getsizeof(data))

data = 100000
print(type(data))
print(sys.getsizeof(data))

data = 100000000
print(type(data))
print(sys.getsizeof(data))

data = 10.0
print(type(data))
print(sys.getsizeof(data))

data = 100000.5
print(type(data))
print(sys.getsizeof(data))

data = 100000000.5
print(type(data))
print(sys.getsizeof(data))

input()
