package BJ;
import java.util.*;
public class Main_BJ_2635 {

	public static void main(String[] args) {
//		첫 번째 수로 양의 정수가 주어진다.
//		두 번째 수는 양의 정수 중에서 하나를 선택한다.
//		세 번째부터 이후에 나오는 모든 수는 앞의 앞의 수에서 앞의 수를 빼서 만든다.
//		음의 정수가 만들어지면, 이 음의 정수를 버리고 더 이상 수를 만들지 않는다.
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int maxCnt = 0;
		
		int[] test = new int[2];
		int tmp;
		int maxTmp=2;
		int maxIdx=0;
		for(int i=N/2; i<N; i++) {
			if(i==0) i = 1;
			test[1] = i;
			test[0] = N;
			maxTmp=2;
			while(true) {
				tmp = test[0] - test[1];
				if(tmp < 0) {
					break;
				}else {
					maxTmp++;
					test[0] = test[1];
					test[1] = tmp;
				}
			}
			if(maxTmp > maxCnt) {
				maxIdx = i;
				maxCnt = maxTmp;
			}
		}
		
		int[] end = new int[maxCnt];
		end[0] = N;
		end[1] = maxIdx;
		for(int i=2; i<maxCnt; i++) {
			end[i] = end[i-2] - end[i-1];
		}
		System.out.println(maxCnt);
		for(int i=0; i<maxCnt; i++) {
			System.out.printf("%d ", end[i]);
		}
	}
}
