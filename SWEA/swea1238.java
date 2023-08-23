import java.io.*;
import java.util.*;

// swea1238. Contact
public class swea1238 {
	
	static int n, start;
	static ArrayList<Integer>[] graph = new ArrayList[101];
	static ArrayList<Integer> toList = new ArrayList<>();
	static HashMap<Integer, Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int tc=1; tc<=10; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
		
			n = Integer.parseInt(st.nextToken()); // 데이터의 길이
			start = Integer.parseInt(st.nextToken()); // 시작점
			
			graph = new ArrayList[101]; // 그래프
			toList = new ArrayList<>(); // 연락을 받는 사람의 번호 리스트
			map = new HashMap<>(); // 연락을 받는 사람, depth 저장
			
			for (int i=1; i<101; i++) {
				graph[i] = new ArrayList<>(); // 초기화
			}
			
			// 그래프 입력
			st = new StringTokenizer(br.readLine(), " ");
			for (int i=0; i<n/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				if (!graph[from].contains(to)) {
					graph[from].add(to);
				}
				
				// 연락을 받는 사람 저장
				if (!toList.contains(to)) {
					toList.add(to);
				}
			}
			
			int depth = bfs(start, 0); // 최대 깊이
			int answer = 0;
			
			for (int t: toList) {	
				if (!map.containsKey(t)) {
					continue;
				}
				
				if (depth != map.get(t)) {
					continue;
				}
				
				// 최댓값 갱신
				answer = Math.max(answer, t);
			}
			
			sb.append("#" + tc + " " + answer + "\n");
		}
		
		System.out.print(sb);	
	}
	
	static int bfs(int x, int depth) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, depth});

		boolean[] visited = new boolean[101];
		visited[start] = true;
		
		while (!queue.isEmpty()) {
			int[] q = queue.poll();
			x = q[0];
			depth = q[1];
			
			map.put(x, depth); // 번호, 깊이
			
			for (int g: graph[x]) {
				if (visited[g]) {
					continue;
				}
				
				queue.add(new int[] {g, depth+1});
				visited[g] = true;
			}
		}
		
		return depth;
	}
}
