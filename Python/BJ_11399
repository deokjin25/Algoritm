import heapq
import itertools
import sys
input = sys.stdin.readline

q = []

n = int(input())

p = list(map(int, input().split()))

for i in p :
  heapq.heappush(q, i)

print(sum(itertools.accumulate([heapq.heappop(q) for _ in range(len(q))])))
