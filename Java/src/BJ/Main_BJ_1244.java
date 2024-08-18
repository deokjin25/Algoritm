package BJ;
import java.io.*;
import java.util.Arrays;
public class Main_BJ_1244 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int SN = Integer.parseInt(br.readLine()); //스위치 개수
		String[] sarr = br.readLine().split(" "); // 스위치 배열
		int HN = Integer.parseInt(br.readLine()); //학생 수
		for (int i = 0; i < HN; i++) {
			String[] a = br.readLine().split(" ");//학생 성별, 학생에 따라 수행할 숫자
			if(a[0].equals("1")) {	//남자
				int x = Integer.parseInt(a[1]);
				int xIdx = x-1;
				while(true) {
					if (xIdx>=SN) break;
					sarr[xIdx] = sarr[xIdx].equals("1") ? "0" : "1";
					xIdx += x;
				}
//				System.out.println(Arrays.toString(sarr));
			}else {		//여자
				int y = Integer.parseInt(a[1])-1;
				int yIdx = 1;
				while(y-yIdx>=0 && y+yIdx < SN) {
					if (sarr[y-yIdx].equals(sarr[y+yIdx])) {
						yIdx++;
					}else{
						break;
					}					
				}
				if(y-yIdx+1 >= 0 && y+yIdx-1 < SN) {
					for (int j = y-yIdx+1; j < y+yIdx; j++) {
						sarr [j] = sarr[j].equals("1") ? "0" : "1";
					}					
				}
//				System.out.println(Arrays.toString(sarr));
			}
		}
		for (int i = 0; i < sarr.length; i++) {
			if(i>1 && i%20 == 0) System.out.println();
			System.out.printf("%d ", Integer.parseInt(sarr[i]));
		}
	}
}
