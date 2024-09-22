N = int(input())

ans = 0
x = 0
tmp = 0

while(True) :
  if x > N :
    break

  x += 1
  tmp = 0
  for i in range(len(str(x))) :
    tmp += int(str(x)[i])
  
  tmp += x

  if(tmp == N) :
    ans = x
    break

print(ans)