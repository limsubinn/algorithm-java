import java.io.*;
import java.util.*;

// 백준 17780: 새로운 게임
public class boj17780 {
	
	static int n, k;
	static int[][] colors;
	static Horse[] horses;
	static List<Integer>[][] board;

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	static class Horse {
		int r; // 행
		int c; // 열
		int d; // 이동 방향
		
		public Horse(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		public void setPosition(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public void setDirection(int d) {
			this.d = d;
		}		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 체스판의 크기
		n = Integer.parseInt(st.nextToken());
		// 말의 개수
		k = Integer.parseInt(st.nextToken());
		
		// 체스판의 정보
		colors = new int[n][n]; // 체스판 색깔
		board = new ArrayList[n][n]; // 말의 위치
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				colors[i][j] = Integer.parseInt(st.nextToken());
				board[i][j] = new ArrayList<>();
			}
		}

		// 말의 정보
		horses = new Horse[k];
		for (int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			
			board[r][c].add(i);
			horses[i] = new Horse(r, c, d);
		}
		
		// 게임
		int answer = 1;
		while (!turn()) {
			if (answer++ > 1000) {
				answer = -1;
				break;
			}
		}
		
		System.out.println(answer);
	}
	
	static boolean turn() {

		Horse horse;
		int r, c, d;
		int mr, mc;
		int b;
		
		for (int i=0; i<k; i++) {
			horse = horses[i];
			r = horse.r; // 행
			c = horse.c; // 열
			
			// 가장 아래에 있는 말이 아닌 경우
			if (board[r][c].indexOf(i) > 0) {
				continue;
			}
						
			d = horse.d; // 방향
			
			// 이동 좌표
			mr = r + dx[d];
			mc = c + dy[d];
			
			// 체스판을 벗어나거나 이동하려는 칸이 파란색인 경우
			if (mr < 0 || mr >= n || mc < 0 || mc >= n || colors[mr][mc] == 2) {
				// 방향 바꾸기
				d = changeDirection(d);
				horse.setDirection(d);
				
				// 이동 좌표
				mr = r + dx[d];
				mc = c + dy[d];
				
				// 이동하지 않는다.
				if (mr < 0 || mr >= n || mc < 0 || mc >= n || colors[mr][mc] == 2) {
					continue;
				}
			}
			
			// 이동하려는 말의 크기
			int size = board[r][c].size();
			
			// 게임 종료
			if (board[mr][mc].size() + size >= 4) {
				return true;
			}
						
			// 흰색
			if (colors[mr][mc] == 0) {
				while (size-- > 0) {
					// 순서대로 쌓기
					b = board[r][c].remove(0);
					board[mr][mc].add(b);
					// 말의 위치 정보 갱신
					horses[b].setPosition(mr, mc);
				}
				continue;
			}
			
			// 빨간색 (colors[mr][mc] == 1)
			while (size-- > 0) {
				// 반대로 쌓기
				b = board[r][c].remove(size);
				board[mr][mc].add(b);
				// 말의 위치 정보 갱신
				horses[b].setPosition(mr, mc);
			}
		}
		
		return false;
	}
	
	static int changeDirection(int d) {
		if (d % 2 == 0) {
			return d + 1;
		}
		return d - 1;
	}
}
