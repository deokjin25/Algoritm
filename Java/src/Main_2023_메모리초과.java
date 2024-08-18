import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2023_메모리초과 {
	
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());		
		
		int maxN = (int) Math.pow(10, N);
		//체, false면 소수
		boolean[] map = new boolean[maxN];
		map[0] = map[1] = true;
		for (int i = 2; i < Math.sqrt(maxN); i++) {
			if(!map[i]) {
				for (int j = i+i; j < maxN; j+=i) {
					map[j] = true;
				}
			}
		}
		
		//10^4 = 1000
//		StringBuilder sb = new StringBuilder();
		for (int i = (int) Math.pow(10, N-1); i < Math.pow(10, N); i++) {
			boolean tf = true;
			for (int j = (int) Math.pow(10, N-1) ; j >= 1; j = j/10) {
				if(map[i/j]) {
					tf = false; 
					break;
				}
			}
			
//			if(tf) sb.append(i+"\n");
			if(tf) System.out.println(i);	
		}
		
//		System.out.println(sb);
		
	}
}
