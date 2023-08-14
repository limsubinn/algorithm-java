import java.io.*;
import java.util.*;

// 백준 15649: N과 M (1)
public class boj15649 {

    static int n, m;
    static int[] result;
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        result = new int[m];
        visited = new boolean[n];

        permutation(0);
        System.out.print(sb);
    }

    // 순열
    static void permutation(int cnt) {
        // 길이가 m을 만족할 경우
        if (cnt == m) {
            for (int res: result) {
                sb.append(res + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i=1; i<=n; i++) {
            if (visited[i-1]) {
                continue;
            }

            result[cnt] = i;
            visited[i-1] = true;
            permutation(cnt + 1);
            visited[i-1] = false;
        }
    }
}
