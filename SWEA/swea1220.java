import java.io.*;
import java.util.*;

/**
 * swea 1220. Magnetic
 */
public class swea1220 {

    static int n;
    static int[][] table;
    static int[] directions = {0, 1, -1}; // N극 -> 아래로, S극 -> 위로

    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int t=1; t<=10; t++) {
            n = Integer.parseInt(br.readLine());
            table = new int[n][n];

            for (int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j=0; j<n; j++) {
                    table[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = 0;
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    // 자성체 반응
                    if (table[i][j] == 1 || table[i][j] == 2) {
                        move(table[i][j], i, j);
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static void move(int d, int x, int y) {
        table[x][y] = 0;

        int direction = directions[d];
        int mx;

        for (int i=1; i<n; i++) {
            mx = x + direction * i;

            // 범위를 벗어나거나 교착 상태를 만난 경우
            if (mx >= n || mx < 0 || table[mx][y] == 3) {
                return;
            }

            // 자성체가 없거나 같은 자성체인 경우
            if (table[mx][y] == 0 || table[mx][y] == d) {
                table[mx][y] = 0;
                continue;
            }

            // 서로 다른 자성체인 경우
            table[mx][y] = 3;
            answer++;
            return;
        }
    }
}
