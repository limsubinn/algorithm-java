import java.io.*;
import java.util.*;

// 백준 15686: 치킨 배달
public class boj15686 {

	static int n, m;
	static int answer = Integer.MAX_VALUE;
	static int[] choose; // 조합
	static List<int[]> chicken = new ArrayList<>(); // 치킨집
	static List<int[]> house = new ArrayList<>(); // 집
	static List<int[]> list; // m개의 치킨집을 저장할 리스트
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		choose = new int[m];
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j=0; j<n; j++) {
				int temp = Integer.parseInt(st.nextToken());
				
				// 집 좌표 저장
				if (temp == 1) {
					house.add(new int[] {i, j});
					continue;
				}
				
				// 치킨 집 좌표 저장
				if (temp == 2) {
					chicken.add(new int[] {i, j});
				}
			}
		}
		
		combinations(0, 0);
		System.out.println(answer);
		
	}
	
	static void combinations(int cnt, int start) { // 조합
		list = new ArrayList<>();
		
		// m개를 모두 뽑았을 경우
		if (cnt == m) {
			// m개의 치킨 집 리스트 저장
			for (int i=0; i<m; i++) {
				list.add(chicken.get(choose[i]));
			}
			
			int result = 0;
			
			// 거리 계산
			for (int[] h: house) {
				int dist = Integer.MAX_VALUE;
				
				for (int[] l: list) {
					dist = Math.min(dist, Math.abs(h[0] - l[0]) + Math.abs(h[1] - l[1]));
				}
				
				result += dist;
			}
			
			// 정답 갱신
			answer = Math.min(answer, result);
			return;
		}
		
		for (int i=start; i<chicken.size(); i++) {
			choose[cnt] = i;
			combinations(cnt+1, i+1);
		}
	}
}
