import sys
input = sys.stdin.readline

n = int(input())
s = list(map(int, input().split()))

ans = {}
for i in s :
  if i in ans :
    ans[i] += 1
  else :
    ans[i] = 1

m = int(input())
c = list(map(int, input().split()))

for i in c :
  if i in ans :
    print(ans[i], end=' ')
  else :
    print(0, end=' ')