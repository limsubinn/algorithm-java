import java.io.*;
import java.util.*;

// 백준 17135: 캐슬 디펜스
public class boj17135 {

    static int n, m;
    static int d;
    static int[][] map; // 격자판의 상태를 저장할 배열
    static int[][] temp; // 격자판을 복사해서 쓸 배열
    static ArrayList<int[]> remove; // 게임에서 제외할 적의 위치를 저장할 리스트

    // 이동 배열: 오른쪽 -> 위쪽 -> 왼쪽 순서로 탐색
    static int[] dx = {0, -1, 0};
    static int[] dy = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        // 격자판 입력받기
        map = new int[n][m];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        // 궁수 3명 뽑기
        for (int i=0; i<m; i++) {
            for (int j=i+1; j<m; j++) {
                for (int k=j+1; k<m; k++) {
                    if (i == j || j == k || k == i) {
                        continue;
                    }

                    temp = cloneArray();
                    int res = 0;

                    // 격자판에서 모든 적이 지워질 때까지 반복
                    while (!isRemoved()) {
                        remove = new ArrayList<>();

                        // 공격할 적 찾기
                        bfs(n - 1, i, 1);
                        bfs(n - 1, j, 1);
                        bfs(n - 1, k, 1);

                        // 적 제거하고 개수 세기
                        for (int[] r : remove) {
                            int x = r[0];
                            int y = r[1];

                            if (temp[x][y] == 1) {
                                temp[x][y] = 0;
                                res++;
                            }
                        }

                        // 적이 모두 지워졌으면 종료
                        if (isRemoved()) {
                            break;
                        }

                        // 아래 칸으로 이동
                        move();
                    }

                    // 최댓값 갱신
                    answer = Math.max(answer, res);
                }
            }
        }

        System.out.println(answer);
    }

    static boolean isRemoved() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                // 적이 하나라도 남아있으면 false
                if (temp[i][j] == 1) {
                    return false;
                }
            }
        }

        // 적이 모두 지워졌으면 true
        return true;
    }

    static void move() {
        // 행 아래로 옮기기
        for (int i=n-1; i>=1; i--) {
            for (int j=0; j<m; j++) {
                temp[i][j] = temp[i-1][j];
            }
        }

        // 첫 행 0으로 채우기
        for (int i=0; i<m; i++) {
            temp[0][i] = 0;
        }
    }

    static int[][] cloneArray() { // 배열 복사
        int[][] temp = new int[n][m];

        for (int i=0; i<n; i++) {
            System.arraycopy(map[i], 0, temp[i], 0, m);
        }

        return temp;
    }

    static void bfs(int x, int y, int cnt) {
        // 해당 칸에 적이 있으면 종료
        if (temp[x][y] == 1) {
            remove.add(new int[]{x, y});
            return;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, cnt}); // x, y: 탐색할 칸의 인덱스. cnt: 탐색한 칸의 개수

        boolean[][] visited = new boolean[n][m];

        while (!queue.isEmpty()) {
            int[] q = queue.poll();
            x = q[0];
            y = q[1];
            cnt = q[2];

            // 공격 거리 제한을 넘어가면 종료
            if (cnt >= d) {
                return;
            }

            for (int i=0; i<3; i++) {
                int mx = x + dx[i];
                int my = y + dy[i];

                if (mx < 0 || my < 0 || my >= m) {
                    continue;
                }

                if (visited[mx][my]) {
                    continue;
                }

                // 적을 만나면 리스트에 추가하고 종료
                if (temp[mx][my] == 1) {
                    remove.add(new int[]{mx, my});
                    return;
                }

                queue.add(new int[]{mx, my, cnt+1});
                visited[mx][my] = true;
            }
        }
    }
}
