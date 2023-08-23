import java.io.*;
import java.util.*;

// 백준 15683: 감시
public class boj15683 {
	
	static int n, m;
	static int[][] map; // 사무실
	static int[][] temp; // 복사한 사무실
	
	static int answer = Integer.MAX_VALUE;
	static int size; // cctv 개수
	static int[] choice; // 중복순열 결과
	static ArrayList<int[]> cctv = new ArrayList<>(); // cctv 저장
	
	// 이동 좌표 저장할 리스트
	static ArrayList<int[][]> dx = new ArrayList<>();
	static ArrayList<int[][]> dy = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				// cctv 저장
				if (map[i][j] != 0 && map[i][j] != 6) {
					cctv.add(new int[]{i, j});
				}
			}
		}
		
		size = cctv.size();
		choice = new int[size];
		
		// 이동 좌표 각각 네 방향 나눠서 저장 (1번~5번)
		dx.add(new int[][] {{0}, {1}, {0}, {-1}});
		dx.add(new int[][] {{0, 0}, {1, -1}, {0, 0}, {1, -1}});
		dx.add(new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}});
		dx.add(new int[][] {{0, -1, 0}, {-1, 0, 1}, {0, 1, 0}, {1, 0, -1}});
		dx.add(new int[][] {{1, 0, -1, 0}, {1, 0, -1, 0}, {1, 0, -1, 0}, {1, 0, -1, 0}});
		
		dy.add(new int[][] {{1}, {0}, {-1}, {0}});
		dy.add(new int[][] {{1, -1}, {0, 0}, {1, -1}, {0, 0}});
		dy.add(new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}});
		dy.add(new int[][] {{-1, 0, 1}, {0, 1, 0}, {1, 0, -1}, {0, -1, 0}});
		dy.add(new int[][] {{0, 1, 0, -1}, {0, 1, 0, -1}, {0, 1, 0, -1}, {0, 1, 0, -1}});
		
		choose(0);	
		System.out.println(answer);
	}
	
	// 배열 복사
	static void cloneArray() {
		temp = new int[n][m];
		
		for (int i=0; i<n; i++) {
			System.arraycopy(map[i], 0, temp[i], 0, m);
		}
	}
	
	// 중복순열
	static void choose(int cnt) {
		if (cnt == size) { // 선택 완료
			cloneArray();
			
			// cctv 순회
			for (int i=0; i<size; i++) {
				int[] c = cctv.get(i);
				// 감시하기
				watch(c[0], c[1], temp[c[0]][c[1]], choice[i]);
			}
			// 정답 갱신
			answer = Math.min(answer, getCnt());

			return;
		}
		
		for (int i=0; i<4; i++) {
			choice[cnt] = i;
			choose(cnt+1);
		}
	}
	
	static void watch(int x, int y, int d, int c) {
		for (int i=0; i<dx.get(d-1)[0].length; i++) {
			int cnt = 1; // 이동 칸 수
			
			while (true) {
				// 이동
				int mx = x + dx.get(d-1)[c][i] * cnt;
				int my = y + dy.get(d-1)[c][i] * cnt;
				
				// 좌표가 범위를 벗어나면 종료
				if (mx < 0 || mx >= n || my < 0 || my >= m) {
					break;
				}
				
				// 벽을 만나면 종료
				if (temp[mx][my] == 6) {
					break;
				}
				
				// 감시
				if (temp[mx][my] == 0) {
					temp[mx][my] = -1;
				}
				
				// 이동 칸 수 늘리기
				cnt++;
			}
		}
	}
	
	// 사각지대 개수 세기
	static int getCnt() {
		int cnt = 0;
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				// 0인 부분의 개수를 센다.
				if (temp[i][j] == 0) {
					cnt++;
				}
			}
		}
		
		return cnt;
	}
}
