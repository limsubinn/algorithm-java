import java.io.*;
import java.util.*;

// swea2001. 파리 퇴치
public class swea2001 {
    static int n;
    static int m;
    static int[][] flies;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            flies = new int[n][n];
            for (int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j=0; j<n; j++) {
                    flies[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;
            for (int i=0; i<n-m+1; i++) {
                for (int j=0; j<n-m+1; j++) {
                    // 파리채 내리친 후 최댓값 갱신
                    answer = Math.max(answer, find(i, j));
                }
            }

            bw.write("#" + t + " " + answer + "\n");
        }

        bw.flush();
        bw.close();

    }

    public static int find(int x, int y) {
        int cnt = 0;

        for (int i=0; i<m; i++) {
            for (int j=y; j<y+m; j++) {
                cnt += flies[x+i][j];
            }
        }

        return cnt;
    }

}