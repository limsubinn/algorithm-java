import java.io.*;
import java.util.*;

// 백준 15650: N과 M (2)
public class boj15650 {

    static int n, m;
    static int[] result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        result = new int[m];

        combinations(0, 1);
        System.out.print(sb);
    }

    // 조합
    static void combinations(int cnt, int start) {
        // 길이가 m을 만족할 경우
        if (cnt == m) {
            for (int res: result) {
                sb.append(res + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i=start; i<=n; i++) {
            result[cnt] = i;
            combinations(cnt+1, i+1);
        }
    }
}
