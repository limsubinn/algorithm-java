import java.io.*;
import java.util.*;

// 백준 1940: 주몽
public class boj1940 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		int[] array = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i=0; i<n; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(array); // 정렬
		
		int start = 0, end = n-1;
		int answer = 0;
		
		while (start < end) {
			if (array[start] + array[end] < m) { // m보다 작으면 시작 인덱스를 늘리고
				start++;
			} else if (array[start] + array[end] > m) { // m보다 크면 끝 인덱스를 줄이고
				end--;
			} else { // m이면 정답 추가하고 시작 인덱스를 늘린다.
				start++;
				answer++;
			}
		}
		
		System.out.println(answer);

	}

}
