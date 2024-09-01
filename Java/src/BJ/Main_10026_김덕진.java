package BJ;

import java.io.*;

public class Main_10026_김덕진{
	static int N, normalCnt, blindCnt;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[][] normal;
	static boolean[][] visitC, visitB;
	static int dx[] = {1,-1,0,0};
	static int dy[] = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception{
		N = Integer.parseInt(br.readLine());
		
		normal = new char[N][N];	//색상표
		for (int i = 0; i < N; i++) {
			normal[i] = br.readLine().toCharArray();
		}
		
		normalCnt = 0;	//정상 색상 인식 카운트
		blindCnt = 0;	//색약 인식 카운트
		visitC = new boolean[N][N];	//같은 색상으로 인식해줄 이진 배열
		visitB = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visitC[i][j]) {		//같은 색으로 인식하지 않은 곳이라면
					rgb(i,j,normal[i][j]);
					normalCnt++;
				}
				if(!visitB[i][j]) {
					gray(i,j,normal[i][j]);
					blindCnt++;
				}
			}
		}
		
		System.out.println(normalCnt+" "+blindCnt);
	}
	
	//DFS
	private static void gray(int x, int y, char color) {
		visitB[x][y] = true;		//인식처리
		for (int k = 0; k < 4; k++) {	//4방에 같은 색이 있는지 확인
			int nx = x + dx[k];
			int ny = y + dy[k];
			if(nx < 0 || nx >= N || ny < 0 || ny >= N || visitB[nx][ny]) continue;	//범위 밖이거나 이미 인색했던 곳이면 pass
			//색약은 색상표에 찍힌게 R이든 G든 구분하지 않음 
			if((color == 'R' || color == 'G') && (normal[nx][ny] == 'R' || normal[nx][ny] == 'G')) {
				gray(nx,ny,color);
			}else if(color == normal[nx][ny]) gray(nx,ny,color);	//위에서 안걸러졌다면 color는 B
		}
	}

	private static void rgb(int x, int y, char color) {
		visitC[x][y] = true;		
		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if(nx < 0 || nx >= N || ny < 0 || ny >= N || visitC[nx][ny]) continue;
			if(normal[nx][ny] == color) rgb(nx, ny, color);

		}
	}

}
