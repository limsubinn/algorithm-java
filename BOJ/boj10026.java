import java.io.*;
import java.util.*;

// 백준 10026: 적록 색약
public class boj10026 {

	static int n;
	static char[][] area;
	static boolean[][] visited;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	static int mx, my;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		area = new char[n][n];
		visited = new boolean[n][n];
		
		for (int i=0; i<n; i++) {
			area[i] = br.readLine().toCharArray();
		}
		
		// 적록 색약이 아닌 사람
		int normal = findArea();
		
		// 적록 색약인 사람
		makeWeakness();
		int weak = findArea();
		
		System.out.println(normal + " " + weak);

	}
	
	static int findArea() {
		visited = new boolean[n][n];
		int cnt = 0;
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (!visited[i][j]) {
					// 연결된 부분 찾기
					bfs(i, j);
					cnt++;
				}
			}
		}
		
		return cnt;
	}
	
	static void makeWeakness() {
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				// R, G 똑같이 만들어주기
				if (area[i][j] == 'G') {
					area[i][j] = 'R';
				}
			}
		}
	}
	
	static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y});
		
		while (!queue.isEmpty()) {
			int[] q = queue.poll();
			x = q[0];
			y = q[1];
			
			for (int i=0; i<4; i++) {
				mx = x + dx[i];
				my = y + dy[i];
				
				// 범위를 벗어나는 경우
				if (mx < 0 || mx >= n || my < 0 || my >= n) {
					continue;
				}
				
				// 이미 방문한 경우
				if (visited[mx][my]) {
					continue;
				}
				
				// 다른 색인 경우
				if (area[mx][my] != area[x][y]) {
					continue;
				}
				
				visited[mx][my] = true;
				queue.add(new int[] {mx, my});
			}
		}
	}

}
