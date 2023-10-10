import java.io.*;
import java.util.*;

// 백준 17472: 다리 만들기 2
public class boj17472 {

    static class Path implements Comparable<Path> {
        int start;
        int end;
        int cost;

        public Path(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Path o) {
            return this.cost - o.cost;
        }
    }

    static int n, m;
    static int[][] board;
    static boolean[][] visited;
    static List<Path> paths = new ArrayList<>();
    static int[] parent;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        visited = new boolean[n][m];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 번호 붙이기
        int num = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (board[i][j] == 1 && !visited[i][j]) {
                    makeIsland(i, j, ++num);
                }
            }
        }

        // 경로 추가
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (board[i][j] != 0) {
                    makePath(i, j);
                }
            }
        }
        Collections.sort(paths); // 비용 순 정렬

        // 부모 테이블 초기화
        parent = new int[num+1];
        for (int i=1; i<=num; i++) {
            parent[i] = i;
        }

        // 섬을 연결하는 최소 비용의 다리 찾기
        int answer = 0;
        int cnt = 0;
        for (Path path: paths) {
            if (find(path.start) != find(path.end)) {
                union(path.start, path.end);
                answer += path.cost;
                cnt++;
            }
        }

        // 모든 섬을 연결할 수 없는 경우
        if (cnt < num-1) {
            answer = -1;
        }

        System.out.println(answer);
    }

    static void makeIsland(int x, int y, int num) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        visited[x][y] = true;
        board[x][y] = num;

        while (!queue.isEmpty()) {
            int[] q = queue.poll();
            x = q[0];
            y = q[1];

            for (int i=0; i<4; i++) {
                int mx = x + dx[i];
                int my = y + dy[i];

                if (!canGo(mx, my)) {
                    continue;
                }

                if (board[mx][my] != 1 || visited[mx][my]) {
                    continue;
                }

                board[mx][my] = num;
                visited[mx][my] = true;
                queue.add(new int[]{mx, my});
            }
        }
    }

    static void makePath(int x, int y) {
        int num = board[x][y];

        for (int i=0; i<4; i++) {
            int mx = x + dx[i];
            int my = y + dy[i];
            int cnt = 1;

            while (canGo(mx, my)) {
                // 다리 만들기
                if (board[mx][my] == 0) {
                    mx += dx[i];
                    my += dy[i];
                    cnt++;
                    continue;
                }

                // 같은 섬을 만난 경우 종료
                if (board[mx][my] == num) {
                    break;
                }

                // 다른 섬을 만난 경우
                if (cnt <= 2) { // 길이가 1 이하인 다리는 불가능
                    break;
                }
                paths.add(new Path(num, board[mx][my], cnt-1)); // 경로 추가
                break;
            }
        }

    }

    static boolean canGo(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return false;
        }
        return true;
    }

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }
}
