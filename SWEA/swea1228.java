import java.io.*;
import java.util.*;

// swea1228. 암호문 1
public class swea1228 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int t=1; t<=10; t++) {
			// 원본 암호문의 길이
			int n = Integer.parseInt(br.readLine());
			
			String[] pw = new String[10];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			// 원본 암호문 (10개까지만 입력)
			for (int i=0; i<n; i++) {
				pw[i] = st.nextToken();
				
				if (i >= 9) {
					break;
				}
			}
			
			// 명령어의 개수
			int k = Integer.parseInt(br.readLine());
			
			// 명령어
			st = new StringTokenizer(br.readLine(), " ");
			for (int i=0; i<k; i++) {
				st.nextToken();
				
				// 새로운 암호를 삽입할 인덱스
				int idx = Integer.parseInt(st.nextToken());
				
				// 새로운 암호의 개수
				int m = Integer.parseInt(st.nextToken());
				
				// 뒤로 밀 문자열을 저장
				Queue<String> temp = new LinkedList<>();
				for (int j=idx; j<10; j++) {
					temp.add(pw[j]);
				}
				
				// 암호문 만들기
				for (int j=idx; j<idx+m; j++) {
					String s = st.nextToken();
					
					if (j >= 10) {
						continue;
					}
					
					pw[j] = s;
				}
				
				// 밀었던 기존 문자열 붙이기
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
