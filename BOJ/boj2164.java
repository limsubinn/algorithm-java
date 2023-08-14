import java.io.*;
import java.util.*;

// 백준 2164: 카드2
public class boj2164 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i=1; i<=n; i++) {
			queue.add(i);
		}
		
		while (queue.size() > 1) {
			// 맨 위 카드 버리기
			queue.poll();
			
			if (queue.size() == 1) {
				break;
			}
			
			// 맨 위 카드 맨 아래로 옮기기
			queue.add(queue.poll());
		}
		
		System.out.println(queue.poll());
	}

}
