import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution_SWEA_5644 {
    public static int T, M, A, answer;
    public static String[][] map = new String[10][10];
    public static int[][] persons = new int[2][101];
    public static int[] p1 = new int[2];
    public static int[] p2 = new int[2];
    public static int[][] bcs = new int[9][4];
    public static int[] dr = { 0, 0, 1, 0, -1 };
    public static int[] dc = { 0, -1, 0, 1, 0 };
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            answer = 0;
            p1[0] = 0;
            p1[1] = 0;
            p2[0] = 9;
            p2[1] = 9;
 
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j] = "0";
                }
            }
 
            for (int i = 0; i < persons.length; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= M; j++) {
                    persons[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            for (int i = 1; i <= A; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    bcs[i][j] = Integer.parseInt(st.nextToken());
                }
 
                int r = bcs[i][0] - 1;
                int c = bcs[i][1] - 1;
                int dis = bcs[i][2];
                int charge = bcs[i][3];
 
                for (int j = -dis; j <= dis; j++) {
                    for (int k = -dis; k <= dis; k++) {
                        if (Math.abs(j) + Math.abs(k) > dis) {
                            continue;
                        }
 
                        int nextR = r + j;
                        int nextC = c + k;
                        if (nextR < 0 || nextR >= 10 || nextC < 0 || nextC >= 10) {
                            continue;
                        }
 
                        if (map[nextR][nextC].equals("0")) {
                            map[nextR][nextC] = Integer.toString(i);
                        } else {
                            map[nextR][nextC] += i;
                        }
                    }
                }
            }
 
            for (int i = 0; i <= M; i++) {
                p1[0] += dr[persons[0][i]];
                p1[1] += dc[persons[0][i]];
                p2[0] += dr[persons[1][i]];
                p2[1] += dc[persons[1][i]];
 
                String cand1 = map[p1[0]][p1[1]];
                String cand2 = map[p2[0]][p2[1]];
 
                int maxCharge = 0;
                for (int j = 0; j < cand1.length(); j++) {
                    for (int k = 0; k < cand2.length(); k++) {
                        String charger1 = cand1.substring(j, j+1);
                        String charger2 = cand2.substring(k, k+1);
                         
                        int charge = 0;
                        if(!charger1.equals(charger2)) {
                            charge += bcs[Integer.parseInt(charger1)][3];
                            charge += bcs[Integer.parseInt(charger2)][3];
                        } else {
                            charge = bcs[Integer.parseInt(charger1)][3];
                        }
                         
                        if(charge > maxCharge) maxCharge = charge;
                    }
                }
                answer += maxCharge;
            }
             
            System.out.println("#"+tc+" "+answer);
 
        }
    }
}