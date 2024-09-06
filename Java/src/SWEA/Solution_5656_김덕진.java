package SWEA;
import java.io.*;
import java.util.*;

public class Solution_5656_김덕진 {
    static int T, N, W, H;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dc = {0, 0, -1, 1};
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 구슬 수
            W = Integer.parseInt(st.nextToken()); // 너비
            H = Integer.parseInt(st.nextToken()); // 높이

            map = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            result = Integer.MAX_VALUE;
            dfs(0, map);
        	System.out.println("#" + tc + " " + result);     
        }
    }

    // 깊이 우선 탐색 (DFS)
    private static void dfs(int depth, int[][] map) {
    	if(countRemainingBricks(map) == 0) {
    		result = 0;
    		return;
    	}
    	
        if (depth == N) {
            // 남은 벽돌 개수를 세고 최소값을 갱신
            int count = countRemainingBricks(map);
            result = Math.min(result, count);
            return;
        }

        // 열마다 구슬을 떨어뜨려서 시도
        for (int i = 0; i < W; i++) {
            int[][] newMap = copyMap(map);
            if (dropMarble(i, newMap)) {
                cleanMap(newMap);
                dfs(depth + 1, newMap);
            }
        }
    }

    // 구슬을 떨어뜨리고 벽돌을 터뜨리는 함수
    private static boolean dropMarble(int col, int[][] map) {
        for (int row = 0; row < H; row++) {
            if (map[row][col] != 0) {
                explode(row, col, map);
                return true;
            }
        }
        return false; // 떨어뜨린 위치에 벽돌이 없으면 false
    }

    // 벽돌을 터뜨리는 함수
    private static void explode(int r, int c, int[][] map) {
        int range = map[r][c];
        map[r][c] = 0; // 현재 벽돌 제거

        for (int d = 0; d < 4; d++) {
            for (int i = 1; i < range; i++) {
                int nr = r + dr[d] * i;
                int nc = c + dc[d] * i;

                if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] != 0) {
                    explode(nr, nc, map); // 연쇄 반응 처리
                }
            }
        }
    }

    // 남은 벽돌을 아래로 내리는 함수
    private static void cleanMap(int[][] map) {
        for (int c = 0; c < W; c++) {
            Queue<Integer> queue = new LinkedList<>();
            for (int r = H - 1; r >= 0; r--) {
                if (map[r][c] > 0) {
                    queue.offer(map[r][c]);
                    map[r][c] = 0;
                }
            }

            int row = H - 1;
            while (!queue.isEmpty()) {
                map[row--][c] = queue.poll();
            }
        }
    }

    // 남은 벽돌 개수를 세는 함수
    private static int countRemainingBricks(int[][] map) {
        int count = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    // 맵 복사 함수
    private static int[][] copyMap(int[][] map) {
        int[][] newMap = new int[H][W];
        for (int i = 0; i < H; i++) {
            newMap[i] = map[i].clone();
        }
        return newMap;
    }
}
