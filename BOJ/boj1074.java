import java.io.*;
import java.util.*;

// 백준 1074: Z
public class boj1074 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken()) - 1;
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int answer = 0;

        while (n >= 0) {
            // 왼쪽 위칸인 경우
            // answer += Math.pow(2, 2*n) * 0;
            // (아무것도 수행하지 않음)

            if (r < Math.pow(2, n) && c >= Math.pow(2, n)) { // 오른쪽 위칸인 경우
                answer += Math.pow(2, 2*n);
                c -= Math.pow(2, n);
            } else if (r >= Math.pow(2, n) && c < Math.pow(2, n)) { // 왼쪽 아래칸인 경우
                answer += Math.pow(2, 2*n) * 2;
                r -= Math.pow(2, n);
            } else if (r >= Math.pow(2, n) && c >= Math.pow(2, n)) { // 오른쪽 아래칸인 경우
                answer += Math.pow(2, 2*n) * 3;
                r -= Math.pow(2, n);
                c -= Math.pow(2, n);
            }

            n--;
        }

        System.out.println(answer);
    }
}
