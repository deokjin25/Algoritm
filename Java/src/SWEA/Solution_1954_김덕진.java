package SWEA;
import java.util.*;

public class Solution_1954_김덕진 {
	static int map[][];

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine();
		
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			sc.nextLine();

			snail(N);
			
			System.out.println("#"+tc);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.printf("%d ",map[i][j]); 
				}
				System.out.println();
			}
		}

	}
	
	static void snail(int N) {
		map = new int[N][N];
		
		int[] dx = {0,1,0,-1};
		int[] dy = {1,0,-1,0};
		
		int n = 1;	//배열 삽입값
		int x=0;	//배열 탐색 좌표	
		int y=0;	
		int direction=0;	//배열 탐색 방향 지정 값
		int block = 0;	//탐색이 안되는 지점 카운트
		
		while(true) {
			map[x][y] = n;
			int nx = x+dx[direction%4];
			int ny = y+dy[direction%4];
			
			if( nx<N && ny< N && ny>=0 && nx>=0) {
				if (map[nx][ny] == 0) {	//가야할 방향이 0, 아무것도 없으면 나아갈 수 있음
					x = nx;
					y = ny;
					block=0;
					n++;	//좌표값 수정해주고 막힌 값 초기화, 들어갈 값 하나 올려서 다시 시작
				}else {	//갈 방향에 0이 아닌 값, 이미 값이 들어간 곳, 방향 전환.
					block++;
					if(block==3) break;	//방향을 3번이나 바꾼건 더이상 갈 곳이 없는 것이므로 종료.	
					direction++; 	//방향 바꿔서 다시 시작
				}
			}else {	//벽을 만나면
				direction++;
				block++;
				if(block==3) break;	//1일때를 위한 코드
			}
		}
	}

}
