import java.io.*;
import java.util.*;

// swea1210. Ladder1
public class swea1210 {

    static Integer[][] board = new Integer[100][100];

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int t=1; t<=10; t++) {
            t = Integer.parseInt(br.readLine());

            for (int i=0; i<100; i++) {
                int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                board[i] = Arrays.stream(temp).boxed().toArray(Integer[]::new);
            }


            int y = Arrays.asList(board[99]).indexOf(2);
            bw.write("#" + t + " " + bfs(99, y) + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static int bfs(int x, int y) {
        // 좌우 먼저 살핀 후 위로 이동
        int[] dx = {0, 0, -1};
        int[] dy = {1, -1, 0};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});

        boolean[][] visited = new boolean[100][100];
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] q = queue.poll();
            x = q[0];
            y = q[1];

            // 도착
            if (x == 0) {
                return y;
            }

            for (int i=0; i<3; i++) {
                int mx = x + dx[i];
                int my = y + dy[i];

                if (mx < 0 || mx >= 100 || my < 0 || my >= 100) {
                    continue;
                }

                if (visited[mx][my]) {
                    continue;
                }

                // 사다리 타고 이동
                if (board[mx][my] == 1) {
                    visited[mx][my] = true;
                    queue.add(new int[] {mx, my});
                    break;
                }
            }
        }

        return 0;
    }
}