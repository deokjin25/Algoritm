# 동:1 서:2 남:3 북:4
# 반시계방향: 2 > 3 > 1 > 4
# 시계방향: 2 > 4 > 1 > 3

K = int(input())
l = [list(map(int, input().split())) for _ in range(6)]
a = b = x = y = 0

for i in range(6) :
  direction = l[i][0]
  length = l[i][1]

  if(direction == 1 or direction == 2) :
    x = max(length, x)
  else :
    y = max(length, y)

  if (l[(i+1)%6][0] == 4 and direction == 2 or
     l[(i+1)%6][0] == 1 and direction == 4 or
     l[(i+1)%6][0] == 3 and direction == 1 or
     l[(i+1)%6][0] == 2 and direction == 3) :
    
    a = l[(i+1)%6][1]
    b = length
    
print((x*y - a*b) * K)

  

