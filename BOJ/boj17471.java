import java.io.*;
import java.util.*;

// 백준 17471: 게리맨더링
public class boj17471 {
	
	static int n;
	static int totalSum = 0;
	static int answer = Integer.MAX_VALUE;

	static Integer[] result; // 조합
	static int[] popularity; // 인구수
	static ArrayList<Integer>[] graph; // 그래프
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		popularity = new int[n];
		graph = new ArrayList[n];
		for (int i=0; i<n; i++) {
			graph[i] = new ArrayList();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i=0; i<n; i++) {
			popularity[i] = Integer.parseInt(st.nextToken());
			totalSum += popularity[i]; // 총 인구수
		}
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int p = Integer.parseInt(st.nextToken());
			for (int j=0; j<p; j++) {
				graph[i].add(Integer.parseInt(st.nextToken()) - 1);
			}
		}
		
		for (int i=1; i<=n/2; i++) {
			result = new Integer[i];
			combinations(0, 0, i); // 조합
		}
		
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
		
	}
	
	static void combinations(int start, int cnt, int r) {
		if (cnt == r) { // 조합 완료
			
			// 뽑은 수들
			List<Integer> resultList = new ArrayList<>(Arrays.asList(result));
			// 뽑지 않은 수들
			List<Integer> tempList = new ArrayList<>();
			for (Integer i=0; i<n; i++) {
				if (!resultList.contains(i)) {
					tempList.add(i);
				}
			}
			
			// 연결되어 있는 두 그래프인 경우
			if (bfs(resultList) && bfs(tempList)) {
				// 두 그래프의 인구수 차이 계산
				int sum = 0;
				for (int res: result) {
					sum += popularity[res];
				}
				// 정답 갱신
				answer = Math.min(answer, Math.abs((totalSum - sum) - sum));
			}
			
			return;
		}
		
		for (int i=start; i<n; i++) {
			result[cnt] = i;
			combinations(i+1, cnt+1, r);
		}
	}
	
	static boolean bfs(List<Integer> list) {		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(list.get(0));
		
		boolean[] visited = new boolean[n];
		visited[list.get(0)] = true;
		
		while (!queue.isEmpty()) {
			int q = queue.poll();
			
			for (int g: graph[q]) {
				// 방문한 적 있으면 넘어간다.
				if (visited[g]) {
					continue;
				}
				
				// 그래프에 포함되어 있지 않으면 넘어간다.
				if (!list.contains((Integer)g)) {
					continue;
				}
				
				queue.add(g);
				visited[g] = true;
			}
		}
		
		// 그래프가 연결되어 있지 않은 경우
		for (int l: list) {
			if (!visited[l]) {
				return false;
			}
		}
		
		return true;
	}
}
