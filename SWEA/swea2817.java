import java.io.*;
import java.util.*;

/**
 * swea 2817. 부분 수열의 합
 */
public class swea2817 {

    static int n, k;
    static int[] numbers;

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
            k = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            numbers = new int[n];
            for (int i=0; i<n; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            answer = 0;

            // 조합
            for (int i=1; i<=n; i++) {
                sum = 0;
                combination(0, 0, i);
            }

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static void combination(int start, int cnt, int goal) {
        if (cnt == goal) {
            if (sum == k) {
                answer++;
            }
            return;
        }

        for (int i=start; i<n; i++) {
            sum += numbers[i];
            combination(i+1, cnt+1, goal);
            sum -= numbers[i];
        }
    }
}
