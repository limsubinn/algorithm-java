import java.io.*;
import java.util.*;

// 백준 2234: 성곽
public class boj2234 {

    static int n, m;
    static int maxSize = 0; // 가장 넓은 방의 넓이
    static int deleteMaxSize = 0; // 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기

    static int[][] castle;
    static int[][] visited;
    static List<Integer> list = new LinkedList<>();

    // 서, 북, 동, 남
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        castle = new int[n][m];
        visited = new int[n][m];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                castle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        list.add(0);
        int cnt = 0; // 방의 개수
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (visited[i][j] == 0) {
                    bfs(i, j, ++cnt);
                }
            }
        }

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                deleteWall(i, j);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append("\n").append(maxSize).append("\n").append(deleteMaxSize);
        System.out.println(sb);
    }

    static void bfs(int x, int y, int cnt) {
        int q[] = {x, y};
        int mx, my, k;
        int size = 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(q);
        visited[x][y] = cnt;

        while (!queue.isEmpty()) {
            q = queue.poll();
            x = q[0];
            y = q[1];

            for (int i=0; i<4; i++) {
                // 벽이 있는 경우
                k = 1 << i;
                if ((castle[x][y] & k) == k) {
                    continue;
                }

                // 벽이 없는 경우
                mx = x + dx[i];
                my = y + dy[i];

                if (visited[mx][my] != 0) {
                    continue;
                }

                queue.add(new int[]{mx, my});
                visited[mx][my] = cnt;
                size++;
            }
        }
        // 방의 크기 저장
        list.add(size);

        // 가장 넓은 방의 넓이 갱신
        maxSize = Math.max(maxSize, size);
    }

    static void deleteWall(int x, int y) {
        int mx, my;
        int a = visited[x][y];
        int b;

        for (int i=0; i<4; i++) {
            mx = x + dx[i];
            my = y + dy[i];

            if (mx < 0 || mx >= n || my < 0 || my >= m) {
                continue;
            }

            b = visited[mx][my];
            if (a != b) { // 벽 제거
                deleteMaxSize = Math.max(deleteMaxSize, list.get(a) + list.get(b));
            }
        }
    }
}
