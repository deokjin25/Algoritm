N, M = map(int, input().split())
card = list(map(int, input().split()))

ans = 0

def comb(ind, cnt, s) :
  global ans
  if(s > M) : return

  if cnt == 3 :
    ans = max(ans, s)
    return
  
  for i in range(ind, N) :
    comb(i+1, cnt+1, s + card[i])

comb(0,0,0)

print(ans)