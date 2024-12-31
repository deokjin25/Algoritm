# 빈 격자는 3으로 visit처리하고 bfs 수행 시에 가장자리에 닿으면 아무것도 하지 않음
# 가장 자리에 못 닿으면 3으로 처리한 visit을 그대로 옮겨 붙임
# 치즈는 격자마다 방향배열로 외부공기 접촉면 확인해서 2로 바꾸고
# 시뮬 끝나면 2랑 3을 0으로 치환, 다 0 될때까지 반복
import copy

def bfs(i, j, airvisit, w) :
  is_inside = True
  q = [[i,j]]
  airvisit[i][j] = w

  while q :
    cur = q.pop(0)
    x = cur[0]
    y = cur[1]

    for k in range(4) :
      nx = x + dx[k]
      ny = y + dy[k]
      if(nx < 0 or ny < 0 or nx >= n or ny >= m) : continue #범위 밖
      if(cheese[nx][ny] != 0 or airvisit[nx][ny] == w) : continue  #공기가 아니거나 이미 방문

      airvisit[nx][ny] = w
      if(nx == 0 or nx == n-1 or ny == 0 or ny == m-1) : is_inside = False   #가장자리에 닿았을 때
      q.append([nx, ny])

  return is_inside, airvisit


def inSideAir() :
  global n, m
  airvisit = [[0] * m for _ in range(n)]

  for i in range(n) :
    for j in range(m) :
      if(airvisit[i][j] != 0 or cheese[i][j] != 0) : continue
      airvisit[i][j] = 1
      
      is_inside, airvisit = bfs(i, j, airvisit, 1)
      if(is_inside) :
        is_inside, airvisit  = bfs(i, j, airvisit, 3)
        for k in range(n) :
          for l in range(m) :
            if airvisit[k][l] == 3 :
              cheese[k][l] = 3

def Melt_cheese() :
  for i in range(n) :
    for j in range(m) :
      if cheese[i][j] == 1 :
        cnt = 0

        for k in range(4) :
          nx = i + dx[k]
          ny = j + dy[k]
          if cheese[nx][ny] == 0 : cnt+=1

        if cnt >= 2 :
          cheese[i][j] = 2

def Melting() :
  cnt = 0
  for i in range(n) :
    for j in range(m) :
      if cheese[i][j] == 1 :
        cnt += 1
      elif cheese[i][j] == 2:
        cheese[i][j] = 0
      elif cheese[i][j] == 3 :
        cheese[i][j] = 0
  return cnt


n, m = map(int, input().split(' '))

cheese = [list(map(int, input().split(' '))) for _ in range(n)]

dx = [-1, 1, 0, 0]
dy = [0, 0, 1, -1]

ans = 0
while True :
  ans += 1
  inSideAir()
  Melt_cheese()
  if Melting() == 0 :
    print(ans)
    break