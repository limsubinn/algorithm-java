import java.io.*;
import java.util.*;

// 백준 17069. 파이프 옮기기 2
public class boj17069 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] graph = new int[n][n];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j=0; j<n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][][] result = new long[n][n][n];
        result[0][0][1] = 1; // 처음 이동

        // 1행 -> 가로 이동만 가능
        for (int i=2; i<n; i++) {
            if (graph[0][i] == 0) {
                result[0][0][i] = result[0][0][i-1];
            }
        }

        // 2행~
        for (int i=1; i<n; i++) {
            for (int j=2; j<n; j++) { // 3열부터 이동 가능
                // 꼭 빈칸이어야 하는 곳
                if (graph[i][j] == 1) {
                    continue;
                }

                // 가로 이동 (가로+대각선)
                result[0][i][j] = result[0][i][j-1] + result[1][i][j-1];

                // 대각선 이동 (가로+세로+대각선)
                if (graph[i][j-1] == 0 && graph[i-1][j] == 0) {
                    result[1][i][j] = result[0][i - 1][j - 1] + result[1][i - 1][j - 1] + result[2][i - 1][j - 1];
                }

                // 세로 이동 (세로+대각선)
                result[2][i][j] = result[1][i-1][j] + result[2][i-1][j];

            }
        }

        long answer = result[0][n-1][n-1] + result[1][n-1][n-1] + result[2][n-1][n-1];
        System.out.println(answer);
    }
}
