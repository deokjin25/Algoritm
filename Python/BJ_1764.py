import sys
input = sys.stdin.readline

n, m = map(int, input().split())

d = set()
b = set()

for _ in range(n) :
  d.add(input())

for _ in range(m) :
  b.add(input())

ans = sorted(list(d.intersection(b)))
print(len(ans))
for i in ans :
  print(i[:-1])

