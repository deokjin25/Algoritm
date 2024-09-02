import java.util.Arrays;
import java.util.Scanner;

public class Test2_김덕진 {

	public static void main(String[] args) {
		//---------여기에 코드를 작성하세요.---------------//
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine(); //줄바꿈
		for(int Ti=0; Ti<T; Ti++) {
			int N = sc.nextInt(); //배열입력
			sc.nextLine(); //줄바꿈
			int rockNum = sc.nextInt(); // 돌 수
			int[] rockPower = new int[rockNum]; //각 돌의 힘 세기 배열
			for(int i=0; i<rockNum; i++) {
				rockPower[i] = sc.nextInt();
			}
			sc.nextLine(); //줄바꿈
			int[] rockPoint = new int[rockNum*2]; //돌 좌표
			for(int i=0; i<rockNum*2; i++) {
				rockPoint[i] = sc.nextInt(); //홀수는 x 짝수는 y
			}
			sc.nextLine(); //줄바꿈			
			
			int[][] arr = new int[N][N]; //연못배열 생성
			
			int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
			int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};
			
			int rockIndex_x=0;
			int rockIndex_y=1;
			
			for(int r=0; r<rockNum; r++) {
				int x = rockPoint[rockIndex_x]-1;
				int y = rockPoint[rockIndex_y]-1;
				arr[x][y] += 1;
				for (int move=0; move < 8; move++) {
					x = rockPoint[rockIndex_x];
					y = rockPoint[rockIndex_y];
					for(int k=0; k<rockNum; k++)
						if (x + dx[move%8] < N && y + dy[move%8] < N && x + dx[move%8] >= 0 && y + dy[move%8] >= 0) {
							x += dx[move%8];
							y += dy[move%8];
							arr[x][y] += 1;
						}else {
							break;
						}
				}
				rockIndex_x += 2;
				rockIndex_y += 2;
			}
			
			
			for(int[] b : arr) {
				System.out.println(Arrays.toString(b));
			}
			
			int answer = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					answer = Math.max(answer, arr[i][j]);
				}
			}
				
			System.out.printf("#%d %d%n",Ti+1,answer);
		}

	}

}
