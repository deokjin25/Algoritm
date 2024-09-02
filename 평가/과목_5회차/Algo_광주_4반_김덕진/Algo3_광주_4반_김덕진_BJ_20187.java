import java.io.*;
import java.util.*;

public class Algo3_광주_4반_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int k, h, map[][];
	static char[] command;
	
	public static void main(String[] args) throws Exception {
		k = Integer.parseInt(br.readLine());
		command = br.readLine().toCharArray();
		h = Integer.parseInt(br.readLine());
		int k2 = (int) Math.pow(k,2);
		
		map = new int[k2][k2];
		
		//시작 위치에서의 작은 사각형이 완성되면 나머지 사각형들도 모두 동일 패턴
		int x1 = 0;
		int x2 = k2;
		int y1 = 0;
		int y2 = k2;
		for (int i = 0; i < command.length; i++) {
			if(command[i] == 'D') y1 = y1 + (y2-y1)/2;
			if(command[i] == 'U') y2 = y2/2;
			if(command[i] == 'R') x1 = x1 + (x2-x1)/2;
			if(command[i] == 'L') x2 = x2/2;
		}
		System.out.println(x1);
		System.out.println(x2);
		System.out.println(y1);
		System.out.println(y2);
		for (int x = x1; x < x2; x++) {
			for (int y = y1; y < y2; y++) {
				map[x][y] = h;
			}
		}
		
		for (int i = 0; i < k2; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		
		
		
	}

}
