import sys
input = sys.stdin.readline

def earth_worm(i, j, ans) :
  field[i][j] = 2
  q = [(i, j)]
  ans += 1
  while len(q) > 0 :
    cur = q.pop(0)
    x = cur[0]
    y = cur[1]
    for (dx, dy) in [(-1,0), (1,0), (0,1), (0,-1)] :
      nx = x + dx
      ny = y + dy
      if nx >= n or nx < 0 or ny >= m or ny < 0 : continue
      if field[nx][ny] != 1 : continue

      field[nx][ny] = 2
      q.append((nx,ny))

  return ans


T = int(input())

for _ in range(T) :
  n, m, k = map(int, input().split())
  field = [ [0]*m for _ in range(n) ]
  ans = 0
  for _ in range(k) :
    x, y = map(int, input().split())
    field[x][y] = 1

  for i in range(n) :
    for j in range(m) :
      if field[i][j] == 1 :
        ans = earth_worm(i, j, ans)

  print(ans)

