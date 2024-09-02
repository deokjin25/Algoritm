import java.util.*;

public class Test1_김덕진 {

	public static void main(String[] args) {
		//---------여기에 코드를 작성하세요.---------------//
		Scanner sc = new Scanner(System.in);
		
		String p = sc.nextLine();
		int num = 25;
		int[][] arr = new int[5][5];
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};

		if(p.equals("*")) {
			for(int i = 4; i>0; i--) {
				for(int j =0; j<4-i; j++) {
					System.out.print("  ");
				}
				
				
				for(int j = 0; j<i; j++) {
					System.out.print("*" + " ");
				}
				System.out.println();
			}
		}else if(p.equals("1")){
			int move = 0;
			int x = 0;
			int y = 0;
			arr[x][y] = num;
			while (num>1) {
				if (x + dx[move%4] < 5 && y + dy[move%4] < 5 && x + dx[move%4] >= 0 && y + dy[move%4] >= 0 && arr[x + dx[move%4]][y + dy[move%4]] == 0) {
					x += dx[move%4];
					y += dy[move%4];
					num--;
					arr[x][y] = num;
				}else {
					move++;
				}

			}
			
			int[][] arr2 = new int[5][5];
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
					arr2[i][j] = arr[i][4-j];
				}
			}
			
			for(int[] a : arr2) {
				System.out.println(Arrays.toString(a));
			}
		}
		
	}

}

