import java.io.*;
import java.util.*;

// 백준 16935: 배열 돌리기 3
public class boj16935 {

	static int n;
	static int m;
	static int r;
	static int[][] array;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		// 배열의 크기
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		// 연산의 수
		r = Integer.parseInt(st.nextToken());
		
		array = new int[n][m];

		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j=0; j<m; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int select;
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i=0; i<r; i++) {
			// 연산
			select = Integer.parseInt(st.nextToken());
			
			switch (select) {
				case 1: upDown(); break;
				case 2: leftRight(); break;
				case 3: rotateRight(); break;
				case 4: rotateLeft(); break;
				case 5: nextPart(); break;
				case 6: previousPart();
			}
		}
		
		// 정답 출력
		for (int a=0; a<array.length; a++) {
			for (int b=0; b<array[0].length; b++) {
				sb.append(array[a][b] + " ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	
	static int[][] cloneArray() { // 배열 복사
		n = array.length;
		m = array[0].length;
		
		int[][] temp = new int[n][m];

		for (int i=0; i<n; i++) {
			System.arraycopy(array[i], 0, temp[i], 0, m);
		}

		return temp;
	}
	
	static void upDown() { // 상하 반전
		n = array.length;
		m = array[0].length;
		
		int[][] temp = cloneArray();
		
		for (int i=0; i<n; i++) {
			array[n-1-i] = temp[i];
		}
	}
	
	static void leftRight() { // 좌우 반전
		n = array.length;
		m = array[0].length;
		
		int[][] temp = cloneArray();
		
		for (int i=0; i<n; i++) {
			for (int j=m-1; j>=0; j--) {
				array[i][m-1-j] = temp[i][j];
			}
		}
	}
	
	static void rotateRight() { // 오른쪽으로 90도 회전
		n = array.length;
		m = array[0].length;
		
		int[][] temp = cloneArray();
		array = new int[m][n];
		
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				array[i][j] = temp[n-1-j][i];
			}
		}
	}
	
	static void rotateLeft() { // 왼쪽으로 90도 회전
		n = array.length;
		m = array[0].length;
		
		int[][] temp = cloneArray();
		array = new int[m][n];
		
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				array[i][j] = temp[j][m-1-i];
			}
		}
	}
	
	static void nextPart() { // 1 -> 2 -> 3 -> 4 -> 1
		n = array.length;
		m = array[0].length;
		
		int[][] temp = cloneArray();

		// 4 -> 1
		for (int i=0; i<n/2; i++) {
			for (int j=0; j<m/2; j++) {
				array[i][j] = temp[i+n/2][j];
			}
		}
		
		// 1 -> 2
		for (int i=0; i<n/2; i++) {
			for (int j=m/2; j<m; j++) {
				array[i][j] = temp[i][j-m/2];
			}
		}
		
		// 2 -> 3
		for (int i=n/2; i<n; i++) {
			for (int j=m/2; j<m; j++) {
				array[i][j] = temp[i-n/2][j];
			}
		}
		
		// 3 -> 4
		for (int i=n/2; i<n; i++) {
			for (int j=0; j<m/2; j++) {
				array[i][j] = temp[i][j+m/2];
			}
		}
	}
	
	static void previousPart() { // 1 -> 4 -> 3 -> 2 -> 1
		n = array.length;
		m = array[0].length;
		
		int[][] temp = cloneArray();

		// 1 -> 4
		for (int i=n/2; i<n; i++) {
			for (int j=0; j<m/2; j++) {
				array[i][j] = temp[i-n/2][j];
			}
		}
		
		// 4 -> 3
		for (int i=n/2; i<n; i++) {
			for (int j=m/2; j<m; j++) {
				array[i][j] = temp[i][j-m/2];
			}
		}
		
		// 3 -> 2
		for (int i=0; i<n/2; i++) {
			for (int j=m/2; j<m; j++) {
				array[i][j] = temp[i+n/2][j];
			}
		}
		
		// 2 -> 1
		for (int i=0; i<n/2; i++) {
			for (int j=0; j<m/2; j++) {
				array[i][j] = temp[i][j+m/2];
			}
		}
	}

}
