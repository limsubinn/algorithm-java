import java.io.*;
import java.util.*;

// 백준 16435: 스네이크버드
public class boj16435 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 과일의 개수
		int n = Integer.parseInt(st.nextToken());
		// 스네이크버드의 초기 길이
		int l = Integer.parseInt(st.nextToken());
		
		int[] h = new int[n];
		
		st = new StringTokenizer(br.readLine(), " ");
		// 과일의 높이
		for (int i=0; i<n; i++) {
			h[i] = Integer.parseInt(st.nextToken());
		}
		
		// 높이 정렬
		Arrays.sort(h);
		
		for (int i: h) {
			if (i <= l) { // 과일을 먹을 수 있는 경우 스네이크버드의 길이를 늘린다.
				l++;
			} else { // 과일을 먹을 수 없는 경우 반복문 종료
				break;
			}
		}
		
		System.out.println(l);
	}

}
