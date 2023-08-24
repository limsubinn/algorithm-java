import java.io.*;
import java.util.*;

// 백준 1753: 최단 경로
public class boj1753 {

	static int v, e;
	static int k;
	static ArrayList<int[]>[] graph;
	static int[] distance;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[v+1];
		for (int i=1; i<=v; i++) {
			graph[i] = new ArrayList(); // 초기화
		}
		
		k = Integer.parseInt(br.readLine());

		for (int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a].add(new int[] {b, c}); // 그래프
		}
		
		// 거리 테이블
		distance = new int[v+1];
		for (int i=1; i<=v; i++) {
			distance[i] = Integer.MAX_VALUE; // 초기화
		}
		
		dijkstra();
		
		for (int i=1; i<=v; i++) {
			if (distance[i] == Integer.MAX_VALUE) { // 경로가 존재하지 않는 경우
				sb.append("INF");
			} else { // 경로가 존재하는 경우
				sb.append(distance[i]);
			}
			sb.append("\n");
		}
		
		System.out.print(sb);

	}
	
	static void dijkstra() {
		PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) { // 비용 기준으로 정렬
				return o1[1] - o2[1];
			}
			
		});
		
		distance[k] = 0;
		queue.add(new int[] {k, 0});
		
		while (!queue.isEmpty()) {
			int[] q = queue.poll();
			int now = q[0];
			int dist = q[1];
			
			// 방문한 경우
			if (distance[now] < dist) {
				continue;
			}
			
			// 연결된 그래프 탐색
			for (int[] g: graph[now]) {
				int cost = dist + g[1];
				
				// 현재 노드를 거치는 더 짧은 비용이 존재할 경우
				if (cost < distance[g[0]]) {
					distance[g[0]] = cost;
					queue.add(new int[]{g[0], cost});
				}
			}
			
		}
		
		
		
	}

}
