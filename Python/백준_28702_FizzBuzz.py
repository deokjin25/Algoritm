l = []
for i in range(3) :
  l.append(input())

for i in range(3) :
  if(l[i].isdecimal()) :
    x = int(l[i]) + 3-i

    if(x%3 == 0 and x%5 == 0) :
      print('FizzBuzz')
    elif (x%3 == 0) :
      print('Fizz')
    elif(x%5 == 0) :
      print('Buzz')
    else :
      print(x)

    break