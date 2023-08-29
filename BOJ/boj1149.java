import java.io.*;
import java.util.*;

// 백준 1149: RGB 거리
public class boj1149 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[][] cost = new int[n][3]; // 각 집을 칠하는 비용
		int[][] result = new int[n][3]; // 집을 칠하는 비용의 최솟값을 저장할 배열
		
		for (int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j=0; j<3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// result 배열 초기화
		for (int j=0; j<3; j++) {
			result[0][j] = cost[0][j];
		}
		
		for (int i=1; i<n; i++) {
			for (int j=0; j<3; j++) {
				result[i][j] = Integer.MAX_VALUE;
			}
		}
		
		// 각 집을 순회하면서 최소 비용 계산
		for (int i=1; i<n; i++) {
			for (int j=0; j<3; j++) {
				for (int k=0; k<3; k++) {
					// 같은 색이면 넘어간다.
					if (j == k) {
						continue;
					}
					// 최솟값 갱신
					if (result[i-1][k] + cost[i][j] < result[i][j]) {
						result[i][j] = result[i-1][k] + cost[i][j];
					}
				}
			}
		}
		
		// 마지막 행의 최솟값 찾기
		int answer = result[n-1][0];
		for (int i=0; i<3; i++) {
			if (result[n-1][i] < answer) {
				answer = result[n-1][i];
			}
		}
		
		// 정답 출력
		System.out.println(answer);
	}

}
