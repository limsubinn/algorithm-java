import java.io.*;
import java.util.*;

// swea1225. ��ȣ ������
public class swea1225 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int t=1; t<=10; t++) {
			br.readLine();
			
			// 8���� ���� - ť�� ����
			Queue<Integer> queue = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i=0; i<8; i++) {
				queue.add(Integer.parseInt(st.nextToken()));
			}
			
			// ������ ��
			int i = 1;
			
			while (true) {
				int q = queue.poll() - i++;
				
				// 0���� �۰ų� �������� ��� 0�� �����ϰ� ����
				if (q <= 0) {
					queue.add(0);
					break;
				}
				
				queue.add(q);
				
				// �� ����Ŭ�� �� ���
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
