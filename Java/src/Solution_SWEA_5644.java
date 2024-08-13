import java.io.*;
import java.util.*;

public class Solution_SWEA_5644 {
	static int T, M, A;	//총 테스트 케이스, 사용자 이동 정보, BC 개수
	static int[] moveA = new int[100];	//사용자A의 이동 정보
	static int[] moveB = new int[100]; //사용자B의 이동 정보
	static List<int[]> bcSet;	//add 처리 할거면 테케마다 clear 필요
	static int[] bc = new int[4];	// x(열),y(행), 충전 거리, 충전 세기
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringTokenizer st2;
	static int map[][];
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T+1; tc++) {
			bcSet.clear();
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			st2 = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
				moveB[i] = Integer.parseInt(st2.nextToken());
			}
			
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					bc[j] = Integer.parseInt(st.nextToken());
				}
				bcSet.add(bc);
			}
			
			bcSpread(bcSet, A);
			
		}

	}

	private static void bcSpread(List<int[]> bcSet, int A) {
		for (int i = 0; i < A; i++) {
			bc = bcSet.get(i);
			int r = bc[1];	//행
			int c = bc[0];	//열
			int d = bc[2];	//충전 거리
			int power = bc[3];	//충전 세기
		}
		
	}
	
	
	

}
