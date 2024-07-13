import java.io.*;
import java.util.*;

/**
 * swea 1486. 장훈이의 높은 선반
 */
public class swea1486 {

    static int n;
    static int b;
    static int[] h;

    static int sum;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            h = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i=0; i<n; i++) {
                h[i] = Integer.parseInt(st.nextToken());
            }

            answer = Integer.MAX_VALUE;
            // 모든 개수의 조합을 구함
            for (int i=1; i<=n; i++) {
                sum = 0; // 점원 키의 합
                combination(0, 0, i);
            }

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static void combination(int start, int cnt, int target) {
        if (cnt == target) {
            if (sum >= b) {
                answer = Math.min(sum - b, answer);
            }
            return;
        }

        for (int i=start; i<n; i++) {
            sum += h[i];
            combination(i+1, cnt+1, target);
            sum -= h[i];
        }
    }
}
