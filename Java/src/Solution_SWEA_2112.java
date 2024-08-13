import java.io.*;
import java.util.*;

public class Solution_SWEA_2112 {
	static BufferedReader br;
	static StringTokenizer st;
	static int D, W ,K, T;
	static int[][] map;
	static int inject;	//현재까지의 주입 횟수
	static boolean[] check;
	static int[] one;	// 약 주입 시 바꿀 전체 1배열
	static int[] zero;	// 약 주입 시 바꿀 전체 0 배열
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken()); 	//세로(행)
			W = Integer.parseInt(st.nextToken());	//가로(열)
			K = Integer.parseInt(st.nextToken());	//검사 기준
			inject = 0;
			check = new boolean[D]; //행 개수 만큼 약 주입 여부 표시
			one = new int[W]; zero = new int[W];
			Arrays.fill(one, 1);
			
			
			map = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			System.out.println(inspection(map, 0, check));
		}

	}
	
	
	static int inspection(int[][] map, int injection, boolean[] check) {
		if (injection >= K) return K;	//약품 주입 횟수가 검사 기준보다 많으면 의미 없음.
		
		
		
		//열 탐색 및 검사.
		List<int[]> list = new ArrayList<>();	//값이 바뀐 곳의 위치를 기억할 리스트
		for (int i = 0; i < W; i++) {
			int cnt = 0;
			for (int j = 0; j < D-1; j++) {
				if (map[j][i] == map[j+1][i]) {
					cnt++;
					if(cnt == K-1) {	//해당 열 검사 기준 충족
						list.clear();	//해당 열에서의 바뀐 위치는 필요 없으니 초기화
						break;
					}
				}else{	//달라 졌다면
					list.add(new int[]{j+1,i});	//바뀐 위치 기록
					cnt = 0;	//검사 체크 기록 초기화
				}
			}
			if (list.size() > 0) {	//열 탐색 다 돌고 나왔는데 검사 기준 통과 못해서 바뀐 위치가 기록되어 있다면
				break;	//여기까지 걸리지 않고 밑 코드까지 진행되면 초기상태부터 검사 기준 충족임.
			}
			if(i==W-1) return injection;
		}
		
		
		
		//바꿔야 하는 위치에 약품 주입
		for (int[] i : list) {
			int r = i[0];
			int c = i[1];
			int[] origin = map[r];	//원래 행 값, 백 트래킹을 위함. 
			if(map[r][c] == 1 && !check[r]) {	//약품 주입 위치(행) && 이전에 주입한 적 없어야 함(한 곳에 또 해서 뭐해)
				map[r] = zero;
				check[r] = true;
			}else if(map[r][c] == 1 && !check[r]) {
				map[r] = one;
				check[r] = true;
			}			
			injection = Math.min(K, inspection(map, injection++, check));	//약물 주입한 이후의 상황보러 재귀 투입
			map[r] = origin;	//원래 값 복원을 해놔야 다음 바뀔 위치에 대한 약물 주입 시에 중복 오류가 없어짐.
		}

		return injection;
	}
	

	
}
