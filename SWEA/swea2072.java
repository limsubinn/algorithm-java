import java.io.*;
import java.util.*;

/**
 * swea 2072. 홀수만 더하기
 */
public class swea2072 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        int sum;
        int n;

        for (int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());

            sum = 0;
            for (int i=0; i<10; i++) {
                n = Integer.parseInt(st.nextToken());
                // 홀수만 더하기
                if (n % 2 != 0) {
                    sum += n;
                }
            }

            sb.append("#").append(t).append(" ").append(sum).append("\n");
        }

        System.out.println(sb);
    }
}
