package BJ;
import java.io.*;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split(" ");
		
		String[] asc = {"1","2","3","4","5","6","7","8"};
		String[] dsc = {"8","7","6","5","4","3","2","1"};

		if(Arrays.equals(arr, asc)) {
			System.out.println("ascending");
		}else if (Arrays.equals(arr, dsc)) {
			System.out.println("descending");
		}else {
			System.out.println("mixed");
		}
	}

}
