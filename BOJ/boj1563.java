import java.io.*;

// 백준 1563: 개근상
public class boj1563 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][][] result = new int[n][2][3]; // [일수][지각 횟수(누적)][결석 횟수(연속)]
		result[0][0][0] = 1;
		result[0][0][1] = 1;
		result[0][1][0] = 1;
		
		for (int i=1; i<n; i++) {
			result[i][0][0] = (result[i-1][0][0] + result[i-1][0][1] + result[i-1][0][2]) % 1000000;
			result[i][0][1] = (result[i-1][0][0]) % 1000000;
			result[i][0][2] = (result[i-1][0][1]) % 1000000;
			result[i][1][0] = (result[i-1][0][0] + result[i-1][0][1] + result[i-1][0][2] + result[i-1][1][0] + result[i-1][1][1] + result[i-1][1][2]) % 1000000;
			result[i][1][1] = (result[i-1][1][0]) % 1000000;
			result[i][1][2] = (result[i-1][1][1]) % 1000000;
		}
		
		int answer = 0;
		for (int i=0; i<2; i++) {
			for (int j=0; j<3; j++) {
				answer += result[n-1][i][j];
				answer %= 1000000;
			}
		}
		
		System.out.println(answer);
	}
}
