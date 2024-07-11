import java.io.*;
import java.util.*;

/**
 * swea 1226. 미로1
 */
public class swea1226 {

    static int sx, sy; // 출발점
    static int ex, ey; // 도착점
    static char[][] miro;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t=1; t<=10; t++) {
            br.readLine();

            miro = new char[16][16];
            sx = sy = ex = ey = -1;

            for (int i=0; i<16; i++) {
                miro[i] = br.readLine().toCharArray();

                if (sx != -1 && ex != -1) {
                    continue;
                }

                for (int j=0; j<16; j++) {
                    // 출발점
                    if (miro[i][j] == '2') {
                        sx = i;
                        sy = j;
                        continue;
                    }
                    // 도착점
                    if (miro[i][j] == '3') {
                        ex = i;
                        ey = j;
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(bfs()).append("\n");
        }

        System.out.print(sb);
    }

    static int bfs() {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{sx, sy});
        miro[sx][sy] = '1';

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

                if (mx < 0 || mx >= 16 || my < 0 || my >= 16) {
                    continue;
                }

                // 도착
                if (miro[mx][my] == '3') {
                    return 1;
                }

                // 벽 or 이미 방문한 경우
                if (miro[mx][my] == '1') {
                    continue;
                }

                miro[mx][my] = '1';
                queue.add(new int[]{mx, my});
            }
        }

        return 0;
    }
}
