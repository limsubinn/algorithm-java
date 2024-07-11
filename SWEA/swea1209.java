import java.io.*;
import java.util.*;

/**
 * swea 1209. Sum
 */
public class swea1209 {

    static int[][] array;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int answer;
        for (int t=1; t<=10; t++) {
            br.readLine();

            array = new int[100][100];
            for (int i=0; i<100; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<100; j++) {
                    array[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = 0;
            answer = Math.max(answer, rowSum());
            answer = Math.max(answer, colSum());
            answer = Math.max(answer, rightDiagonalSum());
            answer = Math.max(answer, leftDiagonalSum());

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static int rowSum() {
        int result = 0;
        int sum;

        for (int i=0; i<100; i++) {
            sum = 0;
            for (int j=0; j<100; j++) {
                sum += array[i][j];
            }
            result = Math.max(sum, result);
        }

        return result;
    }

    static int colSum() {
        int result = 0;
        int sum;

        for (int j=0; j<100; j++) {
            sum = 0;
            for (int i=0; i<100; i++) {
                sum += array[i][j];
            }
            result = Math.max(sum, result);
        }

        return result;
    }

    static int rightDiagonalSum() {
        int result = 0;

        for (int i=0; i<100; i++) {
            result += array[i][i];
        }

        return result;
    }

    static int leftDiagonalSum() {
        int result = 0;

        for (int i=0; i<100; i++) {
            result += array[i][99-i];
        }

        return result;
    }
}
