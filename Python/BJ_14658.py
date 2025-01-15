n, m, l, k = map(int, input().split())
n += 1
m += 1
l += 1

star = [[0] * (m) for _ in range(n)]

for i in range(k) :
  x, y = map(int, input().split())
  star[x][y] = 1

for i in range(n) :
  print(star[i])

ans = 0
for i in range(0, n-l+1) :
  for j in range(0, m-l+1) :
    tmp = 0
    for a in range(i, i+l) :
      for b in range(j, j+l) :
        if star[a][b] == 1 :
          tmp += 1
    ans = max(ans, tmp)

print(k - ans)