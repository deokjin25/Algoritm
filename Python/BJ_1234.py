N, R, G, B = map(int, input().split())

def comb(n, r):
  if r == n or r == 0 :
    return 1
  return comb(n-1, r-1) + comb(n-1, r)

dp = [[[[0]*(B+1) for _ in range(G+1)] for _ in range(R+1)] for _ in range(N+1)]

if B :
  dp[1][R][G][B-1] = 1
if G :
  dp[1][R][G-1][B] = 1
if R :
  dp[1][R-1][G][B] = 1
  
for i in range(1, N) :
  for r in range(R+1) :
    for g in range(G+1) :
      for b in range(B+1) :
        if not dp[i][r][g][b] :
          continue
        d, t = (i+1) // 2, (i+1) // 3
        if (i+1) % 3 == 0 and r >= t and g >= t and b >= t :
          dp[i+1][r-t][g-t][b-t] += dp[i][r][g][b] * comb(3*t, t) * comb(2*t, t)
        if (i+1) % 2 == 0 :
          if r >= d and g >= d :
            dp[i+1][r-d][g-d][b] += dp[i][r][g][b] * comb(2*d, d)
          if g >= d and b >= d :
            dp[i+1][r][g-d][b-d] += dp[i][r][g][b] * comb(2*d, d)
          if r >= d and b >= d :
            dp[i+1][r-d][g][b-d] += dp[i][r][g][b] * comb(2*d, d)
        if r > i :
          dp[i+1][r-i-1][g][b] += dp[i][r][g][b]
        if g > i :
          dp[i+1][r][g-i-1][b] += dp[i][r][g][b]
        if b > i :
          dp[i+1][r][g][b-i-1] += dp[i][r][g][b]

ans = 0
for i in dp[-1] :
  for j in i :
    for k in j :
      ans += k
print(ans)