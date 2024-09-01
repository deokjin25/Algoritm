package BJ;

import java.io.*;
import java.util.*;
/*
 * map 배열 숫자로 관리했다가 메모리 초과
 * boolean 배열로 바꿨지만 메모리 초과
 * visit 배열 없애고 map으로만 관리(왔던길 false처리)했지만 메모리 초과 (최대 100*100이니까 10,000이면 10mb인데 왜...?)
 * >> map 말고 메모리 잡아먹을게 큐 밖에 없다. 큐에 들어가는 데이터 양을 줄이자
 * 큐에 [x,y,길이값]으로 넣어주다가 길이값을 2차원 배열로 관리해서 큐에는 [i,j]로 바꿔줘도 메모리초과
 * 방문 처리를 큐에 넣기 직전에 해주니 통과...
 * 방문 처리를 바로 안하고 p.poll()한 뒤에 방문처리를 해주게 된다면 똑같은 위치에 대해서 큐에 offer되는 양이 최대 4개(4방향 탐색이라)가 된다. 
 * 이런 지점이 어마어마하게 많아서 메모리 초과가 난 듯 하다.
 */
public class Main_2178_김덕진{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static boolean map[][];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String string = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = string.charAt(j) == '1' ? true : false;
			}
		}
		
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {0,0,1});
		map[0][0] = false;
		
		while(!q.isEmpty()) {
			int[] xy = q.poll();
			int x = xy[0];
			int y = xy[1];
			int value = xy[2];
			
			if(x == N-1 && y == M-1) {
				return value;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || ny < 0 || nx == N || ny == M || !map[nx][ny]) continue;
				map[nx][ny] = false;
				q.offer(new int[] {nx,ny, value+1});
			}
		}
		return -1;
	}
}
