import java.io.*;
import java.util.*;

// swea1873. 상호의 배틀필드
public class swea1873 {
	
	static int h, w;
	static char[][] board;
	
	static int n;
	static char[] input;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static HashMap<Character, Integer> positionMap = new HashMap<>();
	static HashMap<Character, Character> charMap = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		positionMap.put('U', 0);
		positionMap.put('D', 1);
		positionMap.put('L', 2);
		positionMap.put('R', 3);
		
		charMap.put('U', '^');
		charMap.put('D', 'v');
		charMap.put('L', '<');
		charMap.put('R', '>');
				
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			boolean flag = false;
			int x = 0, y = 0;
			
			board = new char[h][w];
			
			for (int i=0; i<h; i++) {
				board[i] = br.readLine().toCharArray();
				
				if (flag) {
					continue;
				}
				
				for (int j=0; j<w; j++) {
					// 사용자의 현재 위치 찾기
					if (board[i][j] == '^' || board[i][j] == 'v' || board[i][j] == '<' || board[i][j] == '>') {
						flag = true;
						x = i;
						y = j;
						break;
					}
				}
			}
			
			n = Integer.parseInt(br.readLine());
			input = br.readLine().toCharArray();
			
			int[] position = {x, y};
			for (char i: input) {
				// 사용자의 입력을 하나씩 처리
				position = game(position, i);
			}
			
			sb.append("#" + t + " ");
			for (int i=0; i<h; i++) {
				for (int j=0; j<w; j++) {
					sb.append(board[i][j]);
				}
				sb.append("\n");
			}
		}
		
		System.out.print(sb);
	}
	
	static int[] game(int[] position, char move) {
		// 현재 위치
		int x = position[0];
		int y = position[1];
		// 이동할 위치
		int mx, my;
		
		// 포탄 발사
		if (move == 'S') {
			int i;
			
			// 이동할 좌표
			if (board[x][y] == '^') {
				i = 0;
			} else if (board[x][y] == 'v') {
				i = 1;
			} else if (board[x][y] == '<') {
				i = 2;
			} else {
				i = 3;
			}
			
			mx = x + dx[i];
			my = y + dy[i];
			
			// 범위를 벗어나면 종료
			if (mx < 0 || mx >= h || my < 0 || my >= w) {
				return position;
			}
			
			// 벽돌인 경우 파괴하고 종료
			if (board[mx][my] == '*') {
				board[mx][my] = '.';
				return position;
			}
			
	
			// 강철이 아닌 경우
			while (board[mx][my] != '#') {
				// 계속 이동
				mx += dx[i];
				my += dy[i];
				
				// 범위를 벗어나면 종료
				if (mx < 0 || mx >= h || my < 0 || my >= w) {
					return position;
				}
				
				// 벽돌인 경우 파괴하고 종료 
				if (board[mx][my] == '*') {
					board[mx][my] = '.';
					return position;
				}
			}
			
			// 현재 좌표 리턴
			return position;
		}
		
		// 이동할 좌표
		mx = x + dx[positionMap.get(move)];
		my = y + dy[positionMap.get(move)];
		
		// 범위를 벗어날 경우 현재 좌표의 문자를 바꾸고 종료
		if (mx < 0 || mx >= h || my < 0 || my >= w) {
			board[x][y] = charMap.get(move);
			return position;
		}
		
		// 평지인 경우 이동하고 종료
		if (board[mx][my] == '.') {
			board[x][y] = '.';
			board[mx][my] = charMap.get(move);
			return new int[] {mx, my};
		}
	
		// 이동할 수 없는 경우 현재 좌표의 문자를 바꾸고 종료
		board[x][y] = charMap.get(move);
		return position;
	}

}
