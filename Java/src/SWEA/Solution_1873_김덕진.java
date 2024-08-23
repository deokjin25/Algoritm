package SWEA;

import java.util.*;

public class Solution_1873_김덕진 {
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		sc.nextLine();

		for (int test_case = 1; test_case <= T; test_case++) {


			int h = sc.nextInt(); // 행
			int w = sc.nextInt(); // 열
			sc.nextLine();
			String[][] ground = new String[h][w];

			for (int i = 0; i < h; i++) {
				ground[i] = sc.nextLine().split("");
			}

			int user_len = sc.nextInt();
			sc.nextLine();
			String user_input = sc.nextLine();

			int tank_x = 0; // 탱크 위치 x
			int tank_y = 0; // 탱크 위치 y

			//초기 탱크 위치
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (ground[i][j].equals("^") || ground[i][j].equals("v") || ground[i][j].equals("<")
							|| ground[i][j].equals(">")) {
						tank_x = i;
						tank_y = j;
					}
				}
			}

			for (int i = 0; i < user_input.length(); i++) {
				//위로 갈 때
				if (user_input.charAt(i) == 'U') {
					ground[tank_x][tank_y] = "^"; // 방향 먼저 바꾸고
					if (tank_x - 1 >= 0 && ground[tank_x - 1][tank_y].equals(".")) { // 갈 수 있는 땅인지 확인
						ground[tank_x][tank_y] = "."; // 원래 있던 자리 평지로 전환
						tank_x--;
						ground[tank_x][tank_y] = "^"; // 진행
					}
				}

				//아래로 갈 때
				if (user_input.charAt(i) == 'D') {
					ground[tank_x][tank_y] = "v";
					if (tank_x + 1 < h && ground[tank_x + 1][tank_y].equals(".")) {
						ground[tank_x][tank_y] = ".";
						tank_x++;
						ground[tank_x][tank_y] = "v";
					}
				}

				//왼쪽으로 갈 때
				if (user_input.charAt(i) == 'L') {
					ground[tank_x][tank_y] = "<";
					if (tank_y - 1 >= 0 && ground[tank_x][tank_y - 1].equals(".")) {
						ground[tank_x][tank_y] = ".";
						tank_y--;
						ground[tank_x][tank_y] = "<";
					}
				}

				//오른쪽으로 갈 때
				if (user_input.charAt(i) == 'R') {
					ground[tank_x][tank_y] = ">";
					if (tank_y + 1 < w && ground[tank_x][tank_y + 1].equals(".")) {
						ground[tank_x][tank_y] = ".";
						tank_y++;
						ground[tank_x][tank_y] = ">";
					}
				}

				//포탄을 쏠 때
				if (user_input.charAt(i) == 'S') {
					if (ground[tank_x][tank_y].equals("^")) { // 위를 바라 보고 있다면
						for (int j = tank_x - 1; j >= 0; j--) {
							if (ground[j][tank_y].equals("#")) { // 강철을 만나면
								break; // 바로 종료
							}

							if (ground[j][tank_y].equals("*")) { // 벽돌을 만나면
								ground[j][tank_y] = "."; // 평지로 바꾸고
								break; // 종료
							}
						}
					}

					if (ground[tank_x][tank_y].equals("v")) { // 아래를 보고 있다면
						for (int j = tank_x + 1; j < h; j++) {
							if (ground[j][tank_y].equals("#")) { // 강철을 만나면
								break; // 바로 종료
							}

							if (ground[j][tank_y].equals("*")) { // 벽돌을 만나면
								ground[j][tank_y] = "."; // 평지로 바꾸고
								break; // 종료
							}
						}
					}

					if (ground[tank_x][tank_y].equals("<")) { // 왼쪽을 바라 보고 있다면
						for (int j = tank_y - 1; j >= 0; j--) {
							if (ground[tank_x][j].equals("#")) { // 강철을 만나면
								break; // 바로 종료
							}

							if (ground[tank_x][j].equals("*")) { // 벽돌을 만나면
								ground[tank_x][j] = "."; // 평지로 바꾸고
								break; // 종료
							}
						}
					}

					if (ground[tank_x][tank_y].equals(">")) { // 오른쪽을 바라 보고 있다면
						for (int j = tank_y + 1; j < w; j++) {
							if (ground[tank_x][j].equals("#")) { // 강철을 만나면
								break; // 바로 종료
							}

							if (ground[tank_x][j].equals("*")) { // 벽돌을 만나면
								ground[tank_x][j] = "."; // 평지로 바꾸고
								break; // 종료
							}
						}
					}

				}

			}
			System.out.printf("#%d ", test_case);
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					System.out.print(ground[i][j]);
				}
				System.out.println();
			}
		}
	}
}
