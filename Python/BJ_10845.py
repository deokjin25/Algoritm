from collections import deque
import sys
input = sys.stdin.readline

stack = deque()

n = int(input())

for _ in range(n) :
  x = input().split()
  if x[0] == 'push' :
    stack.append(int(x[1]))
  elif x[0] == 'front' :
    if len(stack) == 0 : 
      print(-1)
      continue
    else :
      tmp = stack.popleft()
      print(tmp)
      stack.appendleft(tmp)
  elif x[0] == 'back' :
    if len(stack) == 0 : 
      print(-1)
      continue
    else :
      tmp = stack.pop()
      print(tmp)
      stack.append(tmp)
  elif x[0] == 'size' :
    print(len(stack))
  elif x[0] == 'empty' :
    if len(stack) == 0 :
      print(1)
    else :
      print(0)
  elif x[0] == 'pop' :
    if len(stack) == 0 :
      print(-1)
      continue
    else :
      print(stack.popleft())