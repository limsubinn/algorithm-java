import java.io.*;
import java.util.*;

/**
 * swea 1247. 최적 경로
 */
public class swea1247 {

    static int n;

    static int[] x;
    static int[] y;
    static int[] result;
    static boolean[] visited;

    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            n = Integer.parseInt(br.readLine());

            x = new int[n+2];
            y = new int[n+2];

            st = new StringTokenizer(br.readLine());
            x[0] = Integer.parseInt(st.nextToken());
            y[0] = Integer.parseInt(st.nextToken());
            x[1] = Integer.parseInt(st.nextToken());
            y[1] = Integer.parseInt(st.nextToken());

            for (int i=0; i<n; i++) {
                x[i+2] = Integer.parseInt(st.nextToken());
                y[i+2] = Integer.parseInt(st.nextToken());
            }

            visited = new boolean[n+2];
            result = new int[n];
            answer = Integer.MAX_VALUE;

            permutation(0, 0);

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static void permutation(int cnt, int sum) {
        if (cnt == n) {
            // 회사 -> 첫 번째 고객, 마지막 고객 -> 집 경로 더하기
            sum += distance(0, result[0]) + distance(result[cnt-1], 1);
            answer = Math.min(sum, answer);
            return;
        }

        for (int i=2; i<n+2; i++) {
            // 가지치기
            if (sum >= answer) {
                return;
            }

            if (visited[i]) {
                continue;
            }

            result[cnt] = i;
            visited[i] = true;
            // 경로 더하기
            if (cnt > 0) {
                permutation(cnt+1, sum + distance(result[cnt-1], result[cnt]));
            } else {
                permutation(cnt+1, sum);
            }
            visited[i] = false;
        }
    }

    static int distance(int a, int b) {
        return Math.abs(x[a] - x[b]) + Math.abs(y[a] - y[b]);
    }
}
