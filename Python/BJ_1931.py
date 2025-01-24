# 종료시간이 빠른거

import heapq
import sys

input = sys.stdin.readline

n = int(input())

q = []
for i in range(n) :
    start, end = map(int,input().split())
    heapq.heappush(q, (end, start))


cnt = 0
endTime = 0
while q :
    end, start = heapq.heappop(q)
    if start >= endTime : 
      cnt += 1
      endTime = end

print(cnt)