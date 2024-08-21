package SWEA;

import java.io.*;
import java.util.*;

/*
 * 짝수번째 있는 행/열을 -1 번째에 합치고
 * 정리하는 매서드로 다 옮기기(큐)
 */
public class Solution_6109_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;
	static int N;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			String direction = st.nextToken();
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}  
			}
			
			organize(direction);	//정리먼저 하고(숫자 먼저 다 땡기고)
			merge(direction);	//조건 맞춰서 합치고
			organize(direction);	//중간 중간 다시 0 생기니까 다시 땡기고
			sb.append("#" + tc + "\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(map[i][j] + " ");
				}
				sb.append("\n");
			}
			
		}
		System.out.println(sb);
	}

	private static void organize(String direction) {
		Queue<Integer> q = new LinkedList<>();	//탐색 전부 할 필요 없이 그 줄에 있는 숫자 만큼만 수행하기 위해서 큐 사용
		switch(direction) {
		case "up" :
			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N; i++) {
					if(map[i][j] == 0) continue;	//0이면 볼 필요 없음
					q.offer(map[i][j]);	//q에 담고
					map[i][j] = 0;	//그 자리 0 처리
				}
				//다음 줄로 넘어가기 전에 채워주기
				int x = 0;
				while(!q.isEmpty()) {	//q에 들어 있는 만큼만
					int tmp = q.poll();	//값 빼서 끝에서 부터 하나씩 넣어주기
					map[x][j] = tmp;
					x++;
				}
			}
			break;
		//이하 방향만 바뀌고 논리구조 똑같음
		case "down" :
			for (int j = 0; j < N; j++) {
				for (int i = N-1; i >= 0; i--) {
					if(map[i][j] == 0) continue;
					q.offer(map[i][j]);
					map[i][j] = 0;
				}
				
				int x = N-1;
				while(!q.isEmpty()) {
					int tmp = q.poll();
					map[x][j] = tmp;
					x--;
				}
			}
			break;
		case "left" :
			for (int i = 0; i < N; i++) {
				for (int j = 0 ; j < N; j++) {
					if(map[i][j] == 0) continue;
					q.offer(map[i][j]);
					map[i][j] = 0;
				}
				
				int x = 0;
				while(!q.isEmpty()) {
					int tmp = q.poll();
					map[i][x] = tmp;
					x++;
				}
			}
			break;
		case "right" :
			for (int i = 0; i < N; i++) {
				for (int j = N-1 ; j >= 0; j--) {
					if(map[i][j] == 0) continue;
					q.offer(map[i][j]);
					map[i][j] = 0;
				}
				
				int x = N-1;
				while(!q.isEmpty()) {
					int tmp = q.poll();
					map[i][x] = tmp;
					x--;
				}
			}
			break;
		}
		
	}

	private static void merge(String direction) {
		switch(direction) {
		case "up" :
			for (int i = 0; i < N; i ++) {
				if(i+1 >= N) break;		//더해줘야 하는 밑줄이 범위를 넘어가면 종료
				for (int j = 0; j < N; j++) {
					if (map[i][j] == map[i+1][j]) {		//해당 자리랑 밑줄의 값이 똑같다면 더해주기
						map[i][j] += map[i+1][j];
						map[i+1][j] = 0;	//끌려온 자리는 0 처리
					}
				}
			}
			break;
		//이하 방향만 바뀌고 논리구조 똑같음
		case "down" :
			for (int i = N-1; i >=0; i--) {
				if(i-1 < 0) break;
				for (int j = 0; j < N; j++) {
					if(map[i][j] == map[i-1][j]) {
						map[i][j] += map[i-1][j];
						map[i-1][j] = 0;
					}
				}
			}
			break;
		case "left" :
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(j+1 >= N) break;
					if(map[i][j] == map[i][j+1]) {
						map[i][j] += map[i][j+1];
						map[i][j+1] = 0;
					}
				}
			}
			break;
		case "right" :
			for (int i = 0; i < N; i++) {
				for (int j = N-1; j >= 0; j --) {
					if(j-1 < 0) break;
					if(map[i][j] == map[i][j-1]) {
						map[i][j] += map[i][j-1];
						map[i][j-1] = 0;
					}
				}
			}
			break;
		}
	}

}
