package BJ;

import java.io.*;
import java.util.*;


public class Main_16236_김덕진 {
	static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, map[][], ans;
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	static PriorityQueue<Fish> pq;
	
	static class BabyShark {
		int x;
		int y;
		int s;
		int e;
		int t;
		
		public BabyShark(int x, int y, int s, int e, int t) {
			super();
			this.x = x;
			this.y = y;
			this.s = s;
			this.e = e;
			this.t = t;
		}
	}
	
	static class Fish {
		int x;
		int y;
		int d;
		public Fish(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		BabyShark bs = null;
		pq = new PriorityQueue<>((o1, o2) -> {
			if(o1.d != o2.d) return o1.d - o2.d;
			if(o1.x != o2.x) return o1.x - o2.x;
			return o1.y - o2.y;
		});
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					map[i][j] = 0;
					bs = new BabyShark(i, j, 2, 0, 0);
				}
			}
		}
		
		int ans = 0;
		do {
			pq.clear();
			bfs(bs);
			if(pq.isEmpty()) break;
			Fish eat = pq.peek();
			map[eat.x][eat.y] = 0;
			bs.x = eat.x;
			bs.y = eat.y;
			if(++bs.e == bs.s) {
				bs.e = 0;
				bs.s++;
			}
			ans += eat.d;
			
			///////////
//			System.out.println("================");
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println("현재 위치: " + bs.x + " "+ bs.y);
//			System.out.println("몸집: " + bs.s);
//			System.out.println();
			//////////
			
			
		}while(!pq.isEmpty());
		
		System.out.println(ans);
		
	}


	private static void bfs(BabyShark start) {
		Queue<BabyShark> q = new ArrayDeque<>();
		q.offer(start);
		boolean visit[][] = new boolean[N][N];
		visit[start.x][start.y] = true;
		
		while(!q.isEmpty()) {
			BabyShark bs = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = bs.x + dx[i];
				int ny = bs.y + dy[i];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || visit[nx][ny]) continue;
				if(map[nx][ny] > bs.s) continue;
				
				visit[nx][ny] = true;
				
				if(map[nx][ny] < bs.s && map[nx][ny] > 0) {
					pq.offer(new Fish(nx, ny, bs.t+1));
					q.offer(new BabyShark(nx, ny, bs.s, bs.e, bs.t+1));
				}else {
					q.offer(new BabyShark(nx, ny, bs.s, bs.e, bs.t+1));
				}
				
			}
			
		}
		
	}

}
