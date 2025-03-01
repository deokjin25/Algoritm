import sys
input = sys.stdin.readline
from collections import deque

def bfs(x, y) :
    q = deque()
    q.append((x,y,0))
    map_[x][y] = 0
    visit[x][y] = 1

    while q :
        x,y,d = q.popleft()

        for dx, dy in ((1,0), (0,1), (-1,0), (0,-1)) :
            nx = x + dx
            ny = y + dy
            if nx < 0 or nx >=n or ny < 0 or ny >= m : continue
            if visit[nx][ny] == 1 : continue
            if map_[nx][ny] == 0 : continue

            visit[nx][ny] = 1
            map_[nx][ny] = d+1
            q.append((nx,ny,d+1))

    return


def find_target() :
    for i in range(n) :
        for j in range(m) :
            if map_[i][j] == 2 :
                bfs(i,j)
                return


n,m = map(int, input().split())
map_ = []
visit = [[0] * m for _ in range(n)]

for _ in range(m) :
    map_.append(list(map(int, input().split())))

find_target()

for i in range(n) :
    for j in range(m) :
        if visit[i][j] == 0 and map_[i][j] != 0:
            print(-1, end=' ')
            continue
        print(map_[i][j], end=' ')
    print()