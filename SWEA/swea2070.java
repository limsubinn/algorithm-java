import java.io.*;
import java.util.*;

/**
 * swea 2070. 큰 놈, 작은 놈, 같은 놈
 */
public class swea2070 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int a, b;

        for (int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            sb.append("#").append(t).append(" ");
            // 부등호
            if (a < b) {
                sb.append("<");
            } else if (a == b) {
                sb.append("=");
            } else {
                sb.append(">");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
