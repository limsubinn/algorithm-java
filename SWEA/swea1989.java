import java.io.*;

/**
 * swea 1989. 초심자의 회문 검사
 */
public class swea1989 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        String word;

        for (int t=1; t<=T; t++) {
            word = br.readLine();

            sb.append("#").append(t).append(" ");
            // 뒤집기
            if (new StringBuffer(word).reverse().toString().equals(word)) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
