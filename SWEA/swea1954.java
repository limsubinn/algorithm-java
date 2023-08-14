import java.io.*;
import java.util.*;

// swea1954. 달팽이 숫자
public class swea1954 {

    static int[][] result;
    static int n;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            n = Integer.parseInt(br.readLine());
            result = new int[n][n];

            sol();

            bw.write("#" + t + "\n");
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    bw.write(result[i][j] + " ");
                }
                bw.write("\n");
            }
        }

        bw.flush();
        bw.close();
    }

    public static void sol() {
        int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0, 1});

        while (!queue.isEmpty()) {
            int[] q = queue.poll();
            int x = q[0];
            int y = q[1];
            int m = q[2];
            int cnt = q[3];

            // 숫자 채우기
            result[x][y] = cnt;

            // 모든 숫자를 채웠을 경우 종료
            if (cnt >= n*n) {
                break;
            }

            // 다음 칸 이동
            int mx = x + move[m][0];
            int my = y + move[m][1];
            cnt++;

            // 방향 바꾸기
            if ((mx < 0 || mx >= n || my < 0 || my >= n) || result[mx][my] > 0) {
                m++;
                if (m >= 4) {
                    m = 0;
                }
                mx = x + move[m][0];
                my = y + move[m][1];
            }

            queue.add(new int[]{mx, my, m, cnt});
        }
    }

}