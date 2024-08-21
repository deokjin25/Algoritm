package SWEA;

import java.util.Scanner;

public class Solution_6782_김덕진 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			long N = sc.nextLong();		//입력이 10^12까지 들어오니까 long타입으로 받기
			int cnt = 0;
			
			//tmp: N의 제곱근
			while(true) {

				if(N == 2) {
					break;
				}
				long tmp = (long) Math.sqrt(N);
				if(N == Math.pow(tmp, 2)) {	//제곱근이 정수라면(제곱근의 정수부를 제곱한 값이 N이랑 똑같다면 N의 루트 씌운 값(tmp)은 소수가 없는 정수)
					N = tmp;	//제곱근 값으로 변경
					cnt++;
				}else {		//제곱근이 정수가 아니면 다음 제곰근 만큼 N을 증가시켜줘야함
					// tmp(N의 제곱근)의 정수부를 +1 시킨 뒤에 제곱한 수만큼 N을 증가시켜줘야함 
					// +1 방식은 너무 오래 걸릴 수 있으니 차이를 바로 구해서 cnt에 더해주기
					cnt += Math.pow(((long) tmp)+1, 2) - N; 	
					N = (long) Math.pow(((long) tmp)+1, 2);
				}
				
				
			}
			
			System.out.println("#"+tc+" "+cnt);
			
		}
	}

}
