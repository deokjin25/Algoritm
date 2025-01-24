import sys
input = sys.stdin.readline

def count_paint(board, start_row, start_col):
    # 두 개의 체스판 패턴
    chess_pattern1 = [
        "WBWBWBWB",
        "BWBWBWBW",
        "WBWBWBWB",
        "BWBWBWBW",
        "WBWBWBWB",
        "BWBWBWBW",
        "WBWBWBWB",
        "BWBWBWBW"
    ]

    chess_pattern2 = [
        "BWBWBWBW",
        "WBWBWBWB",
        "BWBWBWBW",
        "WBWBWBWB",
        "BWBWBWBW",
        "WBWBWBWB",
        "BWBWBWBW",
        "WBWBWBWB"
    ]

    count1 = 0
    count2 = 0

    for i in range(8):
        for j in range(8):
            if board[start_row + i][start_col + j] != chess_pattern1[i][j]:
                count1 += 1
            if board[start_row + i][start_col + j] != chess_pattern2[i][j]:
                count2 += 1

    return min(count1, count2)

# 입력 받기
N, M = map(int, input().split())
board = [input() for _ in range(N)]

min_paint = float('inf')

# 보드를 8x8 체스판으로 잘라서 최소 칠해야 할 수를 구함
for i in range(N - 7):
    for j in range(M - 7):
        min_paint = min(min_paint, count_paint(board, i, j))

print(min_paint)
