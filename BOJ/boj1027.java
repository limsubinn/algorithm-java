import java.io.*;
import java.util.*;

// 백준 1027: 고층 건물
public class boj1027 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] buildings = new int[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i=1; i<=n; i++) {
			buildings[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		int dx[] = {-1, 1}; // x 좌표 이동 배열
		
		for (int i=1; i<=n; i++) {
			// 빌딩들을 차례대로 순회
			int x = i;
			int y = buildings[i];
			
			// 보이는 빌딩의 수
			int cnt = 0;
			
			for (int j=0; j<2; j++) {
				float inclination; // 기울기
				
				if (j == 0) { // 왼쪽으로 가는 경우
					inclination = Integer.MAX_VALUE;
				} else { // 오른쪽으로 가는 경우
					inclination = -Integer.MAX_VALUE;
				}

				// 이동 칸 수
				int c = 1;
				
				while (true) {
					// 이동
					int mx = x + dx[j] * c++;
					if (mx < 1 || mx > n) {
						break;
					}
					int my = buildings[mx];
				
					// 기울기
					float d = (float)(my - y) / (mx - x);
					
					if (j == 0) { // 왼쪽으로 가는 경우
						// 현재의 기울기가 기준 기울기보다 작을 경우 기울기를 갱신하고 보이는 빌딩 수 추가
						if (d < inclination) {
							inclination = d;
							cnt++;
						}
					} else { // 오른쪽으로 가는 경우
						// 현재의 기울기가 기준 기울기보다 클 경우 기울기를 갱신하고 보이는 빌딩 수 추가
						if (d > inclination) {
							inclination = d;
							cnt++;
						}
					}
				}
			}
			
			// 최댓값 갱신
			answer = Math.max(answer, cnt);
		}
		
		System.out.println(answer);
	}
}
