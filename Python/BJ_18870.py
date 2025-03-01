import sys
input = sys.stdin.readline

n = int(input())
points = list(map(int, input().split()))

# for point in points :
#     print(sorted(list(set(points))).index(point), end=' ')

ans = dict(zip(sorted(list(set(points))), range(0, len(set(points)))))

for point in points :
    print(ans[point], end=' ')