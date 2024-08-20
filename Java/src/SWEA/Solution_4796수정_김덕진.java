package SWEA;

import java.util.Scanner;

public class Solution_4796수정_김덕진 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int[] heights = new int[N];
            
            for (int i = 0; i < N; i++) {
                heights[i] = sc.nextInt();
            }
            
            int total_mountains = 0;
            int up_count = 0, down_count = 0;
            
            for (int i = 1; i < N; i++) {
                if (heights[i] > heights[i - 1]) {
                    // 이전보다 크면 증가 부분 수열
                    if (down_count > 0) {
                        // 이전에 감소 부분이 끝났다면, '우뚝 선 산' 패턴을 확인
                        total_mountains += up_count * down_count;
                        up_count = 0;
                        down_count = 0;
                    }
                    up_count++;
                } else if (heights[i] < heights[i - 1]) {
                    // 이전보다 작으면 감소 부분 수열
                    if (up_count > 0) {
                        down_count++;
                    }
                } else {
                    // 같으면 초기화
                    up_count = 0;
                    down_count = 0;
                }
            }
            
            // 마지막으로 종료 전에 확인
            if (down_count > 0) {
                total_mountains += up_count * down_count;
            }
            
            System.out.println("#" + tc + " " + total_mountains);
        }
        sc.close();
    }
}
