import java.io.*;
import java.util.*;

// swea1228. ��ȣ�� 1
public class swea1228 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int t=1; t<=10; t++) {
			// ���� ��ȣ���� ����
			int n = Integer.parseInt(br.readLine());
			
			String[] pw = new String[10];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			// ���� ��ȣ�� (10�������� �Է�)
			for (int i=0; i<n; i++) {
				pw[i] = st.nextToken();
				
				if (i >= 9) {
					break;
				}
			}
			
			// ��ɾ��� ����
			int k = Integer.parseInt(br.readLine());
			
			// ��ɾ�
			st = new StringTokenizer(br.readLine(), " ");
			for (int i=0; i<k; i++) {
				st.nextToken();
				
				// ���ο� ��ȣ�� ������ �ε���
				int idx = Integer.parseInt(st.nextToken());
				
				// ���ο� ��ȣ�� ����
				int m = Integer.parseInt(st.nextToken());
				
				// �ڷ� �� ���ڿ��� ����
				Queue<String> temp = new LinkedList<>();
				for (int j=idx; j<10; j++) {
					temp.add(pw[j]);
				}
				
				// ��ȣ�� �����
				for (int j=idx; j<idx+m; j++) {
					String s = st.nextToken();
					
					if (j >= 10) {
						continue;
					}
					
					pw[j] = s;
				}
				
				// �о��� ���� ���ڿ� ���̱�
				for (int j=idx+m; j<10; j++) {
					if (temp.isEmpty()) {
						break;
					}
					pw[j] = temp.poll();
				}
				
			}
			
			sb.append("#" + t);
			for (String p: pw) {
				sb.append(" " + p);
			}
			sb.append("\n");
	
		}
		
		System.out.println(sb.toString());

	}

}
