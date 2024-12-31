import sys
sys.setrecursionlimit(10**6)

def dfs(node, parent):
    for neighbor in graph[node]:
        if parent_nodes[neighbor] == 0:  # 아직 부모가 기록되지 않은 경우
            parent_nodes[neighbor] = node
            dfs(neighbor, node)

# 입력 처리

N = int(input())  # 첫 번째 줄 입력
graph = [[] for _ in range(N + 1)]

for _ in range(N - 1):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

# 부모 노드 저장 배열 초기화
parent_nodes = [0] * (N + 1)

# DFS 실행 (1번 노드부터 시작)
dfs(1, -1)  # 1번 노드의 부모는 없으므로 -1

# 2번 노드부터 N번 노드까지의 부모 출력
for i in range(2, N + 1):
    print(parent_nodes[i])
