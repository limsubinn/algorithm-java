import java.io.*;
import java.util.*;

// 백준 1113: 수영장 만들기
public class boj1113 {

    static int n, m;
    static int[][] ground;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 땅의 정보
        ground = new int[n][m];
        for (int i=0; i<n; i++) {
            String[] temp = br.readLine().split("");
            for (int j=0; j<m; j++) {
                ground[i][j] = Integer.parseInt(temp[j]);
            }
        }

        int answer = 0, result = 0;
        // 높이 2~9 채우기
        for (int k=2; k<=9; k++) {
            visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    // 방문한 적 없고, 더 낮은 높이의 땅인 경우 탐색
                    if (!visited[i][j] && ground[i][j] < k) {
                        result = bfs(i, j, k);
                        // 수영장을 만들 수 없는 경우
                        if (result == -1) {
                            continue;
                        }
                        answer += result;
                    }
                }
            }
        }

        System.out.println(answer);

    }

    static int bfs(int x, int y, int num) {
        int q[] = {x, y};
        int mx, my;
        int result = 1;
        boolean flag = true; // 수영장을 만들 수 있는지

        Queue<int[]> queue = new LinkedList<>();
        queue.add(q);
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            q = queue.poll();
            x = q[0];
            y = q[1];

            for (int i=0; i<4; i++) {
                mx = x + dx[i];
                my = y + dy[i];

                // 수영장을 만들 수 없는 경우
                if (mx < 0 || mx >= n || my < 0 || my >= m) {
                    flag = false;
                    continue;
                }

                // 방문한 적 없고, 더 낮은 높이의 땅인 경우
                if (!visited[mx][my] && ground[mx][my] < num) {
                    result++;
                    visited[mx][my] = true;
                    queue.add(new int[]{mx, my});
                }
            }
        }

        if (!flag) {
            return -1;
        }

        return result;
    }
}
