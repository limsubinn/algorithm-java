import java.io.*;
import java.util.*;

/**
 * 백준 17266. 어두운 굴다리
 */
public class boj17266 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b;

        int answer = a; // 초기값 (굴다리 처음 ~ 첫 가로등)
        for (int i = 0; i < m-1; i++) {
            b = Integer.parseInt(st.nextToken());
            answer = (int) Math.max(answer, Math.round((b - a) * 0.5)); // 가로등 ~ 가로등
            a = b;
        }
        answer = Math.max(answer, n - a); // 마지막 가로등 ~ 굴다리 끝

        System.out.println(answer);
    }
}
