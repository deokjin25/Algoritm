import pygame
import random
import sys

# 초기 설정
pygame.init()
WIDTH, HEIGHT = 400, 400
GRID_SIZE = 20
SCREEN = pygame.display.set_mode((WIDTH, HEIGHT))
CLOCK = pygame.time.Clock()
FONT = pygame.font.SysFont('Arial', 20)

# 색상 정의
GREEN = (0, 255, 0)
RED = (255, 0, 0)
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)

# 방향 상수
DIRECTIONS = {'UP': (0, -1), 'DOWN': (0, 1), 'LEFT': (-1, 0), 'RIGHT': (1, 0)}

def draw_grid():
    for x in range(0, WIDTH, GRID_SIZE):
        pygame.draw.line(SCREEN, WHITE, (x, 0), (x, HEIGHT))
    for y in range(0, HEIGHT, GRID_SIZE):
        pygame.draw.line(SCREEN, WHITE, (0, y), (WIDTH, y))

def get_random_position(exclude):
    while True:
        x = random.randint(0, (WIDTH // GRID_SIZE) - 1) * GRID_SIZE
        y = random.randint(0, (HEIGHT // GRID_SIZE) - 1) * GRID_SIZE
        if (x, y) not in exclude:
            return x, y

def main():
    # 초기화
    snake = [(random.randint(0, 19) * GRID_SIZE, random.randint(0, 19) * GRID_SIZE)]
    direction = random.choice(list(DIRECTIONS.values()))
    food = get_random_position(snake)
    running = True
    score = 0

    while running:
        SCREEN.fill(BLACK)
        draw_grid()

        # 이벤트 처리
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_UP and direction != DIRECTIONS['DOWN']:
                    direction = DIRECTIONS['UP']
                elif event.key == pygame.K_DOWN and direction != DIRECTIONS['UP']:
                    direction = DIRECTIONS['DOWN']
                elif event.key == pygame.K_LEFT and direction != DIRECTIONS['RIGHT']:
                    direction = DIRECTIONS['LEFT']
                elif event.key == pygame.K_RIGHT and direction != DIRECTIONS['LEFT']:
                    direction = DIRECTIONS['RIGHT']

        # 뱀 이동
        new_head = (snake[0][0] + direction[0] * GRID_SIZE, snake[0][1] + direction[1] * GRID_SIZE)

        # 게임 오버 조건
        if (
            new_head[0] < 0 or new_head[0] >= WIDTH or
            new_head[1] < 0 or new_head[1] >= HEIGHT or
            new_head in snake
        ):
            running = False
            break

        # 먹이 확인
        if new_head == food:
            score += 1
            food = get_random_position(snake)
        else:
            snake.pop()  # 꼬리 제거

        snake.insert(0, new_head)

        # 그리기
        for segment in snake:
            pygame.draw.rect(SCREEN, GREEN, (*segment, GRID_SIZE, GRID_SIZE))
        pygame.draw.rect(SCREEN, RED, (*food, GRID_SIZE, GRID_SIZE))

        pygame.display.flip()
        CLOCK.tick(10)

    # 게임 오버 처리
    while not running:
        SCREEN.fill(BLACK)
        text = FONT.render(f"Game Over! Score: {score}. Press R to Restart or Q to Quit.", True, WHITE)
        SCREEN.blit(text, (WIDTH // 2 - text.get_width() // 2, HEIGHT // 2))
        pygame.display.flip()

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_r:
                    main()
                elif event.key == pygame.K_q:
                    pygame.quit()
                    sys.exit()

if __name__ == "__main__":
    main()
