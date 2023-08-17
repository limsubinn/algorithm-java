import java.io.*;
import java.util.*;

// 백준 2630: 색종이 만들기
public class boj2630 {
	
	static Integer[][] paper;
	static int[] answer = new int[2];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());	
		
		paper = new Integer[n][n];
		boolean flag = true;
		
		for (int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for (int j=0; j<n; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
			
			// 이미 색종이가 모두 같은 색으로 칠해져 있지 않으면 넘어간다.
			if (!flag) {
				continue;
			}
			
			// 색종이가 모두 같은 색으로 칠해져 있는지 확인
			Set<Integer> set = new HashSet<>(Arrays.asList(paper[i]));
			
			if (set.size() != 1) {
				flag = false;
			}
		}
		
		if (!flag) { // 색종이가 모두 같은 색으로 칠해져 있지 않은 경우 분할
			n /= 2;
			getAnswer(0, 0, n);
			getAnswer(n, 0, n);
			getAnswer(0, n, n);
			getAnswer(n, n, n);
		} else { // 색종이가 모두 같은 색으로 칠해져 있는 경우 정답 추가
			answer[paper[0][0]]++;
		}
		
		// 정답 출력
		System.out.println(answer[0]); // 하얀색
		System.out.println(answer[1]); // 파란색
	}
	
	static Integer[][] copyArray(int x, int y, int size) { // 시작 인덱스가 x, y이고 크기가 size인 부분배열 만들기
		Integer[][] temp = new Integer[size][size];
		
		for (int i=0; i<size; i++) {
			System.arraycopy(paper[x+i], y, temp[i], 0, size);
		}
		
		return temp;
	}
	
	static void getAnswer(int x, int y, int size) {
		Integer[][] array = copyArray(x, y, size);
		int temp = array[0][0];
		boolean flag = true;
		
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				// 색종이가 모두 같은 색으로 칠해져 있는지 확인
				if (temp != array[i][j]) {
					flag = false;
					break;
				}
			}
			
			if (!flag) {
				break;
			}
		}
		
		// 색종이가 모두 같은 색으로 칠해져 있는 경우 정답 추가하고 리턴
		if (flag) {
			answer[temp]++;
			return;
		}
		
		// 색종이가 모두 같은 색으로 칠해져 있지 않은 경우 분할
		size /= 2;
		getAnswer(x, y, size);
		getAnswer(x+size, y, size);
		getAnswer(x, y+size, size);
		getAnswer(x+size, y+size, size);
	}

}
