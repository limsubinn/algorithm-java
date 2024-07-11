import java.io.*;
import java.util.*;

/**
 * swea 1249. 보급로
 */
public class swea1249 {

    static int n;
    static int[][] map;
    static int[][] result;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        String[] temp;

        for (int t=1; t<=T; t++) {
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];

            for (int i=0; i<n; i++) {
                temp = br.readLine().split("");

                for (int j=0; j<n; j++) {
                    map[i][j] = Integer.parseInt(temp[j]);
                }
            }

            bfs();
            sb.append("#").append(t).append(" ").append(result[n-1][n-1]).append("\n");
        }

        System.out.print(sb);
    }

    static void bfs() {
        // 복구 시간
        result = new int[n][n];
        for (int i=0; i<n; i++) {
            Arrays.fill(result[i], Integer.MAX_VALUE); // 최댓값으로 채우기
        }
        result[0][0] = 0;

        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});

        int[] q;
        int x, y;
        int mx, my;

        while (!queue.isEmpty()) {
            q = queue.poll();
            x = q[0];
            y = q[1];

            for (int i=0; i<4; i++) {
                mx = x + dx[i];
                my = y + dy[i];

                if (mx < 0 || mx >= n || my < 0 || my >= n) {
                    continue;
                }

                // 최솟값 갱신
                if (result[x][y] + map[mx][my] < result[mx][my]) {
                    result[mx][my] = result[x][y] + map[mx][my];
                    queue.add(new int[]{mx, my});
                }
            }
        }
    }
}
