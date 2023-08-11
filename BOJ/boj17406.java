import java.io.*;
import java.util.*;

// 백준 17406: 배열 돌리기 4
public class boj17406 {

    static int n, m, k;
    static int r, c, s;
    static int[][] operation;
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[][] a = new int[n][m];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for (int j=0; j<m; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        operation = new int[k][3];
        p = new int[k];

        for (int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            // 연산
            operation[i][0] = Integer.parseInt(st.nextToken()) - 1;
            operation[i][1] = Integer.parseInt(st.nextToken()) - 1;
            operation[i][2] = Integer.parseInt(st.nextToken());

            // 순서
            p[i] = i;
        }

        int answer = Integer.MAX_VALUE;

        do {
            // 배열 복사
            int[][] temp = cloneArray(a);

            for (int i: p) {
                // 연산
                r = operation[i][0];
                c = operation[i][1];
                s = operation[i][2];

                // 배열 돌리기
                temp = rotate(temp);
            }

            // 최솟값 갱신
            answer = Math.min(answer, getMin(temp));

        } while(np(p)); // 순열

        System.out.println(answer);

    }

    private static boolean np(int[] p) { // p: 다음 순열을 원하는 기존 순열의 배열
        // 1.맨 뒤쪽부터 탐색하며 꼭대기 찾기
        int N = p.length;
        int i = N-1;
        while(i>0 && p[i-1]>=p[i]) --i;

        if(i==0) return false; // 다음 순열은 없음(가장 큰 순열의 형태)

        // 2. 꼭대기 직전(i-1) 위치에 교환할 한 단계 큰 수를 뒤쪽부터 찾기
        int j = N-1;
        while(p[i-1] >= p[j]) --j;

        // 3. 꼭대기 직전(i-1) 위치에 수와 한단계 큰 수를 교환하기
        swap(p, i-1, j);

        // 4. 대기자리부터 맨 뒤까지의 수를 오름차순의 형태로 바꿈
        int k = N-1;
        while(i<k) {
            swap(p, i++, k--);
        }

        return true;
    }

    private static void swap(int[] p, int a, int b) {
        int temp = p[a];
        p[a] = p[b];
        p[b] = temp;
    }

    static int[][] cloneArray(int[][] array) { // 배열 복사
        int[][] temp = new int[n][m];

        for (int i=0; i<n; i++) {
            System.arraycopy(array[i], 0, temp[i], 0, m);
        }

        return temp;
    }

    static int[][] rotate(int[][] array) { // 배열 돌리기
        int[][] temp = cloneArray(array);

        for (int i=1; i<=s; i++) {
            // 오른쪽으로 이동
            for (int y=c-i; y<c+i; y++) {
                temp[r-i][y+1] = array[r-i][y];
            }

            // 아래쪽으로 이동
            for (int x=r-i; x<r+i; x++) {
                temp[x+1][c+i] = array[x][c+i];
            }

            // 왼쪽으로 이동
            for (int y=c+i; y>c-i; y--) {
                temp[r+i][y-1] = array[r+i][y];
            }

            // 위쪽으로 이동
            for (int x=r+i; x>r-i; x--) {
                temp[x-1][c-i] = array[x][c-i];
            }
        }

        return temp;
    }

    static int getMin(int[][] array) { // 행별 최솟값 구하기
        int result = Integer.MAX_VALUE;

        for (int i=0; i<n; i++) {
            int sum = 0;

            for (int j=0; j<m; j++) {
                sum += array[i][j];
            }

            result = Math.min(result, sum);
        }

        return result;
    }
}
