import java.io.*;
import java.util.*;

// ���� 2493: ž

class Top { // ��ȣ, ����
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
			
			// ������ peek ���� ���� ������ ���� ���
			while (!tops.isEmpty() && tops.peek().height < s) {
				tops.pop();
			}
			
			if (tops.isEmpty()) { // ������ ����ִ� ���
				sb.append("0 ");
				tops.push(new Top(i, s));
			} else { // ������ peek ���� ���� ������ ũ�ų� ���� ���
				sb.append(tops.peek().num + " ");
				tops.push(new Top(i, s));
			}
		}
	
		System.out.println(sb.toString());
	}

}
