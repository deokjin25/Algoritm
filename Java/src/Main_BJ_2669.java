import java.util.*;
import java.io.*;

public class Main_BJ_2669 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] xy = new int[4]; //왼쪽 밑 x,y 오른쪽위 x,y
		int[][] arr = new int[101][101]; //전체 배열
		int answer = 0;
		
		for(int k=0; k<4; k++) {
			String[] tmp = br.readLine().split(" ");
			for (int i = 0; i < 4; i++) {
				xy[i] = Integer.parseInt(tmp[i]);
			}
			
			// xy[2] - xy[0] 위 아래 변의 길이
			// xy[3] - xy[1] 좌우 변의 길이
			for(int i=xy[0]; i<xy[2]; i++) {
				for(int j=xy[1]; j<xy[3]; j++) {
					if (arr[i][j] == 0) arr[i][j] = 1;
				}
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j] == 1) answer++;
			}
		}
		System.out.println(answer);
	}

}
