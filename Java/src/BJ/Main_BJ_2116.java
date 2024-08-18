package BJ;
import java.io.*;

public class Main_BJ_2116 {
    public static int searchMax(int[][] dice, int down, int h, int diceN) {
    	if(h == diceN+1) return 0;
    	
    	int Up = 0;
        int[] searchDice = dice[h-1];
        
        // 마주 보는 면 / 인덱스
        // a f / 0 5,  b d / 1 3,  c e / 2 4
        for (int i = 0; i < 6; i++) {
			if(i==0 && searchDice[i] == down) Up = searchDice[5];
			if(i==1 && searchDice[i] == down) Up = searchDice[3];
			if(i==2 && searchDice[i] == down) Up = searchDice[4];
			if(i==3 && searchDice[i] == down) Up = searchDice[1];
			if(i==4 && searchDice[i] == down) Up = searchDice[2];
			if(i==5 && searchDice[i] == down) Up = searchDice[0];
		}    	
    	        
        int sideMax = 0;
        for (int i = 0; i < 6; i++) {
        	if (searchDice[i] == down || searchDice[i] == Up) continue;
        	sideMax = Math.max(sideMax, searchDice[i]);
        }
        
//        System.out.printf("%d %d %d %d%n", down, Up, sideMax, h);
        
    	return sideMax + searchMax(dice, Up, ++h, diceN);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int diceN = Integer.parseInt(br.readLine());
        int[][] dice = new int[diceN+1][6];    //주사위 2차원 배열
        
        
        for (int i = 0; i < diceN; i++) {
            String[] arr = br.readLine().split(" ");
            for (int j = 0; j < 6; j++) {
                dice[i][j] = Integer.parseInt(arr[j]);
            }
        }
        
        int result = 0;
        for (int i = 1; i < 7; i++) {
        	result = Math.max(result, searchMax(dice, i, 1, diceN));
		}
        
        System.out.println(result);
        
    }

}