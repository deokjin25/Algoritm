T = int(input())

for test_case in range(1, T+1) :
    print(test_case)
    N, K = map(int, input().split())
    arr = []
    for _ in range(N) :
        arr.append(list(map(int, input().split())))

    answer = 0

    for i in range(N) :
        x = []
        for j in range(N) :
            if arr[i][j] == 1:
                x.append(arr[i][j])
                if j == N-1 and x == [1] * K:
                    answer += 1
            else:
                if x == [1]*K :
                    answer += 1
                    x=[]
                else :
                    x=[]

    for i in range(N) :
          x = []
          for j in range(N) :
              if arr[j][i] == 1:
                  x.append(arr[j][i])
                  if j == N-1 and x == [1] * K:
                      answer += 1
              else:
                  if x == [1]*K :
                      answer += 1
                      x = []
                  else :
                      x=[]
    
    print(f'#{test_case} {answer}')