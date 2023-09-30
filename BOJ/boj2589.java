import java.io.*;
import java.util.*;

// 백준 2589: 보물섬
public class boj2589 {

    static int n, m;
    static char[][] board;
    static int answer = 0;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        for (int i=0; i<n; i++) {
            board[i] = br.readLine().toCharArray();
        }

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                // 육지 탐색
                if (board[i][j] == 'L') {
                    bfs(i, j, 0);
                }
            }
        }

        System.out.println(answer);
    }

    static void bfs(int x, int y, int cnt) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, cnt}); // 좌표 x y, 최단거리 cnt

        boolean[][] visited = new boolean[n][m];
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] q = queue.poll();
            x = q[0];
            y = q[1];
            cnt = q[2];

            for (int i=0; i<4; i++) {
                int mx = x + dx[i];
                int my = y + dy[i];

                if (mx < 0 || mx >= n || my < 0 || my >= m) {
                    continue;
                }

                if (board[mx][my] == 'W') {
                    continue;
                }

                if (visited[mx][my]) {
                    continue;
                }

                queue.add(new int[]{mx, my, cnt+1});
                visited[mx][my] = true;
            }
        }

        // 가장 긴 시간이 걸리는 곳
        answer = Math.max(answer, cnt);
    }
}
