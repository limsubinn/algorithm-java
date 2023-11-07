import java.io.*;
import java.util.*;

// 백준 18428: 감시 피하기
public class boj18428 {

	static int n;
	static String[][] aisle;
		
	static boolean[][] choose;
	
	static int students = 0;
	static List<int[]> teachers = new ArrayList<>();
	static List<int[]> barriers = new ArrayList<>();
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		aisle = new String[n][n];
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				aisle[i][j] = st.nextToken();
				
				// 선생님 좌표 저장
				if (aisle[i][j].equals("T")) {
					teachers.add(new int[] {i, j});
					continue;
				}
				
				// 학생 수 저장
				if (aisle[i][j].equals("S")) {
					students++;
				}
			}
		}
		
		choose = new boolean[n][n];
		combination(0);
		
		System.out.println("NO");		
	}
	
	static void combination(int cnt) {
		// 장애물 3개일 때
		if (cnt == 3) {
			// 장애물 만들기
			changeNodes("O");
			
			// 모든 학생들이 감시를 피할 수 있는 경우
			if (find()) {
				System.out.println("YES");
				System.exit(0);
			}
			
			// 원래 상태로 돌리기
			changeNodes("X");
			return;
		}
		
		// 장애물 설치
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (choose[i][j] || !aisle[i][j].equals("X")) {
					continue;
				}
				
				choose[i][j] = true;
				barriers.add(new int[] {i, j});
				
				combination(cnt+1);
				
				choose[i][j] = false;
				barriers.remove(cnt);
			}
		}
	}
	
	static void changeNodes(String target) {
		int x, y;
		
		for (int[] node: barriers) {
			x = node[0];
			y = node[1];
			
			aisle[x][y] = target;
		}
	}
	
	static boolean find() {
		int x, y;
		int mx, my;
		int j;
		
		for (int[] node: teachers) {
			x = node[0];
			y = node[1];
			
			// 선생님 좌표를 기준으로 상하좌우 끝까지 확인
			for (int i=0; i<4; i++) {
				j = 1;
				while (true) {
					mx = x + dx[i] * j;
					my = y + dy[i] * j++;
										
					if (mx < 0 || mx >= n || my < 0 || my >= n) {
						break;
					}
					
					// 장애물을 만나면 더이상 탐색 불가능
					if (aisle[mx][my].equals("O")) {
						break;
					}
					
					// 감시 피하기 불가능
					if (aisle[mx][my].equals("S")) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
}
