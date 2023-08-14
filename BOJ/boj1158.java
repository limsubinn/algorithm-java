import java.io.*;
import java.util.*;

// ���� 1158: �似Ǫ�� ����
public class boj1158 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		// 1~n ���� ����
		List<Integer> list = new ArrayList<>();
		for (int i=1; i<=n; i++) {
			list.add(i);
		}
		
		
		int idx = 0;
		sb.append("<");
		while (list.size() > 1) {
			// ������ �ε���
			idx += k-1;
			
			// �ε����� ����Ʈ�� ������ ����� �ε��� ����
			if (idx >= list.size()) {
				idx %= list.size();
			}
			
			// k��° ��� ����
			sb.append(list.remove(idx) + ", ");
		}
		sb.append(list.remove(0) + ">\n");
		System.out.println(sb.toString());
	}

}
