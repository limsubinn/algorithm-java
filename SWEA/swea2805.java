import java.io.*;
import java.util.*;

// swea2805. 농작물 수확하기
public class swea2805 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            // 농장의 크기
            int n = Integer.parseInt(br.readLine());

            // 농장 내 농작물의 가치
            int board[][] = new int[n][n];
            for (int i=0; i<n; i++) {
                board[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            }

            int start = n / 2;
            int end = start;
            int answer = 0;

            for (int x=0; x<n; x++) {
                // 가운데부터 시작해서 값 더하기
                for (int y=start; y<=end; y++) {
                    answer += board[x][y];
                }

                if (x < n/2) { // n/2보다 작은 행에서는 탐색 범위 늘리기
                    start--;
                    end++;
                } else { // n/2보다 큰 행에서는 탐색 범위 줄이기
                    start++;
                    end--;
                }
            }

            bw.write("#" + t + " " + answer + "\n");
        }
        bw.flush();
        bw.close();
    }

}