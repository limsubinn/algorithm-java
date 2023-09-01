import java.io.*;
import java.util.*;

// 백준 14503: 로봇 청소기
public class boj14503 {

    static int n, m;
    static int r, c;
    static int d;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 방의 크기
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        // 시작 좌표
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        // 시작 방향
        d = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        int cnt = 0;

        // 상, 좌, 하, 우 (북쪽부터 반시계 방향)
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        Queue<int[]> queue = new LinkedList<>();
        d = (4 - d == 4)? 0 : 4-d; // 현재 좌표의 방향
        queue.add(new int[] {r, c, d});

        while (!queue.isEmpty()) {
            int[] q = queue.poll();
            int x = q[0];
            int y = q[1];
            d = q[2];

            // 주변 4칸이 모두 청소되었는지 확인할 변수
            boolean flag = false;

            // 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            if (board[x][y] == 0) {
                board[x][y] = -1;
                cnt++;
            }

            for (int i=0; i<4; i++) {
                // 반시계 방향으로 90도 회전
                d = (d+1 >= 4)? 0 : d+1;

                // 이동
                int mx = x + dx[d];
                int my = y + dy[d];

                // 범위를 벗어나면 넘어간다.
                if (mx < 0 || mx >= n || my < 0 || my >= m) {
                    continue;
                }

                // 벽을 만나거나 이미 청소되어 있는 경우 넘어간다.
                if (board[mx][my] != 0) {
                    continue;
                }

                // 청소하기
                queue.add(new int[] {mx, my, d});
                flag = true; // 청소되지 않은 빈 칸이 있음
                break;
            }

            // 주변 4칸 모두 청소된 칸인 경우
            if (!flag) {
                // 후진
                int mx = x - dx[d];
                int my = y - dy[d];

                // 범위를 벗어나면 멈춘다.
                if (mx < 0 || mx >= n || my < 0 || my >= m) {
                    return cnt;
                }

                // 벽을 만나면 멈춘다.
                if (board[mx][my] == 1) {
                    return cnt;
                }

                queue.add(new int[] {mx, my, d});
            }
        }

        return cnt;
    }

}
