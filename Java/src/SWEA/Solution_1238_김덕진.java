package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1238_김덕진 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, S, answer;
    static List<Integer>[] adj;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());

            adj = new ArrayList[101];  // 인접 리스트로 그래프 표현
            for (int i = 0; i <= 100; i++) {
                adj[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N / 2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adj[from].add(to);
            }

            visit = new boolean[101];
            answer = bfs(S);
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static int bfs(int start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0});  // 시작 노드 넣어주기
        visit[start] = true;
        int maxDepth = 0;
        int maxNumber = start;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];		//의미상 from / 연락이 '닿은' 곳
            int depth = curr[1];
            
            //깊이가 더 깊으면 바로 갱신, 같으면 연락번호 비교
            if (depth > maxDepth || (depth == maxDepth && node > maxNumber)) {
                maxDepth = depth;
                maxNumber = node;
            }
            
            //adj에 있는 to 꺼내서 방문한 곳인지 확인 후에 q에 알맞은 값 넣어주기
            for (int next : adj[node]) {
                if (!visit[next]) {
                    visit[next] = true;
                    queue.offer(new int[]{next, depth + 1});
                }
            }
        }

        return maxNumber;
    }
}
