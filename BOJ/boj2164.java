import java.io.*;
import java.util.*;

// ���� 2164: ī��2
public class boj2164 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i=1; i<=n; i++) {
			queue.add(i);
		}
		
		while (queue.size() > 1) {
			// �� �� ī�� ������
			queue.poll();
			
			if (queue.size() == 1) {
				break;
			}
			
			// �� �� ī�� �� �Ʒ��� �ű��
			queue.add(queue.poll());
		}
		
		System.out.println(queue.poll());
	}

}
