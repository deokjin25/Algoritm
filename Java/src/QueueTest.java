import java.util.*;

public class QueueTest {
	public static void main(String[] args) {
		int N = 20;
		Queue<int[]> queue = new ArrayDeque<>(); //사람의 번호와 받아야하는 마이쮸개수 큐에 넣어서 관리
		int person = 1;
		queue.offer(new int[] {person,1});
		
		while(N>0) {
			int[] p = queue.poll();
			int availableCnt = N >=p[1]?p[1]:N;
			N -= availableCnt;
			if(N==0) { //마지막 마이쮸를 받아가는 상황
				System.out.println("마지막 마이쮸를 포함해서 가져가는 사람은 "+ + p[0]+ "번이며 " + availableCnt + "만큼 가져갑니다.");
			}else {
				System.out.println(p[0]+"번이 "+availableCnt+"만큼 가져갑니다. 남은 개수" + N);
				p[1]++;
				queue.offer(p);
				queue.offer(new int[] {++person, 1});
			}
		}
		
		
		
	}
}
