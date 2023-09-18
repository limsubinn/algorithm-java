import java.io.*;
import java.util.*;

// 백준 16236: 아기 상어
public class boj16236 {

    static int n;
    static int size = 2;
    static int answer = 0;
    static int[][] board;

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        int[] info = {0, 0, 0};
        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == 9) {
                    info = new int[]{i, j, 0}; // 아기 상어의 위치, cnt
                    board[i][j] = 0;
                }
            }
        }

        while (info[0] != -1) { // 물고기를 스스로 잡아먹을 수 있을 때까지 반복
            info = bfs(info, 0);
        }

        System.out.println(answer);
    }

    static int[] bfs(int[] info, int time) {
        int x = info[0];
        int y = info[1];
        int cnt = info[2];

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 1. 이동 횟수 오름차순
                int c1 = o1[2] - o2[2];
                if (c1 == 0) {
                    // 2. 위쪽에 있는 좌표 오름차순
                    int c2 = o1[0] - o2[0];
                    if (c2 == 0) {
                        // 3. 왼쪽에 있는 좌표 오름차순
                        return o1[1] - o2[1];
                    }
                    return c2;
                }
                return c1;
            }
        });
        queue.add(new int[]{x, y, time});

        boolean[][] visited = new boolean[n][n];
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] q = queue.poll();
            x = q[0];
            y = q[1];
            time = q[2];

            // 자신보다 크기가 작은 물고기 -> 먹을 수 있다.
            if (board[x][y] != 0 && board[x][y] < size) {
                board[x][y] = 0;
                cnt++;
                answer += time;

                // 아기상어의 크기와 같은 수의 물고기를 먹으면 크기가 커진다.
                if (cnt == size) {
                    size++;
                    return new int[]{x, y, 0};
                }

                return new int[]{x, y, cnt};
            }

            for (int i=0; i<4; i++) {
                int mx = x + dx[i];
                int my = y + dy[i];

                // 범위를 벗어나면 이동 불가능
                if (mx < 0 || mx >= n || my < 0 || my >= n) {
                    continue;
                }

                // 이미 방문했거나, 자신보다 크기가 큰 물고기가 있는 칸이면 이동 불가능
                if (visited[mx][my] || board[mx][my] > size) {
                    continue;
                }

                // 지나가기
                queue.add(new int[]{mx, my, time+1});
                visited[mx][my] = true;
            }
        }

        return new int[]{-1};
    }
}
