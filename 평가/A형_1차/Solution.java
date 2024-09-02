import java.io.*;
import java.util.*;

class Solution {
	static int N, T;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map = new int[500][500];
	
	public static void main(String args[]) throws Exception { 
		 
		 //test_case 10��
		for (int tc = 1; tc <= 10; tc++) {
			
			N = Integer.parseInt(br.readLine());
			 
			 
			 //�ʱ� �Է�
			 for (int i = 0; i < N; i++) {
				 st = new StringTokenizer(br.readLine());
				 for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			 
			//ù ���� �ִ� ������ �ϰ�
			 Down();
			 
			 
			 

			 Right();
		
			 
			 int cntB = 0;
			 int cntR = 0;
			 for (int i = 0; i < N; i++) {
				 if(map[N-1][i] > 0) cntB++;
				 if(map[i][N-1] > 0) cntR++;
			}
			 System.out.println("#" + tc + " " + cntB + " " + cntR);

		}
		
	}
	
	
	private static void Down() {
		//ù �ุ Ž��
		for (int i = 0; i < N; i++) {
			if(map[0][i] > 0 && map[1][i] == 0) {	//�ؿ� ���� �־�� �ϰ� ����
				brick(0,i, 1, 1);
			}
		}
	}
	
	private static void Right() {
		for (int i = 0; i < N; i++) {
			if(map[i][0] > 0 && map[i][1] == 0) {
				brick2(i, 0, 1, 1);
			}
		}
		
		
	}
	
	
	
	//���ϰ�
	private static void brick2(int x, int y, double m, int brickN) {
		while(true) {
			if(y+1 >= N) break;
			if(map[x][y+1] > 0) {	//�ϰ��ϴٰ� ������ ������ ��
				int cnt = 0;	//�ؿ� ��� �ִ��� ���ֱ�
				
				int re_y = 0;	//���� �ؿ� �ִ� ������ ��ġ
				for (int i = y+1; i < N; i++) {
					if(map[x][i] == 0 || i >= N) break;
					if (map[x][i] > 0) cnt++;
					re_y = i;
				}
				
				if(m > cnt) {	//�ؿ� �ִ� ���Ժ��� �ϰ� ���԰� �� ũ�ٸ�
					//x ���� �� ������ ���� �ʿ�!!
					brick2(x, re_y, m+cnt, brickN+cnt);	//���Կ� ��ġ �����ؼ� �ٽ� �ϰ� ����
					break; //�ش� �ż���� ����
				}else {	//�ؿ� �ִ� ���԰� �� ũ��
					break;	//�״�� ����
				}
			}else {		//�ؿ� ������ ���� ��
				m = m * 1.9;	//���Դ� ��� 1.9�� ����
				
				//�������� �����̰� ���� ��찡 �ִ�.
				for (int i = y; i > y-brickN; i--) {
					map[x][i+1] = map[x][i];
					map[x][i] = 0;
				}
				
				y++;	//���� �ϴ� ���� ��ġ ����
//				System.out.println(x);
			}

		}
		
	}

	

	private static void brick(int x, int y, double m, int brickN) {
		while(true) {
			if(x+1 >= N) break;
			if(map[x+1][y] > 0) {	//�ϰ��ϴٰ� ������ ������ ��
				int cnt = 0;	//�ؿ� ��� �ִ��� ���ֱ�
				
				int re_x = 0;	//���� �ؿ� �ִ� ������ ��ġ
				for (int i = x+1; i < N; i++) {
					if(map[i][y] == 0 || i >= N) break;
					if (map[i][y] > 0) cnt++;
					re_x = i;
				}
				
				if(m > cnt) {	//�ؿ� �ִ� ���Ժ��� �ϰ� ���԰� �� ũ�ٸ�
					//x ���� �� ������ ���� �ʿ�!!
					brick(re_x, y, m+cnt, brickN+cnt);	//���Կ� ��ġ �����ؼ� �ٽ� �ϰ� ����
					break; //�ش� �ż���� ����
				}else {	//�ؿ� �ִ� ���԰� �� ũ��
					break;	//�״�� ����
				}
			}else {		//�ؿ� ������ ���� ��
				m = m * 1.9;	//���Դ� ��� 1.9�� ����
				
				//�������� �����̰� ���� ��찡 �ִ�.
				for (int i = x; i > x-brickN; i--) {
					map[i+1][y] = map[i][y];
					map[i][y] = 0;
				}
				
				x++;	//���� �ϴ� ���� ��ġ ����
//				System.out.println(x);
			}

			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}