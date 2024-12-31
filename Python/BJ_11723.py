import sys
input = sys.stdin.readline

# 비트마스크 초기화
s = 0

def add(x):
    global s
    s |= (1 << x)

def remove(x):
    global s
    s &= ~(1 << x)

def check(x):
    print(1 if s & (1 << x) else 0)

def toggle(x):
    global s
    s ^= (1 << x)

def all():
    global s
    s = (1 << 21) - 1  # 20개의 비트 모두 1로 설정

def empty():
    global s
    s = 0

m = int(input())  # 첫 번째 입력: 명령의 개수

for _ in range(m):
    command = input().strip().split()  # 한 줄씩 입력 처리
    if len(command) == 2:
        x = int(command[1])
    if command[0] == 'add':
        add(x)
    elif command[0] == 'remove':
        remove(x)
    elif command[0] == 'check':
        check(x)
    elif command[0] == 'toggle':
        toggle(x)
    elif command[0] == 'all':
        all()
    elif command[0] == 'empty':
        empty()
