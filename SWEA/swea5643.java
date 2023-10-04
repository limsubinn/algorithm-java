import java.io.*;
import java.util.*;

// swea 5643. 키 순서
public class swea5643 {

	static int n;
	static ArrayList<Integer>[] graph;
	static int[] cnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			n = Integer.parseInt(br.readLine());
			int m = Integer.parseInt(br.readLine());
			
			// 그래프 초기화
			graph = new ArrayList[n];
			for (int i=0; i<n; i++) {
				graph[i] = new ArrayList<>();
			}			
			
			// 그래프 입력
			for (int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				graph[Integer.parseInt(st.nextToken())-1].add(Integer.parseInt(st.nextToken())-1);
			}
			
			// 각 노드마다 비교 횟수 저장
			cnt = new int[n];
			for (int i=0; i<n; i++) {
				bfs(i);
			}
			
			// 정답 출력
			int answer = 0;
			for (int c: cnt) {
				// 모든 노드와 비교했으면 정답 추가
				if (c >= n-1) {
					answer++;
				}
			}
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);

	}
	
	static void bfs(int node) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(node);
		
		boolean[] visited = new boolean[n];
		visited[node] = true;
		
		while (!queue.isEmpty()) {
			int q = queue.poll();
			
			// 그래프 탐색
			for (int g: graph[q]) {
				if (visited[g]) {
					continue;
				}
				
				// 비교 횟수 증가
				cnt[node]++;
				cnt[g]++;
				visited[g] = true;
				queue.add(g);
			}
		}
	}

}
