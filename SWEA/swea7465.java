import java.io.*;
import java.util.*;

// swea 7465. 창용 마을 무리의 개수
public class swea7465 {

	static int n, m;
	static List<Integer>[] graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			visited = new boolean[n+1];
			graph = new ArrayList[n+1];
			
			for (int i=1; i<=n; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for (int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph[a].add(b);
				graph[b].add(a);
			}
			
			int answer = 0;
			for (int i=1; i<=n; i++) {
				if (!visited[i]) {
					bfs(i);
					answer++;
				}
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);

	}
	
	static void bfs(int x) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(x);
		visited[x] = true;
		
		while (!queue.isEmpty()) {
			int q = queue.poll();
			
			for (int g: graph[q]) {
				if (!visited[g]) {
					queue.add(g);
					visited[g] = true;
				}
			}
		}
	}
}
