package SWEA;

import java.io.*;
import java.util.*;

public class Solution_4012_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] arr,scoreArr;
	static int[][] narr;
	static int T, N;
	static StringBuilder sb  = new StringBuilder();
	static boolean check[];
	static int min;
	
	static List<Integer> food = new ArrayList<>();
	
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			narr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					narr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			food.clear();
			check = new boolean[N];
			arr = new int[N/2];
			min = Integer.MAX_VALUE;
			flavor(0,0);
			diffMin(0,0);
//			System.out.println(Arrays.toString(food.toArray()));
			System.out.println("#"+tc+" "+min);
			
			
		}

	}


	private static void diffMin(int idx, int cnt) {
		//음식 2개 가지고 비교
		//비교군: 하나의 음식을 조합(N/2가지 재료 선택)하면 비교 음식(남은 재료)은 자동으로 정해짐 
		//지금은 flavor에서 모든 조합을 살펴봄, 하지만 순서대로 해놓았기 때문에 좌우 대칭 구조를 이루고 있다.
		//food 리스트에서 0번은 끝 인덱스 조합과, 1번은 끝에서-1 음식 조합을 비교하면 됨.
		for (int i = 0; i < food.size()/2; i++) {
			int A = food.get(i);
			int B = food.get(food.size()-1-i);
			min = Math.min(min, Math.abs(A-B));
		}
			
		
	}


	private static void flavor(int idx, int cnt) {
		if(idx == N/2) {	// N/2만큼의 재료 활용
			// N/2 C 2 에 대한 score환산
			scoreArr = new int[2];
			int score = comb(0,0);
			food.add(score);
			return;
		}
		
		for (int i = cnt; i < N; i++) {

			if(!check[i]) {
				check[i] = true;
				arr[idx] = i;
				flavor(idx+1,i);
				check[i] = false;
			}
		}
	}


	private static int comb(int idx, int cnt) {
		int score = 0;
		if(idx == 2) {
			int i = scoreArr[0];
			int j = scoreArr[1];
			return narr[i][j] + narr[j][i];
			
		}
		
		for (int i = cnt; i < N/2; i++) {
			scoreArr[idx] = arr[i];
			score += comb(idx+1, i);
		}
		
		return score;
	}
	
	
	
	

}
