import java.io.*;
import java.util.*;

// 백준 1987: 알파벳
public class boj1987 {

	static int r, c;
	static int answer = 1;
	
	static char[][] board;
	static boolean[] visited = new boolean[26];
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		board = new char[r][c];
		for (int i=0; i<r; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		visited[alphaToInt(board[0][0])] = true;
		check(0, 0, 1);
		
		System.out.println(answer);
		
	}
	
	static int alphaToInt(char c) {
		return (int)(c) - 65;
	}
	
	static void check(int x, int y, int cnt) {
		for (int i=0; i<4; i++) {
			// 이동
			int mx = x + dx[i];
			int my = y + dy[i];
			
			// 범위 벗어나면 통과
			if (mx < 0 || mx >= r || my < 0 || my >= c) {
				continue;
			}
			
			// 정수로 변환
			int n = alphaToInt(board[mx][my]);
			
			// 방문했으면 통과
			if (visited[n]) {
				continue;
			}
			
			
			visited[n] = true;
			check(mx, my, cnt+1);
			answer = Math.max(answer, cnt+1); // 최댓값 갱신
			visited[n] = false;
		}
	}
}
