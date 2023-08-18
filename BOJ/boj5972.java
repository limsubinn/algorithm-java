import java.io.*;
import java.util.*;

// 백준 5972: 택배 배송
public class boj5972 {

	static int n, m;
	
	static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
	static int[] distance;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		// 그래프 초기화
		for (int i=0; i<=n; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 그래프 입력 받기
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
		
			graph.get(a).add(new int[] {b, c});
			graph.get(b).add(new int[] {a, c});
		}
	
		// 거리 초기화 (최댓값으로)
		distance = new int[n+1];
		for (int i=1; i<=n; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		
		dijkstra();	
		System.out.println(distance[n]); // n까지의 최소 비용 출력
		
	}

	static void dijkstra() {
		PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {

			// 비용을 기준으로
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
			
		});
		
		// 시작 노드 삽입
		queue.add(new int[] {1, 0});
		distance[1] = 0;
		
		while (!queue.isEmpty()) {
			int[] q = queue.poll();
			int now = q[0]; // 현재 노드
			int dist = q[1]; // 현재 거리
			
			// 이미 방문했던 적이 있으면 통과
			if (distance[now] < dist) {
				continue;
			}
			
			// 현재 노드와 연결된 다른 노드들 확인
			for (int[] i: graph.get(now)) {
				// 현재 노드를 거쳐서 다른 노드로 이동하는 거리
				int cost = dist + i[1];
				
				// 최솟값을 갱신하고 큐에 삽입
				if (cost < distance[i[0]]) {
					distance[i[0]] = cost;
					queue.add(new int[] {i[0], cost});
				}
			}
		}
	}
}
