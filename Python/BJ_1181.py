n = int(input())
words = set(input().strip() for _ in range(n))  # 중복 제거를 위해 set 사용

# 정렬: 1순위는 길이, 2순위는 사전순
sorted_words = sorted(words, key=lambda word: (len(word), word))

for word in sorted_words:
    print(word)