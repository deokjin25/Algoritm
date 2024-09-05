package SWEA;

import java.io.*;
import java.util.*;

public class Solution_5643_김덕진 {
    static final int INF = 1000000; // 큰 수로 무한대를 표현
    static int T, N, M, adj[][];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            
            adj = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                Arrays.fill(adj[i], INF);
                adj[i][i] = 0; // 자기 자신과의 거리는 0
            }
            
            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adj[from][to] = 1; // from이 to보다 작다는 정보
            }

            // 플로이드-워셜 알고리즘: 각 정점간의 최단 경로를 계산하는 알고리즘( 시간 복잡도: O(v^3) )
            // i -> j 보다 i -> k -> j 로 가는 경로가 더 짧다면 갱신
            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
                    }
                }
            }
            
            int answer = 0;
            for (int i = 1; i <= N; i++) {
                int count = 0;
                for (int j = 1; j <= N; j++) {
                    if (i != j && (adj[i][j] != INF || adj[j][i] != INF)) {
                        count++;
                    }
                }
                if (count == N - 1) { // 자신을 제외한 모든 학생과의 키 비교가 가능하면, (N-1)개의 정보를 가지고 있다면
                    answer++;
                }
            }
            
            System.out.println("#" + tc + " " + answer);
        }
    }
}
