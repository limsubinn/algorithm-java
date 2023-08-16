import java.io.*;

// 백준 1992: 쿼드트리
public class boj1992 {
	
	static String[][] array;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		array = new String[n][n];
		for (int i=0; i<n; i++) {
			array[i] = br.readLine().split("");
		}
		
		zip(0, 0, n);
		System.out.println(sb);
	}
	
	// 결과값 받기
	static String getResult(String[][] a) {
		String temp = a[0][0];
		
		for (int i=0; i<a.length; i++) {
			for (int j=0; j<a[0].length; j++) {
				// 주어진 영상이 0과 1이 섞여 있으면 -1 리턴
				if (!temp.equals(a[i][j])) {
					return "-1";
				}
			}
		}
		
		// 주어진 영상이 모두 0으로만 되어 있으면 압축 결과는 0
		// 주어진 영상이 모두 1로만 되어 있으면 압축 결과는 1
		return temp;
	}
	
	// 부분배열 만들기
	// 시작 인덱스가 원본 배열의 x, y 이고 배열의 크기가 size인 부분배열을 만든다.
	static String[][] makeSubArray(int x, int y, int size) {
		String[][] subArray = new String[size][size];
		
		for (int i=0; i<size; i++) {
			System.arraycopy(array[x+i], y, subArray[i], 0, size);
		}
		
		return subArray;
	}
	
	// 압축
	static void zip(int x, int y, int size) {
		String result = getResult(makeSubArray(x, y, size));
		
		// 주어진 영상이 0과 1이 섞여 있는 경우 분할
		if (result.equals("-1")) {
			size /= 2;

			sb.append("(");
			zip(x, y, size);
			zip(x, y+size, size);
			zip(x+size, y, size);
			zip(x+size, y+size, size);
			sb.append(")");
			
			return;
		}
		
		// 주어진 영상이 모두 같은 숫자이면 해당 숫자 붙이기
		sb.append(result);
	}

}
