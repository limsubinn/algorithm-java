import java.io.*;
import java.util.*;

// 백준 16724: 피리 부는 사나이
public class boj16724 {

	static int n, m;
	static char[][] board;
	static int[][] visited;
	static int answer = 0;
	
	static Map<Character, Integer> map = new HashMap<>();
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new char[n][m];
		visited = new int[n][m];
		
		for (int i=0; i<n; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		// 방향
		map.put('U', 0);
		map.put('D', 1);
		map.put('L', 2);
		map.put('R', 3);
		
		// 연결된 구역 찾기
		int cnt = 1;
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if (board[i][j] != '.') {
					bfs(i, j, cnt++);
				}
			}
		}
		
		System.out.println(answer);

	}
	
	static void bfs(int x, int y, int cnt) {
		int d = map.get(board[x][y]);
		int[] q = new int[] {x, y, d};
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(q);
		visited[x][y] = cnt; // 방문 표시
		
		while(!queue.isEmpty()) {
			q = queue.poll();
			x = q[0];
			y = q[1];
			d = q[2];
			
			int mx = x + dx[d];
			int my = y + dy[d];
			
			// 안전구역 
			if (visited[mx][my] == cnt) {
				answer++;
				return;
			}
			
			// 이전에 안전구역을 만들었던 곳
			if (visited[mx][my] != 0) {
				return;
			}
			
			queue.add(new int[] {mx, my, map.get(board[mx][my])});
			visited[mx][my] = cnt;
		}
	}

}
