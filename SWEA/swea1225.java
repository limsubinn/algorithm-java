import java.io.*;
import java.util.*;

// swea1225. 암호 생성기
public class swea1225 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int t=1; t<=10; t++) {
			br.readLine();
			
			// 8개의 숫자 - 큐에 삽입
			Queue<Integer> queue = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i=0; i<8; i++) {
				queue.add(Integer.parseInt(st.nextToken()));
			}
			
			// 감소할 수
			int i = 1;
			
			while (true) {
				int q = queue.poll() - i++;
				
				// 0보다 작거나 같아지는 경우 0을 삽입하고 종료
				if (q <= 0) {
					queue.add(0);
					break;
				}
				
				queue.add(q);
				
				// 한 사이클을 돈 경우
				if (i >= 6) {
					i = 1;
				}
				
			}
			
			sb.append("#" + t + " ");
			for (Integer qu: queue) {
				sb.append(qu + " ");
			}
			sb.append("\n");		
		}
		
		System.out.println(sb.toString());
		
	}

}
