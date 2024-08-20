package SWEA;
import java.io.*;
import java.util.*;
 
public class Solution_SWEA_2112 {
    public static int T, D, W, K, result;
    public static int[][] map = new int[13][20];
    public static int[][] testMap = new int[13][20];
    public static int[] rowA = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    public static int[] rowB = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
 
            // 1. map 입력하기
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            // 2. test 하기 위한 Map 세팅 > 깊은 복사 왜 불필요한지 생각해보기
            result = 0;
            for (int i = 0; i < D; i++) {
                testMap[i] = map[i];
            }
             
            // 3. 검사가 필요 없거나, 교환 없이도 검사 통과한다면 tc 종료
            if (K == 1 || kTest() == 1) {
                System.out.println("#" + tc + " 0");
                continue;
            }
 
            // 4. 1개 ~ D개 뽑는 모든 경우의수 완전 탐색
            //    1개짜리 조합부터 진행하기 때문에 먼저 발견된 케이스가 최소로 교환했다는 것을 보장함
            for (int cnt = 1; cnt <= D; cnt++) {
                
                comb(0, 0, cnt);
 
                // 5. 검사 중 검사 통과되었다면 tc 종료
                if (result == 1) {
                    System.out.println("#" + tc + " " + cnt);
                    break;
                }
            }
 
        }
    }
 
    public static void comb(int idx, int cnt, int maxCnt) {
 
        // ㄱ. 검사 통과 이력이 있다면 종료
        if (result == 1)
            return;
 
        // ㄴ. 다 뽑았다면 검사 진행
        if (cnt == maxCnt) {
            if (kTest() == 1) {
                result = 1;
            }
            return;
        }
 
        // ㄷ. range 탐색하며 교환 여부 선택
        for (int i = idx; i < D; i++) {
            // a. 해당 라인을 A로 바꾼 후 진행
            testMap[i] = rowA;
            comb(i + 1, cnt + 1, maxCnt);
 
            // b. 해당 라인을 B로 바꾼 후 진행
            testMap[i] = rowB;
            comb(i + 1, cnt + 1, maxCnt);
 
            // c. 해당 라인 변화없이 진행
            testMap[i] = map[i];
        }
    }
 
    public static int kTest() {
        // 행 우선 검사
        for (int j = 0; j < W; j++) {
            // recent는 최근 문자 기록용
            // sameCount는 이어지는 문자 누적 counting용
            int recent = -1;
            int sameCount = 0;
            
            for (int i = 0; i < D; i++) {
                // 최근 문자와 같다면 counting
                // counting 결과가 K층 이상이라면 해당 열에 대해 검사 통과
                // 아니라면 recent와 sameCount 초기화
                if (testMap[i][j] == recent) {
                    if (++sameCount >= K) {
                        break;
                    }
                } else {
                    recent = testMap[i][j];
                    sameCount = 1;
                }
            }
            // 해당 열을 통과했는데 K개 이상이었던 적이 없다면 불량
            if (sameCount < K) {
                return -1;
            }
        }
        
        // 여기까지 왔다는 것은 모든 열이 검사를 통과했다는 것
        return 1;
    }
}