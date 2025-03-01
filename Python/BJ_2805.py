import sys
input = sys.stdin.readline

# 입력
n, m = map(int, input().split())
trees = list(map(int, input().split()))

# 이진 탐색 범위 설정
low, high = 0, max(trees)

# 이진 탐색 시작
while low <= high:
    mid = (low + high) // 2
    
    # mid 높이로 잘랐을 때 얻을 수 있는 나무 길이 계산
    total_wood = sum((tree - mid) for tree in trees if tree > mid)
    
    if total_wood >= m:  # 충분히 얻었으면 더 높이 자를 수 있음
        low = mid + 1
    else:  # 나무가 부족하면 더 낮게 잘라야 함
        high = mid - 1

# 최적의 절단기 높이 출력
print(high)
