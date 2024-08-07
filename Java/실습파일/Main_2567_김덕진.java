import java.util.*;
import java.io.*;

public class Main_2567_김덕진 {

	public static void main(String[] args) throws IOException{
		int[][] arr = new int[101][101];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<100; i++) {
			Arrays.fill(arr[i], 0); //도화지 0으로 초기화
		}
		
		int N = Integer.parseInt(br.readLine()); //입력받는 색종이 수
		
		for(int n=0; n<N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());  //색종이 x좌표
			int y = Integer.parseInt(st.nextToken());  //색종이 y좌표
			for(int i=x; i<x+10; i++) {
				for(int j=y; j<y+10; j++) {
					if (arr[i][j] == 0) {  //색종이가 들어가 있지 않으면
						arr[i][j] = 1;  //1로 칠하기
					}
				}
			}		
			
		}
	
		//0과 1이 만나는 지점이 둘레
		int cnt = 0; //둘레 카운트
		for(int i=1; i<100; i++) {
			for(int j=1; j<100; j++) {
				if(arr[i][j] == 0 && arr[i-1][j] == 1) cnt++;  //아래가 0이고 위가 1일 때
				if(arr[i][j] == 0 && arr[i+1][j] == 1) cnt++;  //위가 0이고 아래가 1일 때
				if(arr[i][j] == 0 && arr[i][j-1] == 1) cnt++;  //오른쪽이 0이고 왼쪽이 1일 때
				if(arr[i][j] == 0 && arr[i][j+1] == 1) cnt++;	// 왼쪽이 0이고 오른쪽이 1일 때
			}
		}
		
		System.out.println(cnt);
		
	}
}
