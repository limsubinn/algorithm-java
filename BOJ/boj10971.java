import java.io.*;
import java.util.*;

// 백준 10971: 외판원 순회 2
public class boj10971 {

	static int n;
	static int[][] graph;
	
	static int answer = Integer.MAX_VALUE;
	static int[] result;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		graph = new int[n][n];
		result = new int[n];
		visited = new boolean[n];

		for (int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j=0; j<n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		permutation(0);
		System.out.println(answer);

	}
	
	static void permutation(int cnt) {
		// 선택 완료
		if (cnt == n) {
			// 경로 존재하지 않는 경우
			if (graph[result[n-1]][result[0]] == 0) {
				return;
			}
			
			// 비용의 합 구하기
			int sum = 0;
			for (int i=1; i<n; i++) {
				sum += graph[result[i-1]][result[i]];
			}
			sum += graph[result[n-1]][result[0]];
			
			// 정답 갱신
			answer = Math.min(answer, sum);
			return;
		}
		
		// 순열
		for (int i=0; i<n; i++) {
			if (visited[i]) {
				continue;
			}
			
			result[cnt] = i;
			
			// 경로 존재하지 않는 경우 
			if (cnt > 0 && graph[result[cnt-1]][result[cnt]] == 0) {
				continue;
			}
			
			visited[i] = true;
			permutation(cnt+1);
			visited[i] = false;
		}
	}

}
