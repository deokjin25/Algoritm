import java.io.*;
import java.util.*;

public class Solution_SWEA_4013 {
	static int T, K;	//총 TC, 각 입력 TC
	static String[] m1, m2, m3, m4;	//자석 배열
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			K = Integer.parseInt(br.readLine());
			m1 = br.readLine().split(" ");
			m2 = br.readLine().split(" ");
			m3 = br.readLine().split(" ");
			m4 = br.readLine().split(" ");	
			String[][] mm = new String[][] {m1,m2,m3,m4};	//전체 자석 배열
			int answer = 0;
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int mIdx = Integer.parseInt(st.nextToken())-1;	//자석 번호
				int clcn = Integer.parseInt(st.nextToken());	//방향
				boolean[] check = new boolean[] {true, true, true, true};
				// 시계 반시계 시계 반시계
				// 반시계 시계 반시계 시계
				
				//돌려야 하는 자석을 돌리면서 주변 자석 탐색
				mm = roll_magnetic(mm, mIdx, clcn, check);
				//자석 확인 코드
//				for (int i = 0; i < 4; i++) {
//					System.out.println(Arrays.toString(mm[i]));
//				}
//				System.out.println();
			}
			int m1S = Integer.parseInt(m1[0]);
			int m2S = Integer.parseInt(m2[0])*2;
			int m3S = Integer.parseInt(m3[0])*4;
			int m4S = Integer.parseInt(m4[0])*8;
			
			answer += m1S+m2S+m3S+m4S;
			System.out.println("#"+tc+" "+answer);
				
		}
	}
	
	static String[][] roll_magnetic(String[][] mm, int mIdx, int clcn, boolean[] check) {
		String[] m = mm[mIdx];
		check[mIdx] = false;
		if(clcn == 1) { 	//시계방향
			if(mIdx -1 >= 0 && !m[6].equals(mm[mIdx-1][2]) && check[mIdx-1]) {
				roll_magnetic(mm, mIdx-1, -1, check);
			}
			
			if(mIdx + 1 < 4 && !m[2].equals(mm[mIdx+1][6]) && check[mIdx+1]) {
				roll_magnetic(mm, mIdx+1, -1, check);
			}
			String tmp = m[7];
			for (int i = 7; i >= 1; i--) {
				m[i] = m[i-1];
			}
			m[0] = tmp;
		}else {		//반시계방향
			if(mIdx -1 >= 0 && !m[6].equals(mm[mIdx-1][2]) && check[mIdx-1]) {
				roll_magnetic(mm, mIdx-1, 1, check);
			}
			if(mIdx + 1 < 4 && !m[2].equals(mm[mIdx+1][6]) && check[mIdx+1]) {
				roll_magnetic(mm, mIdx+1, 1, check);
			}
			String tmp = m[0];
			for (int i = 0; i < 7; i++) {
				m[i] = m[i+1];
			}
			m[7] = tmp;
		}
		
		return mm;
		
	}
}
