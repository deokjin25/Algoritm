package BJ;
import java.io.*;
import java.util.*;


public class Main_BJ_13335 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	//트럭의 개수
		int W = Integer.parseInt(st.nextToken());	//다리 길이
		int L = Integer.parseInt(st.nextToken());	//다리의 최대하중
		Queue<Integer> bridge = new LinkedList<>();
		for(int i=0; i<W; i++) {
			bridge.offer(0);			
		}

		String[] truck = br.readLine().split(" ");
		
		int idx = 0;
		int weight = 0;
		int answer = 0;
		while(true) {
			weight -= bridge.poll(); answer++;
			int nextTruck = Integer.parseInt(truck[idx]);
			if(weight+nextTruck <= L) {
				weight += nextTruck;
				bridge.offer(nextTruck);
				idx++;
				if(idx == N) {
					answer += W;
					break;
				}
			}else{
				bridge.offer(0);
			}
		}
		System.out.println(answer);
	}
}
