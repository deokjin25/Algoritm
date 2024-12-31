a, b = map(int, input().split())

small = min(a,b)
for i in range(small, 0, -1) :
  if(a%i == 0 and b%i == 0) :
    print(i)
    break


tmp = 1

while(True) :
  big = max(a,b)
  big *= tmp

  if(big % small == 0) :
    print(big)
    break
  
  tmp += 1
