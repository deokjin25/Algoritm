from collections import deque
import sys
input = sys.stdin.readline
n = int(input())

def yesNo(tmp) :
  for i in tmp :
    if i == '(' :
      x.append('(')
    else :
      if len(x) > 0 and x.pop() == '(' :
        continue
      else :
        print('NO')
        return

  if len(x) == 0 :
    print('YES')
  else :
    print('NO')


for _ in range(n) :
  x = deque()
  tmp = input().strip()
  yesNo(tmp)