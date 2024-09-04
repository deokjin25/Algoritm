package SWEA;
import java.io.*;
import java.util.*;

public class Solution_1949_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N, K;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static int maxLenght;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	
			K = Integer.parseInt(st.nextToken());	//최대 공사 가능 깊이
			map = new int[N][N];
			int top = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					top = Math.max(top, map[i][j]);		//제일 높은 곳의 값 기억하기
				}
			}
			
			visited = new boolean[N][N];	//방문체크 배열
			maxLenght=Integer.MIN_VALUE;	//최대 길이
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if ( map[i][j] == top ) {	//2차원 배열 순회하다가 제일 높았던 곳이라면
						visited[i][j] = true;	//방문 체크(무한 반복 방지) 후 다시 돌아왔을 때 다른 방향에 대해서도 봐야하니까 원복처리
						dfs(i, j, 1, false);	//(1): x, (2): y, (3): 현재까지의 길이값, (4): 공사 여부
						visited[i][j] = false;
					}
				}
			}
			System.out.println("#"+tc+" "+maxLenght);
		}

	}
	
	private static void dfs(int x, int y, int lenght, boolean check) {
		maxLenght = Math.max(maxLenght, lenght);
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;	//범위를 벗어낫으므로 or 방문 했던 곳이므로 pass
			
			if(map[x][y] > map[nx][ny]) {	//공사 안하고 그냥 진행하는 경우의 수.	
				visited[nx][ny] = true;
				dfs(nx, ny, lenght+1, check);
				visited[nx][ny] = false;
			}
			
			else if(!check && map[x][y] > map[nx][ny] - K) { 	//공사를 아직 안했고 + 공사하면 진출 가능한 경우
				int tmp = map[nx][ny]; 	//원복 처리를 위한 값
				map[nx][ny] = map[x][y] - 1;	//꼭 K만큼 다 파지 않아도 된다.(1 차이만 나게 공사해야 최대 길이를 볼 수 있다)
				visited[nx][ny] = true;
				dfs(nx, ny, lenght+1, true);
				map[nx][ny] = tmp;
				visited[nx][ny] = false;
			}

		}
		
	}

}
