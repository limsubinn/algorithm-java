import java.io.*;
import java.util.*;

// 백준 2374: 같은 수로 만들기
public class boj2374 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Long> stack = new Stack<>();
		
		long answer = 0;
		long maxValue = 0;
		
		int n = Integer.parseInt(br.readLine());
		for (int i=0; i<n; i++) {
			long a = Long.parseLong(br.readLine());
			maxValue = Math.max(maxValue, a); // 최댓값 갱신
			
			// 스택이 비어있는 경우
			if (stack.isEmpty()) {
				stack.add(a);
				continue;
			}
			
			// 더 작은 값이 들어오는 경우
			if (stack.peek() > a) {
				stack.pop();
				stack.push(a);
				continue;
			}
			
			// 더 큰 값이 들어오는 경우
			answer += a - stack.pop(); // 차이값 더하기
			stack.add(a);
		}
		
		// 남은 값 더하기
		while (!stack.isEmpty()) {
			answer += maxValue - stack.pop();
		}

		System.out.println(answer);
	}

}
