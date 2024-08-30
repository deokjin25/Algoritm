package SWEA;

import java.io.*;
import java.util.*;
/*
 * 모든 core의 방향 순열 살피기
 * 어떤 코어가 먼저 전선 뻗을건지에 대한 순열(최대 12! >> 가지치기 필수로)
 * ^ 한 코어가 길을 뻗으면 다른 코어가 교차해서 못가니까 생각한건데 그냥 아예 안 뻗는 경우를 넣어주면 될 듯
 */
public class Solution_1767_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N, map[][], simulMap[][], arr[], coreN, power, lenght, maxPower, minLenght;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static List<int[]> corePostion;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			corePostion = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(i > 0 && j > 0 && i <N-1 && j < N-1 && map[i][j] == 1) corePostion.add(new int[] {i,j});
				}
			}
			
			coreN = corePostion.size();
			arr = new int[coreN];
			maxPower = Integer.MIN_VALUE;
			minLenght = Integer.MAX_VALUE;
			subSet(0, true);
			subSet(0, false);
			System.out.println("#" + tc + " " + minLenght);
		}
	}

	private static void subSet(int idx, boolean tf) {
		if(power + coreN-idx < maxPower) return;
		
		if(idx == coreN) {
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(simulMap[i]));
//			}
//			System.out.println();
			
			if(power > maxPower) {
				maxPower = power;
				minLenght = lenght;
			}else if(power == maxPower) {
				minLenght = Math.min(minLenght, lenght);
			}
			return;
		}
		
		
		if(tf) {
			for (int i = 0; i < 4; i++) {
				if(idx == 0) {
					power = 0;
					lenght = 0;
					simulMap = new int[N][N];
					for (int j = 0; j < N; j++) {
						simulMap[i] = map[i].clone();
					}
				}
				
				int x = corePostion.get(idx)[0];
				int y = corePostion.get(idx)[1];
				
				dfs(x,y,i,0);
				
				subSet(idx+1, true);
				subSet(idx+1, false);
			}
		}

	}


	private static boolean dfs(int x, int y, int direction, int len) {
		if(x == 0 || y == 0 || x == N-1 || y == N-1) {
			power++;
			lenght += len;
			return	true;
		}
		
		boolean isEnd = false;
		
		int nx = x + dx[direction];
		int ny = y + dy[direction];
		
		if(simulMap[nx][ny] == 0) {
			simulMap[nx][ny] = 1;
			isEnd = dfs(nx, ny, direction, len+1);
			if(!isEnd) simulMap[nx][ny] = 0;
		}
		
		return isEnd;
	}


	
	
	
	
	
	
	
	
	
	
	
}
