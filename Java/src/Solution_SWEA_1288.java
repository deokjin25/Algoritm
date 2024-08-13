import java.io.*;
import java.util.Arrays;

public class Solution_SWEA_1288 {

	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int N = Integer.parseInt(br.readLine());	//시작 양
			int NN = N;
			int cnt = 1;	//양 횟수
			int[] arr1 = new int[10]; 	//0~9 count 배열
			int[] arr2 = new int[10]; 	//대 채웠을 때의 비교 배열
			Arrays.fill(arr2, 1);
			while(true) {
				String SNN = String.valueOf(NN);
				for (int i = 0; i < SNN.length(); i++) {
					if(SNN.charAt(i)-'0' == 0) {
						arr1[9] = 1;
					}else {
						arr1[SNN.charAt(i)-'0' - 1] = 1;						
					}
				}
				
				if(Arrays.equals(arr1, arr2)) {
					break;
				}
				
				NN = N*++cnt;
			}
			System.out.printf("#%d %d%n",test_case+1,NN);
		}
	}
}
