import java.util.*;

// 백준 2023: 신기한 소수
public class boj2023 {

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		dfs(0, n);
		
		System.out.println(sb.toString());

	}

	static boolean isPrimeNumber(int num) {
		if (num < 2) return false;
		
		for (int i=2; i*i<=num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	static void dfs(int num, int n) { // 소수, 자릿수
		// 자릿수를 완성했을 때 (전부 탐색 완)
		if (n == 0) {
			sb.append(num + "\n");
		}

		// 숫자 만들기
		for (int i=1; i<=9; i++) {
			int temp = 10 * num + i;
			// 소수이면 다음 자리 탐색 ?.. ?? ㅠ
			if (n > 0 && isPrimeNumber(temp)) {
				dfs(temp, n-1);
			}
		}
	}
}
