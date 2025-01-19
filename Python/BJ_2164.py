from collections import deque

n = int(input())

cards = deque(range(1, n + 1))

while len(cards) > 1:
    cards.popleft()
    cards.append(cards.popleft())

print(cards[0])

## 공식 풀이
# n = int(input())
# x = 0
# while pow(2, x) <= n :
#   x += 1
# x -= 1
# if n % pow(2,x) == 0 :
#   print(pow(2,x))
# else :
#   print(2 * (n-pow(2,x)))