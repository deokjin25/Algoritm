package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1861_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T,N, map[][], top, maxCnt;
	static int[] arr = new int[2];	//정답 출력할 배열
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			top = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					top = Math.max(top, map[i][j]);
				}
			}
			arr[0] = Integer.MAX_VALUE;	//제일 작은 번호
			maxCnt = Integer.MIN_VALUE;	//최대 방 방문 개수
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j]== top) continue;	//제일 큰 값은 볼 필요 없음.
					arr[1] = search(i,j,1);
					if(arr[1] > maxCnt) {	//탐색된 깊이가 더 크면
						maxCnt = arr[1];
						arr[0] = map[i][j];		//무조건 갱신
					}else if(arr[1] == maxCnt) {
						arr[0] = Math.min(arr[0], map[i][j]);	//독같으면 최소값만 가지고 감
					}
				}			
			}
			
			System.out.println("#" + tc + " " + arr[0] + " " + maxCnt);
			
		}
	}

	private static int search(int i, int j, int cnt) {
		int tmp = cnt;
		
		for (int k = 0; k < 4; k++) {
			int nx = i + dx[k];
			int ny = j + dy[k];
			if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] - map[i][j] != 1) continue;
			tmp = search(nx, ny, cnt+1);	//위 조건에 맞으면 깊이 하나 늘려서 탐색 추가로 진행
		}
		
		return tmp;

	}

}
