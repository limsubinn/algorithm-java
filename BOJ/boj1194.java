import java.io.*;
import java.util.*;

// 백준 1194: 달이 차오른다, 가자.
public class boj1194 {

    static int n, m;
    static char[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        boolean flag = false;
        int x = 0, y = 0;

        for (int i=0; i<n; i++) {
            board[i] = br.readLine().toCharArray();

            if (flag) {
                continue;
            }

            for (int j=0; j<m; j++) {
                // 민식이의 현재 위치 (출발점)
                if (board[i][j] == '0') {
                    flag = true;
                    x = i;
                    y = j;
                }
            }
        }

        int answer = bfs(x, y, 0, 0);
        System.out.println(answer);

    }

    static int bfs(int x, int y, int cnt, int key) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, cnt, key});

        boolean[][][] visited = new boolean[n][m][64];
        visited[x][y][key] = true;

        while (!queue.isEmpty()) {
            int[] q = queue.poll();
            x = q[0];
            y = q[1];
            cnt = q[2];
            key = q[3];

            for (int i=0; i<4; i++) {
                int mx = x + dx[i];
                int my = y + dy[i];

                // 범위 벗어난 경우
                if (mx < 0 || mx >= n || my < 0 || my >= m) {
                    continue;
                }

                // 벽인 경우 or 방문한 경우
                if (board[mx][my] == '#' || visited[mx][my][key]) {
                    continue;
                }

                // 미로 탈출 !!!
                if (board[mx][my] == '1') {
                    return cnt+1;
                }

                // 열쇠인 경우
                if (board[mx][my] >= 97 && board[mx][my] <= 102) {
                    int k = key | (1 << (board[mx][my] - 97));
                    visited[mx][my][k] = true;
                    queue.add(new int[]{mx, my, cnt+1, k});
                    continue;
                }

                // 문인 경우
                if (board[mx][my] >= 65 && board[mx][my] <= 70) {
                    int k = board[mx][my] - 65;

                    // 해당 열쇠가 없는 경우 넘어간다.
                    if ((key & (1 << k)) != (1 << k)) {
                        continue;
                    }

                    visited[mx][my][key] = true;
                    queue.add(new int[]{mx, my, cnt+1, key});
                    continue;
                }

                visited[mx][my][key] = true;
                queue.add(new int[]{mx, my, cnt+1, key});
            }

        }

        return -1;
    }
}
