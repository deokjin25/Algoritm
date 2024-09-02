import java.io.*;
import java.util.*;

public class Algo2_광주_4반_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M,K, map[][];
	static boolean visit[][];
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1,};
	static List<Integer> list;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for (int x = x1; x < x2; x++) {
				for (int y = y1; y < y2; y++) {
					map[x][y] = 1;
				}
			}
		}
		
		visit = new boolean[N][M];
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(visit[i][j] || map[i][j] == 1) continue;
				bfs(i,j);	//방문 안했고 얼지 않은 땅이면 시작
			}
		}
		
		System.out.println(list.size());	//얼지 않은 땅 개수
		Collections.sort(list);		//오름 차순
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("%d ",list.get(i));
		}
		
		
	}

	private static void bfs(int i, int j) {
		int n = 0;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i,j});
		visit[i][j] = true;
		n++;	//넓이 카운트 변수
		
		while(!q.isEmpty()) {
			int[] xy = q.poll();
			int x = xy[0];
			int y = xy[1];
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if( nx < 0 || nx >= N || ny < 0 || ny >= M || visit[nx][ny] || map[nx][ny] == 1) continue;
				visit[nx][ny] = true;
				q.offer(new int[] {nx,ny});				
				n++;	//큐에 들어갈 때마다 +1 (얼어붙지 않은 영역)
			}
		}
		
		list.add(n);
	}


}
