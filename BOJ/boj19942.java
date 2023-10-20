import java.io.*;
import java.util.*;

// 백준 19942: 다이어트
public class boj19942 {

	static int n;
	static int answer = Integer.MAX_VALUE;
	static ArrayList<Integer> answers;
	
	static int[] m = new int[4];
	static int[][] info;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 단백질, 지방, 탄수화물, 비타민의 최소 영양성분
		for (int i=0; i<4; i++) {
			m[i] = Integer.parseInt(st.nextToken());
		}
		
		info = new int[n][5];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			// 식재료의 단백질, 지방, 탄수화물, 비타민, 가격
			for (int j=0; j<5; j++) {
				info[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[] visited = new boolean[n];
		for (int i=1; i<n; i++) {
			find(0, visited, 0, 0, 0, 0, 0);

		}
		
		if (answer == Integer.MAX_VALUE) {
			sb.append(-1);
		} else {
			sb.append(answer).append("\n");
			for (int a: answers) {
				sb.append(a).append(" ");
			}
		}
		
		System.out.println(sb);
	}
	
	
	static void find(int start, boolean[] visited, int mp, int mf, int ms, int mv, int p) {
		// 조건을 만족하는 경우
		if (isSatisfied(mp, mf, ms, mv)) {
		
			// 최소 비용이 아닌 경우 X
			if (answer <= p) {
				return;
			}
			
			answers = new ArrayList<>();
			
			for (int i=0; i<n; i++) {
				if (visited[i]) {
					answers.add(i+1);
				}
			}

			answer = p;
						
			return;
		}
		
		// 조합
		for (int i=start; i<n; i++) {
			if (visited[i]) {
				continue;
			}
			
			visited[i] = true;
			
			find(i+1, visited,
					mp + info[i][0], mf + info[i][1], ms + info[i][2],
					mv + info[i][3], p + info[i][4]);
			
			visited[i] = false;
		}
	}
	
	static boolean isSatisfied(int mp, int mf, int ms, int mv) {
		// 단백질, 지방, 탄수화물, 비타민 중 최소 영양성분을 하나라도 만족하지 못하면 false
		if (mp < m[0]) {
			return false;
		}
		
		if (mf < m[1]) {
			return false;
		}
		
		if (ms < m[2]) {
			return false;
		}
		
		if (mv < m[3]) {
			return false;
		}
		
		// 최소 영양성분을 모두 만족하면 true
		return true;
	}

}
