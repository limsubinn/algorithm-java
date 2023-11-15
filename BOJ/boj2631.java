import java.io.*;

// 백준 2631: 줄세우기
public class boj2631 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] numbers = new int[n];
		
		for (int i=0; i<n; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		
		// 최장 증가 수열
		int[] result = new int[n];
		int max = 0;
		for (int i=0; i<n; i++) {
			result[i] = 1;
			for (int j=0; j<i; j++) {
				if (numbers[j] < numbers[i]) {
					result[i] = Math.max(result[i], result[j] + 1);
					max = Math.max(max, result[i]); // 최댓값
				}
			}
		}
		
		// 최장 증가 수열에 포함된 수를 뺀 나머지를 옮겨야 함.
		System.out.println(n-max);
	}
}
