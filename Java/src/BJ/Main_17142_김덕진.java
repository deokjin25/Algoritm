package BJ;
import java.io.*;
import java.util.*;
/*
 * 17141 개선
 * visit 배열은 방문을 위한 배열이 아닌 정답 산출을 위한 시간 갱신 배열.
 * 방문 처리는 simulMap에서 1로 두어서 관리
 */
public class Main_17142_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, map[][], simulMap[][], minTime, virusN, visit[][];
	static List<int[]> virusPosition;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static boolean select[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		map = new int[N][N];
		virusPosition = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) virusPosition.add(new int[] {i,j});
			}
		}
		
		virusN = virusPosition.size();
		select = new boolean[virusN];
		minTime = Integer.MAX_VALUE;
		comb(0, 0);
		System.out.println(minTime == Integer.MAX_VALUE ? -1 : minTime);	//한번도 갱신된 적 없으면 -1 출력
		
	}
	
	//바이러스 개수 중 M개만 선택
	private static void comb(int idx, int cnt) {
		if(idx == M) {	//어떤 바이러스를 퍼트릴지 결정, 나머지(결정 안된) 애들은 0 으로 두어야 함
			//시뮬 전용 맵 생성
			simulMap = new int[N][N];
			for (int i = 0; i < N; i++) {
				simulMap[i] = map[i].clone();
			}
			
			Queue<int[]> q = new ArrayDeque<>();
			for (int i = 0; i < virusN; i++) {
				int vr = virusPosition.get(i)[0];
				int vc = virusPosition.get(i)[1];
				//선택된 바이러스 q 삽입 처리, [2]: 시간 값
				if (select[i]) q.offer(new int[] {vr,vc,0});	
			}
			
			visit = new int[N][N];	//시뮬마다 방문체크 배열 재생성	
			
			if(simulation(q) >= 0) minTime = Math.min(minTime, simulation(q));	//다 퍼진 경우만 값 갱신
			return;
		}
		
		for (int i = cnt; i < virusN; i++) {
			select[i] = true;
			comb(idx+1, i+1);
			select[i] = false;
		}
		
		
	}

	private static int simulation(Queue<int[]> q) {
		//한 번의 시간 마다 그때 담겨있던 큐의 개수만큼만. >> 전체 맵에 0이 다 사라질 때까지 하려고 했는데 안 사라지는 경우가 있음.
		//bfs 수행하고 끝나면 제일 큰 숫자값을 반환하는데, 0이 남아 있다면 -1 반환.
		int time = 0; 
		
		bfs(q);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(simulMap[i][j] == 0) return - 1;
				if(simulMap[i][j] == 2) continue;;
				time = Math.max(visit[i][j],time);
			}
		}
		
		return time;
	}

	private static void bfs(Queue<int[]> q) {
		
		while(!q.isEmpty()) {
			int[] rc = q.poll();
			int r = rc[0];
			int c = rc[1];
			int time = rc[2]; 
			if(visit[r][c] > 0) continue;
			visit[r][c] = time;
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr<0 || nc<0 || nr>=N || nc>=N || visit[nr][nc] > 0 || simulMap[nr][nc] == 1) continue;
				if(simulMap[nr][nc] != 2) simulMap[nr][nc] = 1;	//2인 경우는 값 표기 x, 오로지 퍼지기 위해서 큐에만 삽입(이래야 위에서 안 봄)
				q.offer(new int[] {nr,nc,time+1});
			}
		}
		
	}

}
