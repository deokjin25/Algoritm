package algo;
import java.util.*;
import java.io.*;
public class Algo1_광주_4반_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			
			int N = Integer.parseInt(st.nextToken());	//종이컵 개수
			int X = Integer.parseInt(st.nextToken());	//처음 간식이 들어있는 위치
			int K = Integer.parseInt(st.nextToken());	//바꾸는 횟수
			
			int[] cup = new int[N+1];	//1번 인덱스를 1번 종이컵으로 쓰기 위해서 N+1 
			cup[X] = 1;
			
			int[][] change = new int[K][2];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 2; j++) {
					change[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int cnt = 0;	//변경 횟수 카운트
			while(cnt!=K) {

				if(cup[change[cnt][0]] == 1 || cup[change[cnt][1]] == 1) {	//변경 하려고 하는 컵에 간식이 있다면
					//둘중 어디 있는지는 모르지만 일단 스왑 (다른 경우의 스왑은 고려대상이 아님)
					int tmp = cup[change[cnt][0]];
					cup[change[cnt][0]] = cup[change[cnt][1]];
					cup[change[cnt][1]] = tmp;
				}
				cnt++;
			}
			
			//1이 있는 위치 찾아서 출력 및 종료.
			for (int i = 0; i < N; i++) {
				if(cup[i] == 1) {
					System.out.println("#" + tc + " " + i);
					break;
				}
			}						
			
		}

	}

	

}
