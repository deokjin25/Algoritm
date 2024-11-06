package BJ;

import java.io.*;
import java.util.*;

public class Main_BJ_2887GPT {
    static int N;
    static int[] parent;
    static List<Edge> edges = new ArrayList<>();
    
    static class Edge implements Comparable<Edge> {
        int from, to, weight;
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        int[][] coords = new int[N][4];  // x, y, z, index
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            coords[i][0] = Integer.parseInt(st.nextToken());
            coords[i][1] = Integer.parseInt(st.nextToken());
            coords[i][2] = Integer.parseInt(st.nextToken());
            coords[i][3] = i;  // index
        }
        
        // 각 좌표축별로 정렬하여 인접한 점들 간의 거리로 간선 추가
        for (int d = 0; d < 3; d++) {
            final int dim = d;
            Arrays.sort(coords, Comparator.comparingInt(a -> a[dim]));
            for (int i = 0; i < N - 1; i++) {
                int dist = Math.abs(coords[i][dim] - coords[i + 1][dim]);
                edges.add(new Edge(coords[i][3], coords[i + 1][3], dist));
            }
        }
        
        // Kruskal's MST 알고리즘 시작
        Collections.sort(edges);
        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;
        
        int totalWeight = 0;
        int edgeCount = 0;
        
        for (Edge edge : edges) {
            if (union(edge.from, edge.to)) {
                totalWeight += edge.weight;
                edgeCount++;
                if (edgeCount == N - 1) break;
            }
        }
        
        System.out.println(totalWeight);
    }
    
    // Find-Union functions
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = rootA;
            return true;
        }
        return false;
    }
}
