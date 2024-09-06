package SWEA;

import java.io.*;
import java.util.*;

public class Solution_5656_재풀이 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N, W, H, map[][], arr[], simulmap[][], min;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//공 개수
			W = Integer.parseInt(st.nextToken());	//가로(열)
			H = Integer.parseInt(st.nextToken());	//세로(행)
			
			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			arr = new int[N];
			min = Integer.MAX_VALUE;
			perm(0);	//순서 상관 있음
			System.out.println("#" + tc + " " + min);
		}
		
	}

	private static void perm(int idx) {
		if(idx == N) {	//어디에 떨어트릴지 정했다면
			//시뮬 전용 맵 생성
			simulmap = new int[H][W];
			for (int i = 0; i < H; i++) {
				simulmap[i] = map[i].clone();
			}
			
			//구슬 순서에 맞춰서 낙하
			for (int i = 0; i < N; i++) {
				simulation(arr[i]);
				//한 번 강하 후 밑으로 정리				
				clear();
			}
			
			//남은 벽돌의 수가 가장 적어야 많은 벽돌을 깨트린 셈
			int cnt = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if(simulmap[i][j] > 0) cnt++;
				}
			}
			
			//최소값 갱신
			min = Math.min(min, cnt);
			return;
		}
		
		for (int i = 0; i < W; i++) {
			arr[idx] = i;
			perm(idx+1);
		}
		
	}

	private static void clear() {
		Queue<Integer> q = new ArrayDeque<>();
		
		//터져서 0된거 빼고 모두 큐에 넣기
		for (int j = 0; j < W; j++) {
			
			for (int i = H-1; i >=0 ; i--) {
				if(simulmap[i][j] > 0) {
					q.offer(simulmap[i][j]);
					simulmap[i][j] = 0;
				}
			}
			
			//큐에 들어간 순서대로 밑에서부터 쌓아주기
			int h = H-1;
			while(!q.isEmpty()) {
				simulmap[h][j] = q.poll();
				h--;
			}
		}

	}

	private static void simulation(int fall) {
		//fall: 구슬이 떨어질 위치(열)
		int[] first = null;	//구슬이 떨어지다가 만나는 첫 벽돌
		for (int i = 0; i < H; i++) {
			
			if(simulmap[i][fall] > 0) {		//첫 벽돌부터 본격적인 시작
				first = new int[] {i, fall, simulmap[i][fall]};
				
				//연쇄 반응을 위한 큐 생성, [0]: x, [1]: y, [2]: 벽돌에 쓰여있었던 숫자
				Queue<int[]> q = new ArrayDeque<>();	
				q.offer(first);	//첫번째 벽돌 삽입
				
				
				while(!q.isEmpty()) {
					int[] xy = q.poll();
					int x = xy[0];
					int y = xy[1];
					int range = xy[2];
					for (int j = 0; j < range; j++) {	//벽돌에 쓰여 있던 폭발 범위만큼 탐색
						for (int k = 0; k < 4; k++) {
							int nx = x + dx[k]*j;
							int ny = y + dy[k]*j;
							//범위 밖이거나 범위 내 값이 0이면 pass
							if(nx<0 || ny<0 || nx>=H || ny>=W || simulmap[nx][ny]==0) continue;
							q.offer(new int[] {nx,ny,simulmap[nx][ny]});	//범위 내에 있으면 큐에 넣고
							simulmap[nx][ny] = 0;	//해당 구억 0처리(사실상 방문처리느낌)
							
						}
					}
				}
				
				break;
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
