import java.io.*;
import java.util.*;

// 백준 1600: 말이 되고픈 원숭이
public class boj1600 {

	static int k;
	static int w, h;
	static int[][] board;
	
	static boolean[][][] visited;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		k = Integer.parseInt(br.readLine()); // 말처럼 움직일 수 있는 횟수
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		w = Integer.parseInt(st.nextToken()); // 가로
		h = Integer.parseInt(st.nextToken()); // 세로
		
		// 격자판
		board = new int[h][w];
		for (int i=0; i<h; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j=0; j<w; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[h][w][k+1];
		
		bfs(0, 0, 0, 0);
		
		// 도착점까지 갈 수 없는 경우
		if (answer == Integer.MAX_VALUE) {
			answer = -1;
		}
		
		System.out.println(answer);
	}
	
	static void bfs(int x, int y, int move, int cnt) {
		// 원숭이 이동 배열
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		// 말 이동 배열
		int[] ddx = {-2, -1 ,-2, -1, 1, 2, 1, 2};
		int[] ddy = {-1, -2, 1, 2, -2, -1, 2, 1};
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y, move, cnt}); // x좌표, y좌표, 동작수, 말처럼 움직인 횟수
		
		while (!queue.isEmpty()) {
			int[] q = queue.poll();
			x = q[0];
			y = q[1];
			move = q[2];
			cnt = q[3];
			
			// 도착 지점
			if (x == h-1 && y == w-1) {
				answer = Math.min(answer, move);
				continue;
			}
			
			// 원숭이 이동
			for (int i=0; i<4; i++) {
				int mx = x + dx[i];
				int my = y + dy[i];
				
				if (mx < 0 || mx >= h || my < 0 || my >= w) {
					continue;
				}
				
				if (board[mx][my] == 1) {
					continue;
				}
				
				if (visited[mx][my][cnt]) {
					continue;
				}
				
				visited[mx][my][cnt] = true;
				queue.add(new int[] {mx, my, move+1, cnt});			
			}
			
			// 말 이동 횟수를 초과한 경우
			if (cnt >= k) {
				continue;
			}
			
			// 말 이동
			for (int i=0; i<8; i++) {
				int mx = x + ddx[i];
				int my = y + ddy[i];
				
				if (mx < 0 || mx >= h || my < 0 || my >= w) {
					continue;
				}
				
				if (board[mx][my] == 1) {
					continue;
				}
				
				if (visited[mx][my][cnt+1]) {
					continue;
				}
				
				visited[mx][my][cnt+1] = true;
				queue.add(new int[] {mx, my, move+1, cnt+1});			
			}			
		}
	}
	
}
