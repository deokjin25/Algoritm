import java.io.*;
import java.util.*;

public class Solution_SWEA_5644 {
	static int T, M, A;	//총 테스트 케이스, 사용자 이동 정보, BC 개수
	static int[] moveA = new int[100];	//사용자A의 이동 정보
	static int[] moveB = new int[100]; //사용자B의 이동 정보
	static List<int[]> bcSet = new ArrayList<>();	//add 처리 할거면 테케마다 clear 필요
	static int[] bc;	// x(열),y(행), 충전 거리, 충전 세기
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringTokenizer st2;
	static int map[][] = new int [10][10];	//가로, 세로, 그 자리에 있는 BC 배열
	/***	 
	<제약 사항> 
	1. 지도의 가로, 세로 크기는 10이다.
	2. 사용자는 총 2명이며, 사용자A는 지도의 (1, 1) 지점에서, 사용자B는 지도의 (10, 10) 지점에서 출발한다.
	>> [0][0]에서 출발, [9][9]에서 출발
	3. 총 이동 시간 M은 20이상 100이하의 정수이다. (20 ≤ M ≤ 100)
	4. BC의 개수 A는 1이상 8이하의 정수이다. (1 ≤ A ≤ 8)
	5. BC의 충전 범위 C는 1이상 4이하의 정수이다. (1 ≤ C ≤ 4)
	6. BC의 성능 P는 10이상 500이하의 짝수이다. (10 ≤ P ≤ 500)
	7. 사용자의 초기 위치(0초)부터 충전을 할 수 있다.
	8. 같은 위치에 2개 이상의 BC가 설치된 경우는 없다. 그러나 사용자A, B가 동시에 같은 위치로 이동할 수는 있다. 사용자가 지도 밖으로 이동하는 경우는 없다.
	 * D = |XA – XB| + |YA – YB|
	 * BC 행렬 바뀌어 있음
	 *
	 *	
	 */
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
				bc = new int[4];
				for (int j = 0; j < 4; j++) {
					bc[j] = Integer.parseInt(st.nextToken());
				}
				bcSet.add(bc);
			}
			
			bcSpread(A);
			
			//충전 범위 확인 코드
//			for (int i = 0; i < 10; i++) {
//				for (int j = 0; j < 10; j++) {
//					System.out.printf("%d	",map[i][j]);
//				}
//				System.out.println();
//			}
			
			
			
			
		}

	}

	private static void bcSpread(int A) {
		for (int k = 0; k < A; k++) {
			bc = bcSet.get(k);
			System.out.println(Arrays.toString(bc));
			int r = bc[1]-1;	//행
			int c = bc[0]-1;	//열
			int d = bc[2];	//충전 거리
			int power = bc[3];	//충전 세기
			
			for (int i = r-d; i < r-d+d*2+1; i++) {
				for (int j = c-d; j < c-d+d*2+1; j++) {
					if(i>=0 && j>= 0 && i < 10 && j < 10 && d >= Math.abs(i-r) + Math.abs(j-c)) {
						map[i][j] += power;
					}
				}
			}
		}
	}
	

	
	
	
	
	
	
	
	

}