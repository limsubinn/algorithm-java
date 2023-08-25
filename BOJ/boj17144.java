import java.io.*;
import java.util.*;

// 백준 17144: 미세먼지 안녕!
public class boj17144 {

	static int r, c, t;
	static int clean = -1; // 공기청정기 위쪽의 x 좌표
	static int[][] room;
	static int[][] temp; // 복사 배열
	static Queue<int[]> queue = new LinkedList<>();
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1 ,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		room = new int[r][c];
		int cnt = 0;
		
		for (int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j=0; j<c; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				
				if (room[i][j] == -1 && clean == -1) {
					clean = i;
				}
			}
		}

		while(t-- > 0) {
			for (int i=0; i<r; i++) {
				for (int j=0; j<c; j++) {
					// 미세먼지 있는 곳 큐에 삽입
					if (room[i][j] > 0) {
						queue.add(new int[] {i, j});
					}
				}
			}
			
			bfs();
			move();
		
		}
		
		int answer = 0;
		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) {
				// 먼지 개수 세기
				int r = room[i][j];
				if (r <= 0) {
					continue;
				}
				answer += r;
			}
		}
		System.out.println(answer);
	}
	
	static int[][] cloneArray(int[][] before) {
		int[][] after = new int[r][c];
		
		for (int i=0; i<r; i++) {
			System.arraycopy(before[i], 0, after[i], 0, c);
		}
		
		return after;
	}
	
	static void bfs() {
		temp = cloneArray(room);
		
		while (!queue.isEmpty()) {
			int[] q = queue.poll();
			int x = q[0];
			int y = q[1];
			int dust = room[x][y] / 5; // 확산되는 미세먼지의 양
			
			// 확산되는 먼지가 없으면 넘어간다.
			if (dust < 1) {
				continue;
			}
			
			for (int i=0; i<4; i++) {
				// 이동
				int mx = x + dx[i];
				int my = y + dy[i];
				
				// 범위 벗어나면 넘어간다.
				if (mx < 0 || mx >= r || my < 0 || my >= c) {
					continue;
				}
				
				// 공기청정기를 만나면 넘어간다.
				if (temp[mx][my] == -1) {
					continue;
				}
				
				// 미세먼지 확산
				temp[mx][my] += dust;
				temp[x][y] -= dust;
			}
		}
	}
	
	static void move() {
		// 공기청정기 위쪽
		int x = clean;
		room = cloneArray(temp);
		
		for (int j=2; j<c; j++) { // 오른쪽
			room[x][j] = temp[x][j-1];
		}
		
		for (int i=x; i>0; i--) { // 위
			room[i-1][c-1] = temp[i][c-1];
		}
		
		for (int j=c-1; j>0; j--) { // 왼쪽
			room[0][j-1] = temp[0][j];
		}
		
		for (int i=1; i<x; i++) { // 아래
			room[i][0] = temp[i-1][0];
		}
		
		room[x][1] = 0;
		
		// 공기청정기 아래쪽
		x = clean + 1;
		
		for (int j=2; j<c; j++) { // 오른쪽
			room[x][j] = temp[x][j-1];
		}
		
		for (int i=x+1; i<r; i++) { // 아래
			room[i][c-1] = temp[i-1][c-1];
		}
		
		for (int j=c-1; j>0; j--) { // 왼쪽
			room[r-1][j-1] = temp[r-1][j];
		}
		
		for (int i=r-1; i>x+1; i--) { // 위
			room[i-1][0] = temp[i][0];
		}
		
		room[x][1] = 0;
	}

}
