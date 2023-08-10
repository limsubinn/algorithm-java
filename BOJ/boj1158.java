import java.io.*;
import java.util.*;

// 백준 1158: 요세푸스 문제
public class boj1158 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		// 1~n 숫자 삽입
		List<Integer> list = new ArrayList<>();
		for (int i=1; i<=n; i++) {
			list.add(i);
		}
		
		
		int idx = 0;
		sb.append("<");
		while (list.size() > 1) {
			// 제거할 인덱스
			idx += k-1;
			
			// 인덱스가 리스트의 범위를 벗어나면 인덱스 조정
			if (idx >= list.size()) {
				idx %= list.size();
			}
			
			// k번째 사람 제거
			sb.append(list.remove(idx) + ", ");
		}
		sb.append(list.remove(0) + ">\n");
		System.out.println(sb.toString());
	}

}
