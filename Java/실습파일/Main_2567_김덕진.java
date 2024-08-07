import java.util.*;
import java.io.*;

public class Main_2567_����� {

	public static void main(String[] args) throws IOException{
		int[][] arr = new int[101][101];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<100; i++) {
			Arrays.fill(arr[i], 0); //��ȭ�� 0���� �ʱ�ȭ
		}
		
		int N = Integer.parseInt(br.readLine()); //�Է¹޴� ������ ��
		
		for(int n=0; n<N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());  //������ x��ǥ
			int y = Integer.parseInt(st.nextToken());  //������ y��ǥ
			for(int i=x; i<x+10; i++) {
				for(int j=y; j<y+10; j++) {
					if (arr[i][j] == 0) {  //�����̰� �� ���� ������
						arr[i][j] = 1;  //1�� ĥ�ϱ�
					}
				}
			}		
			
		}
	
		//0�� 1�� ������ ������ �ѷ�
		int cnt = 0; //�ѷ� ī��Ʈ
		for(int i=1; i<100; i++) {
			for(int j=1; j<100; j++) {
				if(arr[i][j] == 0 && arr[i-1][j] == 1) cnt++;  //�Ʒ��� 0�̰� ���� 1�� ��
				if(arr[i][j] == 0 && arr[i+1][j] == 1) cnt++;  //���� 0�̰� �Ʒ��� 1�� ��
				if(arr[i][j] == 0 && arr[i][j-1] == 1) cnt++;  //�������� 0�̰� ������ 1�� ��
				if(arr[i][j] == 0 && arr[i][j+1] == 1) cnt++;	// ������ 0�̰� �������� 1�� ��
			}
		}
		
		System.out.println(cnt);
		
	}
}
