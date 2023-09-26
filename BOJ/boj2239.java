import java.io.*;

// 백준 2239: 스도쿠
public class boj2239 {
	
	static int[][] board = new int[9][9];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 스도쿠 퍼즐 입력
		for (int i=0; i<9; i++) {
			String[] temp = br.readLine().split("");
			for (int j=0; j<9; j++) {
				board[i][j] = Integer.parseInt(temp[j]);
			}
		}
		
		sudoku(0, 0);		
	}
	
	static void sudoku(int x, int y) {
		// y가 범위를 벗어나면 다음 행 찾기
		if (y >= 9) {
			sudoku(x+1, 0);
			return;
		}
		
		// x가 범위를 벗어나면 정답
		if (x >= 9) {
			StringBuilder sb = new StringBuilder();
			
			for (int i=0; i<9; i++) {
				for (int j=0; j<9; j++) {
					sb.append(board[i][j]);
				}
				sb.append("\n");
			}
			
			System.out.print(sb);
			System.exit(0);
		}
		
		// 칸이 이미 숫자로 채워져 있으면 다음 칸 찾기
		if (board[x][y] != 0) {
			sudoku(x, y+1);
			return;
		}
		
		boolean[] visited = new boolean[10];
		
		// 가로, 세로, 3x3 방문 체크
		checkRow(x, visited);
		checkCol(y, visited);
		checkArea(x, y, visited);
		
		for (int i=1; i<=9; i++) {
			if (visited[i]) {
				continue;
			}
			
			// 나타난 적 없는 숫자 넣기
			visited[i] = true;
			board[x][y] = i;
			
			// 다음 칸 찾기
			sudoku(x, y+1);
			
			// 되돌리기
			visited[i] = false;
			board[x][y] = 0;
		}	
	}
	
	static void checkRow(int x, boolean[] visited) {
		for (int i=0; i<9; i++) {
			int b = board[x][i];
			
			if (b != 0) {
				visited[b] = true;
			}
		}
	}
	
	static void checkCol(int y, boolean[] visited) {
		for (int i=0; i<9; i++) {
			int b = board[i][y];
			
			if (b != 0) {
				visited[b] = true;
			}
		}
	}
	
	static void checkArea(int x, int y, boolean[] visited) {
		int X = (x / 3) * 3;
		int Y = (y / 3) * 3;
		
		for (int i=X; i<X+3; i++) {
			for (int j=Y; j<Y+3; j++) {
				int b = board[i][j];
				
				if (b != 0) {
					visited[b] = true;
				}
			}
		}
	}
	
}
