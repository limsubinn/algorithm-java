import java.io.*;

// 백준 2389: 설탕 배달
public class boj2389 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int answer = 0;
		
		while (true) {
			// 5 추가
			if (n % 5 == 0) {
				// 정답 추가하고 종료 (설탕 배달 가능)
				answer += n / 5;
				break;
			}
			
			// 3 추가
			n -= 3;
			answer++;
			
			// 설탕 배달 불가능한 경우
			if (n < 0) {
				answer = -1;
				break;
			}
			
			// 설탕 배달 가능한 경우
			if (n == 0) {
				break;
			}
		}
		
		System.out.println(answer);

	}

}
