import java.io.*;
import java.util.*;

public class Main_11660_김덕진 {

	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 	//배열의 크기
		int M = Integer.parseInt(st.nextToken());	//합을 구해야 하는 횟수
		
		int[][] arr = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}			
		}
		
		
		//누적합 배열
		int[][] map = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <=  N; j++) {
				map[i][j] = map[i-1][j] + map[i][j-1] - map[i-1][j-1] + arr[i][j];
			}
			 
		}
		
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			System.out.println(map[x2][y2] - map[x1-1][y2] -map[x2][y1-1] + map[x1-1][y1-1]);
			
		}
		
		
	}

}
