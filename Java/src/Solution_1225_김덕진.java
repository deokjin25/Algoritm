import java.io.*;
import java.util.*;

public class Solution_1225_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int tc;
	static int[] nums = new int[8];	//무족권 8개 숫자만 들어옴
	
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			tc = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 8; j++) {
				nums[j] =Integer.parseInt(st.nextToken());
			}
			
			cycle();
			
			//테케별로 쌓아 뒀다가 10개 케이스 끝나면 한번에 출력
			sb.append("#").append(tc).append(" ");
			for (int j = 0; j < 8; j++) {
				sb.append(nums[j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);	
	}

	
	private static void cycle() {
		while(true) {
			for (int j = 1; j < 6; j++) {	//한 사이클에 5까지만 감소
				int tmp = nums[0] - j;	//감소 시키고
				//자리 바꾸고
				for (int i = 0; i < 7; i++) {
					nums[i] = nums[i+1];
				}
				nums[7] = tmp;	//끝에 다시 넣어주고
				if (tmp <= 0) {	// 한 사이클 돌다가 0보다 작아지면
					nums[7] = 0;	//0으로 넣어준다고 했음
					return;		//조건 충족시 매서드 종료
				}
			}
		}
		
	}

}
