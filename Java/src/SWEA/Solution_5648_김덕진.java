package SWEA;

import java.io.*;
import java.util.*;

미해결문제
public class Solution_5648_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N, ans, mx, my;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static Queue<Atomic> q;
	static Atomic map[][];
	
	static class Atomic {
		int x;
		int y;
		int d;	//원자 방향
		int e;	//원자의 에너지
		int t;	//원자의 시간
		boolean s;	//다른 원자랑 합쳐진 원자인지 나타내는 변수
		
		public Atomic(int x, int y, int d, int e, int t, boolean s) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.e = e;
			this.t = t;
			this.s = s;
		}

		@Override
		public String toString() {
			return "Atomic [x=" + x + ", y=" + y + ", e=" + e + ", d=" + d + ", s=" + s + "]";
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			//map 최대 크기
			mx = Integer.MIN_VALUE;
			my = Integer.MIN_VALUE;
			
			q = new ArrayDeque<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				mx = Math.max(Math.abs(x), mx);
				my = Math.max(Math.abs(y), my);
				Atomic atomic = new Atomic(x, y, d, e, 0, false);
				q.offer(atomic);
			}
			
			map = new Atomic[mx*2+1][my*2+1];
			
			//map에 저장할 수 있게 0부터 시작하는 좌표로 재조정
			for (int i = 0; i < q.size(); i++) {
				Atomic atomic = q.poll();
				atomic.x = mx + atomic.x;
				atomic.y = my + atomic.y;
				atomic.d = atomic.d == 2 ? 0 : atomic.d == 0 ? 1 : atomic.d == 3 ? 2 : 3;
				q.offer(atomic);
				map[atomic.x][atomic.y]= atomic; 
			}
			
			ans = 0;
			int t = 0;
			//원자가 하나도 안 남을 때까지 반복
			while(!q.isEmpty()) {
				simulation();
				t++;
				//한 번의 사이클 돌고 나서 뭉쳐진 원자 검사 후 소멸 및 에너지 합산 처리
				//관리되고 있는 시간(t)과 원자의 시간이 일치하는 경우에만 관리
				for (int i = 0; i < map.length; i++) {
					for (int j = 0; j < map[0].length; j++) {
						Atomic atomic = map[i][j];
						if(atomic != null && atomic.t == t  && atomic.s) {
							ans += atomic.e;
							map[i][j] = null;
						}else if(atomic != null && atomic.t == t && !atomic.s) {
							q.offer(new Atomic(i, j, atomic.d, atomic.e, atomic.t, false));
						}
					}
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}


	private static void simulation() {
		int size = q.size();
		
		for (int i = 0; i < size; i++) {
			Atomic atomic = q.poll();
			atomic.t++;	//이동예정
			
			int nx = atomic.x + dx[atomic.d];
			int ny = atomic.y + dy[atomic.d];
			//범위 밖으로 벗어나면 영영 만날 일 없으므로 큐에서 제거
			if(nx < 0 || ny < 0 || nx > mx*2 || ny > my*2) continue;
			
			Atomic go = map[nx][ny];
			
			//가려는 방향에 아무것도 없으면 순수 이동
			if(go == null) {
				map[nx][ny] = atomic;
				//원자의 기존 자리 처리해야 함... 어떻게? >> 무작정 null처리는 안된다. 이미 와 있었던 원자가 있을 수 있어.
				//
				
			}
			
			//이동 방향에 아직 이동 전(t-1)인 원자가 있는데 나랑 방향이 반대가 아닌 경우
			else if(go != null && go.t+1 == atomic.t && (go.d + atomic.d)%2 != 0) {
				//덮어쓰기가 되나? 이동 전인 원자는 보존 해야 하는데,,, 큐에서 관리되고 있으니 괜찮나? >> 반례 존재해서 안된다.
				map[nx][ny] = atomic;
			}
			
			//이동 방향에 아직 이동 전(t-1)인 원자가 있는데 나랑 방향이 정반대인 경우, 두 원자는 0.5초 후 소멸 할 예정.
			else if(go != null && go.t+1 == atomic.t && (go.d + atomic.d)%2 == 0) {
				
			}
			
			//이동 방향에 이미 이동해 온(t) 원자가 있는 경우, 하지만 아직 합쳐지기 전 상태 일 때
			else if(go != null && go.t == atomic.t && !go.s) {
				Atomic sAtomic = new Atomic(nx, ny, atomic.d, go.e + atomic.e, go.t, true);
				map[nx][ny] = sAtomic;
			}
			
			//이동 방향에 이미 이동을 마친 원자가 있는데 합쳐져 있는 경우
			else if(go != null && go.t == atomic.t && !go.s) {
				map[nx][ny].e += atomic.e;
				//원자의 기존 자리 처리해야 함... 어떻게? >> 무작정 null처리는 안된다. 이미 와 있었던 원자가 있을 수 있어.
				//
			}
			
		}
		
	}

}
