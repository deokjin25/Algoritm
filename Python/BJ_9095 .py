def dfs(x) :
    global ans
    if x == 0 :
        ans += 1
        return

    if x >=3 :
        dfs(x-3)

    if x >=2 :
        dfs(x-2)

    dfs(x-1)


t = int(input())
for i in range(t) :
    ans = 0
    dfs(int(input()))
    print(ans)

# dp = [0 for _ in range(11)]
# dp[1] = 1
# dp[2] = 2
# dp[3] = 4
# for i in range(4,11):
#     dp[i] = dp[i-3] + dp[i-2] + dp[i-1]

# t = int(input())
# for i in range(t) :
#     print(dp[int(input())])