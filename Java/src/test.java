import java.util.Arrays;

public class test {
	public static void main(String[] args) {
		int N = 5;
		
		printStar(N);
		System.out.println(factorial(N));
		
		int[] arr = {1,2,3};
		SOP(arr);
		
			
		}
	
	public static void printStar(int N) {
		if (N == 1) {
			System.out.println("*");
			return;
		}
		
		printStar(N-1);
		for(int i =0; i<N; i++) {
			System.out.print("*");
		}
		System.out.println();
		
	}
	
	static int factorial(int N) {
		if (N==1) {
			return 1;
		}
		
		return N*factorial(N-1);
	}
	
	static void SOP(int[] arr) {
		if(arr.length == 1) {
			System.out.println(arr[0]);
			return;
		}
		
		SOP(Arrays.copyOf(arr, arr.length-1));
		System.out.println(arr[arr.length-1]);
		return;
		
	}
	
	static void hanoi(int[] N) {
		
		
	}
	
}
