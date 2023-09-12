import java.io.*;
import java.util.*;

// 백준 3190: 뱀
public class boj3190 {

    static int n;
    static int[][] board;

    static HashMap<Integer, Integer> map = new HashMap<>();

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 보드의 크기
        int k = Integer.parseInt(br.readLine()); // 사과의 개수

        board = new int[n][n];
        for (int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            board[x][y] = 1; // 사과 표시
        }

        int l = Integer.parseInt(br.readLine()); // 방향 변환 횟수

        for (int i=0; i<l; i++) {
            st = new StringTokenizer(br.readLine());

            // x초 후에 방향을 c로 변환
            int x = Integer.parseInt(st.nextToken());
            String c = st.nextToken();

            if (c.equals("D")) { // 오른쪽
                map.put(x, 1);
            } else { // 왼쪽
                map.put(x, -1);
            }
        }

        board[0][0] = 2;
        System.out.println(find(0, 0, 0, 1));

    }

    static int find(int x, int y, int direction, int cnt) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (true) {
            // 이동
            x += dx[direction];
            y += dy[direction];

            // 벽을 만났거나 자기자신을 만난 경우
            if (x < 0 || x >= n || y < 0 || y >= n || board[x][y] == 2) {
                return cnt;
            }

            // 사과가 없는 경우
            if (board[x][y] == 0) {
                int q[] = queue.poll();
                board[q[0]][q[1]] = 0;
            }

            // 뱀의 머리를 다음칸에 위치시킨다.
            board[x][y] = 2;
            queue.add(new int[] {x, y});

            // 방향 변환
            if (map.containsKey(cnt)) {
                direction += map.get(cnt);
                direction = (direction >= 4)? 0: direction;
                direction = (direction < 0)? 3: direction;
            }

            cnt++;
        }
    }
}