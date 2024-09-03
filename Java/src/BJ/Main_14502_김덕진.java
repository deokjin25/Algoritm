package BJ;
import java.io.*;
import java.util.*;
/*
 * 벽의 위치 조합으로 구하고 바이러스 퍼트린 뒤 전체 0 개수 구하기
 * 새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.
 */
public class Main_14502_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, map[][], simulMap[][], safe;
	static List<int[]> virusPosition;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static boolean visit[][], wall[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		map = new int[N][M];
		virusPosition = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) virusPosition.add(new int[] {i,j});
			}
		}
		
		wall = new boolean[N*M];	// 인덱스/M => x, 인덱스%M => y
		safe = Integer.MIN_VALUE;
		comb(0,0);
		System.out.println(safe);
	}

	private static void comb(int idx, int cnt) {
		if(idx == 3) {	//3개의 벽 위치 조합 완성
			//시뮬레이션용 맵 생성
			simulMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				simulMap[i] = map[i].clone();
			}
			
			//벽 위치 시키기
			for (int i = 0; i < N*M; i++) {
				if(wall[i]) simulMap[i/M][i%M] = 1;
			}
			
			//시뮬 돌 때마다 새로운 방문배열 생성
			visit = new boolean[N][M];
			
			//시뮬 이후 안전지대 카운트
			safe = Math.max(safe, simulation());
			return;
		}
		
		for (int i = cnt; i < N*M; i++) {
			// i/M(열) => x(행), i%M => y(열)
			if(map[i/M][i%M] != 0) continue;
			wall[i] = true;		//벽은 3개만 할거니까 다른 위치 탐색할 때는 원복처리
			comb(idx+1, i+1);
			wall[i] = false;
		}
	}

	private static int simulation() {
		int safe = 0;
		
		//각각의 바이러스 위치마다 bfs수행
		for (int i = 0; i < virusPosition.size(); i++) {
			bfs(virusPosition.get(i));
		}
		
		//시뮬맵에서의 안전지대 카운트
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(simulMap[i][j] == 0) safe++;
			}
		}
		
		return safe;
	}

	private static void bfs(int[] start) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(start);
		
		while(!q.isEmpty()) {
			int[] rc = q.poll();
			int r = rc[0];
			int c = rc[1];
			if(visit[r][c]) continue;
			visit[r][c] = true;
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr<0 || nc<0  || nr >= N  || nc >= M  || 
						visit[nr][nc] || simulMap[nr][nc] != 0) continue;
				simulMap[nr][nc] = 2;
				q.offer(new int[] {nr,nc});
			}
			
		}
		
		
		
	}
}
