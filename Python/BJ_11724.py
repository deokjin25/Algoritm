import sys
from collections import deque
input = sys.stdin.readline

def bfs(vex) :
    global visit

    q = deque()
    q.append(vex)
    visit[vex] = 1

    while q:
        from_ = q.popleft()
        for to_ in edges[from_] :
            if visit[to_] == 1 : continue
            visit[to_] = 1
            q.append(to_)


N, M = map(int, input().split())

edges = [[] for _ in range(N+1)]
for i in range(M) :
    from_, to_ = map(int, input().split())
    edges[from_].append(to_)
    edges[to_].append(from_)

visit = [0 for _ in range(N+1)]

ans = 0
for i in range(1, N+1) :
    if visit[i] == 1 : continue
    ans += 1
    bfs(i)

print(ans)