package BJ;

import java.io.*;
import java.util.*;

public class Main_9205_김덕진 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int t, n;
    static int hx, hy, fx, fy;
    static List<int[]> clist;
    
    public static void main(String[] args) throws Exception {
        t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            n = Integer.parseInt(br.readLine());    // 편의점 개수
            
            st = new StringTokenizer(br.readLine());
            hx = Integer.parseInt(st.nextToken());  // 집 좌표 x
            hy = Integer.parseInt(st.nextToken());  // 집 좌표 y
            
            clist = new ArrayList<>();    // 편의점 리스트
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int cx = Integer.parseInt(st.nextToken()); // 편의점 좌표 x
                int cy = Integer.parseInt(st.nextToken()); // 편의점 좌표 y
                clist.add(new int[]{cx, cy});
            }
            
            st = new StringTokenizer(br.readLine());
            fx = Integer.parseInt(st.nextToken());  // 페스티벌 좌표 x
            fy = Integer.parseInt(st.nextToken());  // 페스티벌 좌표 y
            
            System.out.println(isHappy() ? "happy" : "sad");
        }
    }

    // BFS로 탐색해서 도착 가능한지 확인
    private static boolean isHappy() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n];  // 편의점 방문 여부
        
        q.offer(new int[]{hx, hy});  // 시작점 (집)
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            
            // 페스티벌에 도착 가능한지 확인
            if (Math.abs(x - fx) + Math.abs(y - fy) <= 1000) {
                return true;
            }
            
            // 편의점들 중 아직 방문하지 않은 곳을 탐색
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    int cx = clist.get(i)[0];
                    int cy = clist.get(i)[1];
                    
                    // 현재 위치에서 편의점까지 거리가 1000 이하일 경우 방문
                    if (Math.abs(x - cx) + Math.abs(y - cy) <= 1000) {
                        visited[i] = true;
                        q.offer(new int[]{cx, cy});
                    }
                }
            }
        }
        
        // 페스티벌에 도착할 수 없으면 sad
        return false;
    }
}
