import sys
from collections import deque

input = sys.stdin.readline

n = int(input())
pair = int(input())

# com = range(1,n)
vex = [[] for _ in range(n+1)]
for i in range(pair) :
    from_, to_ = map(int, input().split())
    vex[from_].append(to_)
    vex[to_].append(from_)

visit = [0 for _ in range(n+1)]
visit[1] = 1  # 1번 컴퓨터도 방문 표시

ans = 0

q = deque()
for i in vex[1] :
    q.append(i)
    visit[i] = 1
    ans+=1

while q :
    from_ = q.popleft()
    for to_ in vex[from_] :
        if visit[to_] == 1 : continue
        q.append(to_)
        visit[to_] = 1
        ans+=1

print(ans)