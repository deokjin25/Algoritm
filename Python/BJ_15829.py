#ord('z')-96
L = int(input())
x = input()

h = 0
for i in range(L) :
  h += (ord(x[i])-96) * (31**i)
  h %= 1234567891

print(h)
