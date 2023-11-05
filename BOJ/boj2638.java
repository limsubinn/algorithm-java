import java.io.*;
import java.util.*;

// 백준 2638: 치즈
public class boj2638 {

    static int n, m;
    static int[][] graph;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {
            if (isMeltedAll()) {
                break;
            }
            melt();
            answer++;
        }

        System.out.println(answer);
    }

    static void melt() {
        int x = 0, y = 0;
        int mx, my;
        int[] q = {x, y};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(q);

        int[][] visited = new int[n][m];
        visited[x][y] = 1;

        while (!queue.isEmpty()) {
            q = queue.poll();
            x = q[0];
            y = q[1];

            for (int i=0; i<4; i++) {
                mx = x + dx[i];
                my = y + dy[i];

                // 범위를 벗어나는 경우
                if (mx < 0 || mx >= n || my < 0 || my >= m) {
                    continue;
                }

                // 치즈 외부 공기
                if (graph[mx][my] == 0) {
                    if (visited[mx][my] == 0) {
                        visited[mx][my]++;
                        queue.add(new int[]{mx, my});
                    }
                    continue;
                }

                // 치즈
                if (graph[mx][my] == 1) {
                    // 2변 이상이 외부 공기와 접촉한 경우
                    if (visited[mx][my] >= 1) {
                        graph[mx][my] = 0;
                        continue;
                    }
                    // 공기 접촉 횟수 추가
                    visited[mx][my]++;
                }
            }
        }
    }

    static boolean isMeltedAll() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (graph[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
