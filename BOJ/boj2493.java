import java.io.*;
import java.util.*;

// 백준 2493: 탑

class Top { // 번호, 높이
    int num; 
    int height;
 
    Top(int num, int height) {
        this.num = num;
        this.height = height;
    }
}

public class boj2493 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		Stack<Top> tops = new Stack<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i=1; i<=n; i++) {
			int s = Integer.parseInt(st.nextToken());
			
			// 스택의 peek 값이 현재 값보다 작을 경우
			while (!tops.isEmpty() && tops.peek().height < s) {
				tops.pop();
			}
			
			if (tops.isEmpty()) { // 스택이 비어있는 경우
				sb.append("0 ");
				tops.push(new Top(i, s));
			} else { // 스택의 peek 값이 현재 값보다 크거나 같을 경우
				sb.append(tops.peek().num + " ");
				tops.push(new Top(i, s));
			}
		}
	
		System.out.println(sb.toString());
	}

}
