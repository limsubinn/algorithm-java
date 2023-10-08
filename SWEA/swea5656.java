import java.io.*;
import java.util.*;

// swea 5656. 벽돌 깨기
public class swea5656 {

    static int n, w, h;
    static int answer;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            int[][] board = new int[h][w];

            for (int i=0; i<h; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j=0; j<w; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = Integer.MAX_VALUE;
            find(0, board);
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static void find(int cnt, int[][] array) {
        if (cnt == n) {
            int sum = 0;
            for (int i=0; i<h; i++) {
                for (int j=0; j<w; j++) {
                    if (array[i][j] != 0) {
                        sum++;
                    }
                }
            }
            answer = Math.min(answer, sum);
            return;
        }

//        int[][] copyArray = copy(array);

        for (int i=0; i<w; i++) {
            int[][] copyArray = copy(array);

            crack(i, copyArray);
            down(copyArray);
            find(cnt+1, copyArray);
        }
    }

    static int[][] copy(int[][] before) {
        int[][] after = new int[h][w];

        for (int i=0; i<h; i++) {
            for (int j=0; j<w; j++) {
                after[i][j] = before[i][j];
            }
        }

        return after;
    }

    static void crack(int y, int[][] array) {
        // 각 열의 제일 위에 있는 벽돌 위치 x
        int x = -1;
        for (int i=0; i<h; i++) {
            if (array[i][y] != 0) {
                x = i;
                break;
            }
        }

        // 해당 열에 벽돌이 없으면 종료
        if (x == -1) {
            return;
        }

        int k = array[x][y];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y, k});
        array[x][y] = 0;

        while (!queue.isEmpty()) {
            int[] q = queue.poll();
            x = q[0];
            y = q[1];
            k = q[2];

            // 상하좌우
            for (int i=0; i<4; i++) {
                // (벽돌에 적힌 숫자 - 1)만큼
                for (int j=1; j<k; j++) {
                    int mx = x + dx[i] * j;
                    int my = y + dy[i] * j;

                    if (mx < 0 || mx >= h || my < 0 || my >= w) {
                        continue;
                    }

                    if (array[mx][my] == 0) {
                        continue;
                    }

                    queue.add(new int[] {mx, my, array[mx][my]});
                    array[mx][my] = 0;
                }
            }
        }

    }

    static void down(int[][] array) {
        for (int j=0; j<w; j++) {
            int[] temp = new int[h];
            int k = h-1;

            // 벽돌 모으기
            for (int i=h-1; i>=0; i--) {
                if (array[i][j] != 0) {
                    temp[k--] = array[i][j];
                }
            }

            // 벽돌 내리기
            for (int i=0; i<h; i++) {
                array[i][j] = temp[i];
            }
        }
    }
}