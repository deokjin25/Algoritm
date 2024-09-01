package SWEA;

import java.io.*;
import java.util.*;
/*
 * 1: 모든 core의 방향 순열 살피기 >> 시간초과
 * 어떤 코어가 먼저 전선 뻗을건지에 대한 순열(최대 12! >> 가지치기 필수로)
 * ^ 한 코어가 길을 뻗으면 다른 코어가 교차해서 못가니까 생각한건데 그냥 아예 안 뻗는 경우를 넣어주면 될 듯 >> 5^12 경우의 수
 * 
 * 2: 한 코어에 대해 방향을 정한 뒤 dfs 수행하고 다음 코어 살펴보기
 * 남은 코어 모조리 다 합쳐도 최대 코어보다 못할 때 가지치기
 */
public class Solution_1767_김덕진{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N, coreN, power, lenght, maxPower, minLenght;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static List<int[]> corePostion;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			int[][] map = new int[N][N];
			corePostion = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(i > 0 && j > 0 && i <N-1 && j < N-1 && map[i][j] == 1) corePostion.add(new int[] {i,j});
				}
			}
			
			coreN = corePostion.size();
			maxPower = Integer.MIN_VALUE;
			minLenght = Integer.MAX_VALUE;
			subSet(0, true, map);
			subSet(0, false, map);
			System.out.println("#" + tc + " " + minLenght);
		}
	}

	private static void subSet(int idx, boolean tf, int[][] map) {
		if(power + coreN-idx < maxPower) return;	//현재까지 연결된 core + 남은 코어 < 갱신된 최대 연결 코어
		
		if(idx == coreN) {	//모든 코어에 대한 선택 작업 및 방향 배열 탐색 종료
			
			//최대 전원 개수 갱신 및 전선 길이 갱신
			if(power > maxPower) {
				maxPower = power;
				minLenght = lenght;
			}else if(power == maxPower) {
				minLenght = Math.min(minLenght, lenght);
			}
			return;
		}
		
		if(tf) {	//선택 했다면
			for (int i = 0; i < 4; i++) {	//4방향 중 하나에 대한 dfs
				//방향 바뀔때마다 "이전" map(subSet의 파라미터 값)에 대해서 dfs 수행해야 함
				int[][] simulMap = new int[N][N];
				for (int j = 0; j < N; j++) {
					simulMap[j] = map[j].clone();
				}
				
				if(idx == 0) {	//처음 코어라면 방향 바뀔때마다 전력과 길이 초기화
					power = 0;
					lenght = 0;
				}
				
				int nx = corePostion.get(idx)[0] + dx[i];
				int ny = corePostion.get(idx)[1] + dy[i];
				
				if(simulMap[nx][ny] == 0) {
					dfs(nx,ny,i,1, simulMap, idx);
				}
				
			}
		}else {
			subSet(idx+1, true, map);
			subSet(idx+1, false, map);
		}


	}


	private static boolean dfs(int x, int y, int direction, int len, int map[][], int idx) {
		map[x][y] = 1;
		
		if(x == 0 || y == 0 || x == N-1 || y == N-1) {
			power++;
			lenght += len;
			subSet(idx+1, true, map);
			subSet(idx+1, false, map);
			lenght -= len;
			power--;
			return	true;
		}
		
		boolean isEnd = false;
		
		int nx = x + dx[direction];
		int ny = y + dy[direction];
		
		if(map[nx][ny] == 0) {
			isEnd = dfs(nx, ny, direction, len+1, map, idx);
		}
		
		map[x][y] = 0;
		
		return isEnd;
	}


	
	
	
	
	
	
	
	
	
	
	
}
