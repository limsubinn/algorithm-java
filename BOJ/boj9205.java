import java.io.*;
import java.util.*;

// 백준 9205: 맥주 마시면서 걸어가기

class Position {
    int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class boj9205 {

    static int n;
    static Position[] positions;
    static ArrayList<Integer>[] graph;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t=0; t<T; t++) {
            n = Integer.parseInt(br.readLine());
            positions = new Position[n+2];

            graph = new ArrayList[n+2];
            for (int i=0; i<n+2; i++) {
                graph[i] = new ArrayList<>();
            }

            // x, y 좌표 저장
            for (int i=0; i<n+2; i++) {
                st = new StringTokenizer(br.readLine());
                positions[i] = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            // 연결된 좌표 표시
            for (int i=0; i<n+1; i++) {
                for (int j=i; j<n+2; j++) {
                    if (isLinked(i, j)) {
                        graph[i].add(j);
                        graph[j].add(i);
                    }
                }
            }

            bfs(0);
        }

        System.out.print(sb);
    }

    static boolean isLinked(int a, int b) {
        int x = Math.abs(positions[a].x - positions[b].x);
        int y = Math.abs(positions[a].y - positions[b].y);

        if (x + y > 1000) { // 맥주 한 박스에는 맥주 20개, 50미터에 한 병씩 마셔야 함
            return false;
        }

        return true;
    }

    static void bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);

        boolean[] visited = new boolean[n+2];
        visited[node] = true;

        while (!queue.isEmpty()) {
            node = queue.poll();

            // 연결된 노드 탐색
            for (int g: graph[node]) {
                if (visited[g]) {
                    continue;
                }

                // 페스티벌 도착
                if (g == n+1) {
                    sb.append("happy\n");
                    return;
                }

                queue.add(g);
                visited[g] = true;
            }
        }

        // 페스티벌에 도착하지 못한 경우
        sb.append("sad\n");
    }
}
