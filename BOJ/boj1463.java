import java.util.*;

// 백준 1463: 1로 만들기
public class boj1463 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] dp = new int[n + 1];
        dp[1] = 0;

        for (int i=2; i<=n; i++) {
            // 1을 빼는 경우
            dp[i] = dp[i-1] + 1;

            // X가 3으로 나누어 떨어지는 경우
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i/3] + 1, dp[i]);
            }

            // X가 2로 나누어 떨어지는 경우
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i/2] + 1, dp[i]);
            }
        }

        System.out.println(dp[n]);
    }
}
