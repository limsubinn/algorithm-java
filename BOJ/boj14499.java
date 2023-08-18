import java.io.*;
import java.util.*;

// 백준 14499: 주사위 굴리기
public class boj14499 {
	
	static int n, m;
	static int x, y;
	static int k;
	
	static int[][] map;
	static int[] dice = new int[6]; // 뒤, 앞, 왼, 오, 상, 하
	
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, 1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine(), " ");
		
		for (int i=0; i<k; i++) {
			int result = run(Integer.parseInt(st.nextToken()));

			// 명령 무시
			if (result == -1) {
				continue;
			}
			
			sb.append(result + "\n");
		}	
		System.out.print(sb);
	}
	
	static void rotateDice(int i) {
		int temp = dice[5];
		
		// 동쪽
		if (i == 1) {
			dice[5] = dice[3];
			dice[3] = dice[4];
			dice[4] = dice[2];
			dice[2] = temp;
			return;
		}
		
		// 서쪽
		if (i == 2) {
			dice[5] = dice[2];
			dice[2] = dice[4];
			dice[4] = dice[3];
			dice[3] = temp;
			return;
		}
		
		// 북쪽
		if (i == 3) {
			dice[5] = dice[0];
			dice[0] = dice[4];
			dice[4] = dice[1];
			dice[1] = temp;
			return;
		}
		
		// 남쪽
		dice[5] = dice[1];
		dice[1] = dice[4];
		dice[4] = dice[0];
		dice[0] = temp;
	}
	
	
	static int run(int i) {
		// 좌표 이동
		x += dx[i];
		y += dy[i];
		
		// 인덱스가 범위를 벗어나는 경우
		if (x < 0 || x >= n || y < 0 || y >= m) {
			x -= dx[i];
			y -= dy[i];
			return -1;
		}
		
		// 주사위 굴리기
		rotateDice(i);
			
		if (map[x][y] == 0) { // 이동한 칸에 쓰여 있는 수가 0인 경우, 주사위의 바닥면에 쓰여 있는 수를 칸에 복사
			map[x][y] = dice[5];
		} else { // 0이 아닌 경우, 칸에 쓰여 있는 수를 주사위의 바닥면으로 복사하고 칸에 쓰여 있는 수는 0
			dice[5] = map[x][y];
			map[x][y] = 0;
		}
			
		// 주사위 상단에 쓰여 있는 값 리턴
		return dice[4];
	}
}
