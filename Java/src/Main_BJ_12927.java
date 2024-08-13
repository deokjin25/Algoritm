import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BJ_12927 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String arr = br.readLine(); //스위치 상태
		char[] s = new char[arr.length()];	//스위치 상태 배열
		for (int i = 0; i < arr.length(); i++) {
			s[i] = arr.charAt(i);
		}
		
		int cnt =0;	//스위치를 누른 횟수
		for (int i = 0; i < s.length; i++) {
			if(s[i] == 'Y') {
				int idx = i;	//변경 위치 설정 인덱스
				cnt++;
				while(true) {	//배열 변경
					s[idx] = s[idx] == 'Y' ? 'N' : 'Y';
					idx += i+1;
					if (idx >= s.length) {
						break;
					}
				}
			}
		}
		
		char[] allN = new char[s.length];	//
		for (int i = 0; i < s.length; i++) {
			allN[i] = 'N';
		}
		
		if(Arrays.equals(allN, s)) {
			System.out.println(cnt);
		}else {
			if(cnt > 0) {
				System.out.println(-1);
			}else {
				System.out.println(0);
			}
		}

	}

}
