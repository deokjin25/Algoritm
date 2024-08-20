package BJ;

import java.util.*;

public class Main_2839_김덕진 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		if(N%5 == 0) {
			System.out.println(N/5);
		}else {
			int n5 = N/5;	//5kg 최대개수
			for (int i = n5; i >= -1; i--) {
				if(i == -1) {
					System.out.println(-1);
					break;
				}
				
				if((N-i*5)%3 == 0) {
					System.out.println(i + (N-i*5)/3);
					break;
				}
			}
		}

	}

}
