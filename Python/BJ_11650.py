n = int(input())
points = [list(map(int, input().split())) for _ in range(n)]

sorted_point = sorted(points, key=lambda point: (point[0], point[1]))

for point in sorted_point :
  print(point[0], point[1])