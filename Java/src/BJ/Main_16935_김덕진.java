package BJ;

import java.io.*;
import java.util.*;
public class Main_16935_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M,R;
	static int[][] map;
	static int[] arr;	//command 배열
	static Deque<Integer> dq = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		arr = new int[R];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < R; i++) {
			command(arr[i]);			
		}
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.printf("%d ", map[i][j]);
			}
			System.out.println();
		}
		
		
	}

	private static void command(int idx) {
		
		if(idx == 1) upDown();
		if(idx == 2) leftRight();
		if(idx == 3) roll90();
		if(idx == 4) roll270(); //3번 입력 3번하면 같은 결과
		if(idx == 5) partRoll90();
		if(idx == 6) partRoll270();	//5번 3번하면 같은 결과
		
		
	}

	private static void partRoll270() {
		for (int i = 0; i < 3; i++) {
			partRoll90();
		}
	}

	private static void partRoll90() {
		int x = map.length;	//행 길이
		int y = map[0].length;	//열 길이

		//문제에서 주어진 4분면 기준임.
		for (int i = 0; i < x/2; i++) {	//1사분면
			for (int j = 0; j < y/2; j++) {
				dq.addLast(map[i][j]);
			}
		}
		
		for (int i = 0; i < x/2; i++) {	//2사분면
			for (int j = y/2; j < y; j++) {
				dq.addLast(map[i][j]);
				map[i][j] = dq.pollFirst();		//1사분면에서 뺀 값 그대로 넣어주기
			}
		}
		
		for (int i = x/2; i < x; i++) {	//3사분면
			for (int j = y/2; j < y; j++) {
				dq.addLast(map[i][j]);
				map[i][j] = dq.pollFirst();
			}
		}
		
		for (int i = x/2; i < x; i++) {	//4사분면
			for (int j = 0; j < y/2; j++) {
				dq.addLast(map[i][j]);
				map[i][j] = dq.pollFirst();
			}
		}
		
		for (int i = 0; i < x/2; i++) {	//4사분면에서 넣은거 1사분면에 다시 넣어주기
			for (int j = 0; j < y/2; j++) {
				map[i][j] = dq.pollFirst();
			}
		}
		
	}

	private static void roll270() {
		for (int i = 0; i < 3; i++) {
			roll90();
		}
		
	}

	private static void roll90() {
		//행열 뒤바뀜
		int tmp = N;
		N = M;
		M = tmp;
		
		//행열 바뀐 배열
		int tmpArr[][] = new int[N][M];
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				dq.addLast(map[i][j]);
			}
		}
		
		for (int j = M-1; j >= 0; j--) {
			for (int i = 0; i < N; i++) {
				tmpArr[i][j] = dq.pollFirst();
			}
		}
		
		//다른 command에서 map을 가지고 실행하기 때문에 map에 tmpArr 참조하도록 설정
		map = tmpArr;
	}

	private static void leftRight() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dq.addLast(map[i][j]);
			}
		}
		
		for (int i = N-1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				map[i][j] = dq.pollLast();
			}
		}
	}

	
	private static void upDown() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dq.addLast(map[i][j]);
			}
		}
		
		for (int i = N-1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				map[i][j] = dq.pollFirst();
			}
		}
	}

}
