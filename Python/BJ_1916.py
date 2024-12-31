import heapq

def go(s, e) :
  heap = [(0, s)]
  dis = [float('inf')] * (N+1)
  dis[s] = 0

  while heap :
    cost, to = heapq.heappop(heap)

    if to == e : return cost

    if(cost > dis[to]) : continue

    for edge in arr[to] :
      next_cost, next_to = edge
      total_cost = next_cost + cost

      if total_cost < dis[next_to] :
        dis[next_to] = total_cost
        heapq.heappush(heap, (total_cost, next_to))


N = int(input())
M = int(input())

arr = [[] for _ in range(N+1)]

for _ in range(M) :
  start, end, cost = map(int, input().split(" "))
  arr[start].append((cost, end))

s, e = map(int, input().split(" "))
print(go(s, e))