import java.io.*;

/**
 * swea 1545. 거꾸로 출력해 보아요
 */
public class swea1545 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        for (int i=n; i>=0; i--) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }
}
