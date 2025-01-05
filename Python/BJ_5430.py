import sys
input = sys.stdin.readline

def ac(arr) :
  cntR = 0
  for i in range(len(p)) :
    if p[i] == 'D' and cntR%2 == 0 :
      if arr == [] : 
        return 'error'
      arr.pop(0)
    elif p[i] == 'D' and cntR%2 == 1 :
      if arr == [] : 
        return 'error'
      arr.pop(-1)
    else :
      cntR += 1

  if cntR%2 == 1 :
    arr = arr[::-1]

  return arr


T = int(input())
for _ in range(T) :
  p = input().strip()
  n = int(input())
  arr = input().strip()
  if arr == '[]' :
    arr = []
  else :
    arr = list(map(int, arr.strip("[]").split(',')))

  ans = ac(arr)
  if ans == 'error' :
    print('error')
  else :
    print("["+",".join(map(str, ans))+"]")