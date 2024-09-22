N = int(input())
a = list(map(int, input().split()))
t, p = map(int, input().split())

ans = 0
for i in range(len(a)) :
  if a[i] % t == 0 :
    ans += a[i] // t
  else :
    ans += (a[i] // t) + 1

print(ans)
print(N//p, N%p)