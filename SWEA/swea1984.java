import java.io.*;
import java.util.*;

/**
 * swea 1984. 중간 평균값 구하기
 */
public class swea1984 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int[] numbers = new int[10];
        float sum;

        for (int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());

            for (int i=0; i<10; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            // 정렬
            Arrays.sort(numbers);

            // 최솟값과 최댓값을 제외한 나머지의 합
            sum = 0;
            for (int i=1; i<9; i++) {
                sum += numbers[i];
            }

            // 평균값 출력
            sb.append("#").append(t).append(" ").append(Math.round(sum / 8)).append("\n");
        }

        System.out.print(sb);
    }
}
