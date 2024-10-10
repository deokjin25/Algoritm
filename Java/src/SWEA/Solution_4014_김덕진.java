package SWEA;

import java.io.*;
import java.util.*;

public class Solution_4014_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N, X, map[][];
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());	//경사로 길이
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int ans = 0;
			//한 줄 확인할 때마다 가능한 경우의 수 카운트
			for (int i = 0; i < N; i++) {
				if(row(i)) ans++;
				if(col(i)) ans++;
			}
			
			System.out.println("#" + tc + " " + ans);
			
		}
		
	}

	private static boolean col(int i) {
		//경사로를 놓은 곳은 true처리(중복 경사로를 피하기 위해)
		boolean visit[][] = new boolean[N][N];
		
		for (int j = 0; j < N-1; j++) {
			//인접 칸이 2칸 차이나면 아예 안됨(경사로는 1칸 차이만 가능)
			if(Math.abs(map[j][i] - map[j+1][i]) > 1) return false;
			
			//올라갈 때, 왼쪽(j-k)에 경사로를 놓을 수 있는지 확인
			if(map[j][i] < map[j+1][i]) {
				for (int k = 0; k < X; k++) {
					//경사로를 놓다가 범위를 벗어나거나, 경사로 길목에 높이 차가 나거나, 이미 경사로를 놓은 곳인 경우 안됨
					if(j-k < 0 || map[j][i] != map[j-k][i] || visit[j-k][i]) return false;
					visit[j-k][i] = true;
				}
			}
			//내려갈 때, 오른쪽(j+1+k)에 경사로를 놓을 수 있는지 확인
			else if(map[j][i] > map[j+1][i]) {
				for (int k = 0; k < X; k++) {
					//경사로를 놓다가 범위를 벗어나거나, 경사로 길목에 높이 차가 나거나, 이미 경사로를 놓은 곳인 경우 안됨
					if(j+1+k >= N || map[j+1][i] != map[j+1+k][i] || visit[j+1+k][i]) return false;
					visit[j+1+k][i] = true;
				}
			}
			
		}
		//끝까지 이동했다면 활주로 공사가 가능한 경우
		return true;
	}


	private static boolean row(int i) {
		//경사로를 놓은 곳은 true처리(중복 경사로를 피하기 위해)
		boolean visit[][] = new boolean[N][N];
		
		for (int j = 0; j < N-1; j++) {
			//인접 칸이 2칸 차이나면 아예 안됨(경사로는 1칸 차이만 가능)
			if(Math.abs(map[i][j] - map[i][j+1]) > 1) return false;
			
			//올라갈 때, 왼쪽(j-k)에 경사로를 놓을 수 있는지 확인
			if(map[i][j] < map[i][j+1]) {
				for (int k = 0; k < X; k++) {
					//경사로를 놓다가 범위를 벗어나거나, 경사로 길목에 높이 차가 나거나, 이미 경사로를 놓은 곳인 경우 안됨
					if(j-k < 0 || map[i][j] != map[i][j-k] || visit[i][j-k])  return false;
					visit[i][j-k] = true;
				}
				
			}
			
			//내려갈 때, 오른쪽(j+1+k)에 경사로를 놓을 수 있는지 확인
			else if(map[i][j] > map[i][j+1]) {
				for (int k = 0; k < X; k++) {
					//경사로를 놓다가 범위를 벗어나거나, 경사로 길목에 높이 차가 나거나, 이미 경사로를 놓은 곳인 경우 안됨
					if(j+1+k >= N || map[i][j+1] != map[i][j+1+k] || visit[i][j+1+k])  return false;
					visit[i][j+1+k] = true;
				}
			}
			
		}
		//끝까지 이동했다면 활주로 공사가 가능한 경우
		return true;
	}

}
