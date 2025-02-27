import sys
input = sys.stdin.readline
import heapq

def bfs() :
    while q :
        d, x, y = heapq.heappop(q)

        for dx, dy in ((0,1), (1,0), (-1,0), (0,-1)) :
            nx = x + dx
            ny = y + dy
            if nx < 0 or nx >= n or ny < 0 or ny >= m : continue
            if visit[nx][ny] == 1 : continue
            if tomatos[nx][ny] != 0 : continue

            visit[nx][ny] = 1
            tomatos[nx][ny] = d+1
            q.append((d+1, nx, ny))

def answer() :
    if count_tomato >= 1 and before_tomato == 0 :
        return 0
    else :
        bfs()
        ans = 0
        for i in range(n) :
            for j in range(m) :
                if visit[i][j] == 0 and tomatos[i][j] != -1 :
                    return -1
                ans = max(ans, tomatos[i][j])
    return ans


m,n = map(int, input().split())

tomatos = [list(map(int, input().split())) for _ in range(n)]
visit = [[0] * m for _ in range(n)]

count_tomato = 0
before_tomato = 0
q = []
for i in range(n) :
    for j in range(m) :
        if tomatos[i][j] == 1 :
            count_tomato += 1
            visit[i][j] = 1
            heapq.heappush(q, (0,i,j))
        if tomatos[i][j] == 0 :
            before_tomato += 1


print(answer())
# for i in range(n) :
#     print(tomatos[i])
# print('--------------------')
# for i in range(n) :
#     print(visit[i])