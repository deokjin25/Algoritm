package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1251_prim_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N;
	static boolean visit[];
	static double E, xarr[], yarr[], costSum;
	static List<double[]> list = new ArrayList<>();
	static List<double[]>[] adj;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			list.clear();
			
			xarr = new double[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				xarr[i] = Double.parseDouble(st.nextToken());
			}
			
			yarr = new double[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				yarr[i] = Double.parseDouble(st.nextToken());
			}
			
			E = Double.parseDouble(br.readLine());
			
			//섬 끼리의 환경 부담금 정책값 계산
			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					double d = Math.pow(xarr[i] - xarr[j], 2) + Math.pow(yarr[i] - yarr[j], 2);
					list.add(new double[] {i,j,E * d});
				}
			}
			//입력 종료
			//list에 간선 정보 from , to, cost 모두 들어있음
			
			adj = new ArrayList[N];	//간선 정보를 넣어줄 리스트 배열(from -> to/to/to/to 를 알기 위해서)
			for (int i = 0; i < N; i++) {
				adj[i] = new ArrayList<double[]>();	//[to,cost]를 넣기 위해서 int[] 삽입
			}
			
			//간선 리스트 생성
			for (int i = 0; i < list.size(); i++) {
				double[] node = list.get(i);
				int from = (int) node[0];
				int to = (int) node[1];
				double cost = node[2];
				adj[from].add(new double[] {to,cost});
				adj[to].add(new double[] {from, cost});
			}
			
			costSum = 0;
			visit = new boolean[N];
			prim(0);
			System.out.printf("#%d %.0f\n",tc,costSum);
		}
		
	}

	private static void prim(int start) {
		//cost를 기준으로 pq 정렬
		PriorityQueue<double[]> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1[1], o2[1]));
		
		//start에 연결된 간선 정보 모두 삽입
		for (int i = 0; i < adj[start].size(); i++) {
			pq.offer(adj[start].get(i));
		}
		visit[start] = true;
		
		while(!pq.isEmpty()) {
			double[] minEdge = pq.poll();	//최소 비용을 가진 간선 추출
			int vertex = (int) minEdge[0];
			double cost = minEdge[1];
			
			if(visit[vertex]) continue;		//방문한 정점(트리 구성 정점)은 pass
			costSum += cost;
			visit[vertex] = true;
			for (int i = 0; i < adj[vertex].size(); i++) {	//최소비용으로 결정된 정점에 연결된 간선 정보 pq 삽입
				pq.offer(adj[vertex].get(i));
			}
			
			
		}
		
		
	}

	

}
