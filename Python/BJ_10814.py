import sys

n = int(input())

users = []

for i in range(n) :
  user = input().split()
  user[0] = int(user[0])
  user.append(i)

  users.append(user)


sorted_users = sorted(users, key= lambda x : (x[0], x[2]))

for user in sorted_users :
  print(user[0], user[1])