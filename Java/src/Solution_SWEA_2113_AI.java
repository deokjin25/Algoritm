import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_2113_AI {
    static int D, W, K;
    static int[][] film;
    static int minInjection;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken()); // 세로
            W = Integer.parseInt(st.nextToken()); // 가로
            K = Integer.parseInt(st.nextToken()); // 검사 기준

            film = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            minInjection = K; // 초기 값은 K로 설정
            dfs(0, 0);
            System.out.println("#" + tc + " " + minInjection);
        }
    }

    static void dfs(int depth, int injected) {
        if (injected >= minInjection) return; // 이미 최소 주입 횟수보다 크면 종료

        if (depth == D) { // 마지막 행까지 처리한 경우
            if (checkFilm()) { // 필름이 조건을 만족하면
                minInjection = Math.min(minInjection, injected); // 최소 주입 횟수 갱신
            }
            return;
        }

        // 약물 주입 없이 다음 행으로 이동
        dfs(depth + 1, injected);

        // 현재 행을 A(0)로 변환하고 다음 행으로 이동
        int[] original = film[depth];
        film[depth] = new int[W];
        dfs(depth + 1, injected + 1);
        
        // 현재 행을 B(1)로 변환하고 다음 행으로 이동
        film[depth] = new int[W];
        for (int i = 0; i < W; i++) {
            film[depth][i] = 1;
        }
        dfs(depth + 1, injected + 1);

        // 원래 행으로 복원
        film[depth] = original;
    }

    static boolean checkFilm() {
        for (int i = 0; i < W; i++) {
            boolean valid = false;
            for (int j = 0; j <= D - K; j++) {
                boolean isSequence = true;
                for (int l = 1; l < K; l++) {
                    if (film[j][i] != film[j + l][i]) {
                        isSequence = false;
                        break;
                    }
                }
                if (isSequence) {
                    valid = true;
                    break;
                }
            }
            if (!valid) return false;
        }
        return true;
    }
}
