s, d = map(int, input().split())

q = [(s,0)]
c = set()
while q :
  cur = q.pop(0)
  x = cur[0]
  t = cur[1]

  if x in c : continue
  c.add(x)

  if x == d :
    print(t)
    break

  if x < d :
    q.append((x+1, t+1))
    q.append((x*2, t+1))
    if x-1 > 0 :
      q.append((x-1, t+1))
  else :
    q.append((x-1, t+1))