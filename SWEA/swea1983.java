import java.io.*;
import java.util.*;

/**
 * swea 1983. 조교의 성적 매기기
 */
public class swea1983 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        int n, k; // 학생 수, 학점을 알고 싶은 학생의 번호
        int a, b, c; // 중간, 기말, 과제
        double[] scores; // 총점

        double temp = 0;
        int j;
        String[] res = {"D0", "C-", "C0", "C+", "B-", "B0", "B+", "A-", "A0", "A+"};

        for (int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            scores = new double[n];
            for (int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());

                scores[i] = a * 0.35 + b * 0.45 + c * 0.2;

                // 학점을 알고 싶은 학생의 점수
                if (i+1 == k) {
                    temp = scores[i];
                }
            }
            // 정렬
            Arrays.sort(scores);

            sb.append("#").append(t).append(" ");

            j = -1;
            for (int i=0; i<n; i+=n/10) { // n명의 학생이 있을 경우 n/10명의 학생들에게 동일한 평점을 부여할 수 있다.
                if (temp < scores[i]) {
                    sb.append(res[j]).append("\n");
                    break;
                }
                j++;
                if (temp == scores[i]) {
                    sb.append(res[j]).append("\n");
                    break;
                }
            }
        }

        System.out.print(sb);
    }
}
