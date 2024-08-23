package BJ;
import java.io.*;
import java.util.*;
public class Main_3190_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R,C, cnt;
	static char map[][];
	static boolean check[][];
	static int[] dx = {-1,0,1};	
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		cnt = 0;
		check = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			flag = false;	//도착하면 값 바꿔줄 변수
			dfs(i,0);
		}
		
		System.out.println(cnt);
	}

	private static void dfs(int x, int y) {
		if(y == C-1) {
			cnt++;
			flag = true;	//도착 표시
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			int nx = x + dx[i];	//오른쪽 위가 best이므로 -1부터 시작
			int ny = y + 1;
			if(nx < 0 || nx >= R || check[nx][ny] || map[nx][ny] == 'x') continue;	//범위 밖이거나 방문했었거나 벽이라면 pass
			check[nx][ny] = true;	//방문 표시
			dfs(nx,ny);
			//지나온 자리는 최선이라면 끝까지 간 길일 것이고 최악이라면 끝까지 연결되지 않는 경우다
			//최선의 경우 이미 파이프가 놓여 있어야 하니까 원복하지 않고
			//최악의 경우에도 다시 가봐야 어차피 끝까지 이어지지 않으니까 원복하지 않음
			if(flag) return;	//마지막을 찍고 왔는데 true 값으로 변했다면 끝까지 간거니까 더 이상 다른 방향으로 또 끝까지 가지 않게 탐색하지 않게 종료
		}
		
	}
		
	
	
}