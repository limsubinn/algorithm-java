import java.io.*;
import java.util.*;

// 백준 2563: 색종이
public class boj2563 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int x, y;
		int answer = 0;
		boolean[][] visited = new boolean[101][101];
		
		StringTokenizer st;
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			for (int a=x+1; a<=x+10; a++) {
				for (int b=y+1; b<=y+10; b++) {
					// 이미 색종이가 있는 영역이면 넘어가기
					if (visited[a][b]) {
						continue;
					}
					// 색종이 추가
					visited[a][b] = true;
					answer++;
				}
			}
		}

		System.out.println(answer);
	}

}
