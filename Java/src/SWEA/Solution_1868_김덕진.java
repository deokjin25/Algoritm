package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1868_김덕진 {
	static int T, N;
	static char map[][];
	static int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
	static int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static boolean[][] visit;
		
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new char[N][N];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			visit = new boolean[N][N];
			minecheck();
			
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == '*' || visit[i][j]) continue;	//밑에서 bfs작업으로 방문처리 된 곳 pass
					if (map[i][j] == '0') {
						bfs(i,j);	//0먼저 처리
						cnt++;	//해당 지점을 누른 거니까 카운트 세주기
					}
				}
			}
			
			//0이 아닌 다른 지점들은 각 칸을 눌러도 그 자리 하나밖에 안나오니까 세주기만 하면 된다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == '*' || visit[i][j]) continue;	//지뢰거나 일전에 방문처리 됐던 곳이면 pass
					cnt++;
				}
			}
			
			System.out.println("#" + tc + " " + cnt);
			
		}
		
	}

	private static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i,j});
		visit[i][j] = true;
		
		while(!q.isEmpty()) {
			int[] xy = q.poll();
			int x = xy[0];
			int y = xy[1];
			for (int k = 0; k < 8; k++) {	//0 주변 8방탐색 하면서 q에 추가 삽입 해주기
				int nx = x + dx[k];
				int ny = y + dy[k];
				if(nx<0 || nx>=N || ny<0 || ny>=N || visit[nx][ny]) continue;	//범위밖이거나 방문했으면 pass
				//현재 map에 각 칸마다의 8방 지뢰 개수가 표시되어 있기 때문에 주변에 0이 있는지 확인만 하고 q에 넣기만 하면
				//다시 위로 돌아가서 방문 처리 및 그 지점에서의 8방 탐색이 또 이루어 짐.
				
				visit[nx][ny] = true;	//방문처리
				
				if(map[nx][ny] == '0') {
					q.offer(new int[] {nx,ny});
				}
			}
			
		}
	}

	private static void minecheck() {
		//본격적인 작업전에 각 칸마다 주변 지뢰개수 표시
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '*') continue;
				//지뢰가 아닌 곳
				int mine = 0;
				for (int k = 0; k < 8; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if(nx<0 || nx >= N || ny<0 || ny>=N) continue;	//범위 밖이면 pass
					if(map[nx][ny] == '*') mine++;	//8방 중의 지뢰 개수 세기
				}
				map[i][j] = (char) (mine+'0');	//지뢰가 없던 곳의 8방에 지뢰 몇개 있었는지 표시
			}
		}
	}
	
}