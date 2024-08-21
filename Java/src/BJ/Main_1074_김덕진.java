package BJ;
import java.io.*;
import java.util.*;
public class Main_1074_김덕진 {
	/*
	 * 4구간을 쪼갰을 때 r이랑 c가 어디에 속하는지 파악
	 * 1,2,3,4 분면일 때 세야할 개수가 각각 2^N관련해서 나옴
	 * >> 1분면일땐 0부터 / 2분면일때 2^N*2^N/4 부터 시작 / 3분면일때 2^N*2^N/4 * 2부터 시작 / 4분면일때 2^N*2^N/4*3부터 시작
	 * 이전 값 계속 더하면서 반복.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int r = sc.nextInt();	//행
		int c = sc.nextInt();	//열
		int cnt = 0;
		double x = Math.pow(2, N)/ 2;	//비교 가로줄
		double y = Math.pow(2, N)/ 2;	//비교 세로줄
		
		//각각의 행/열 길이는 N
		double size = Math.pow(2,N) * Math.pow(2,N); 	//맵 전체 크기
		while(size > 4) {
			if(r < x && c < y) {	//1분면
				N--;	//2^N 감소
				size = Math.pow(2,N) * Math.pow(2,N);
				x -= Math.pow(2, N)/2;
				y -= Math.pow(2, N)/2;
			}else if(r < x && c >= y) {	//2분면
				cnt += size/4;
				N--;
				size = Math.pow(2,N) * Math.pow(2,N);
				x -= Math.pow(2, N)/2;
				y += Math.pow(2, N)/2;
			}else if(r>= x && c < y) {	//3분면
				cnt += size/4*2;
				N--;	//2^N 감소
				size = Math.pow(2,N) * Math.pow(2,N);
				x += + Math.pow(2, N)/ 2;
				y -= Math.pow(2, N)/2;
			}else if(r>=x && c >= y) {	//4분면
				cnt += size/4*3;
				N--;	//2^N 감소
				size = Math.pow(2,N) * Math.pow(2,N);
				x += Math.pow(2, N)/ 2;
				y += Math.pow(2, N) / 2;
			}
			
			
		}

		//마지막(size가 4, 최소 박스일 때)
		if(r < x && c < y) {	//1분면
			//아무것도 안함
		}else if(r < x && c >= y) {	//2분면
			cnt += 1;
		}else if(r>= x && c < y) {	//3분면
			cnt += 2;
		}else if(r>= x && c >= y) {	//4분면
			cnt += 3;
		}
		
		System.out.println(cnt);
		
	}

}
