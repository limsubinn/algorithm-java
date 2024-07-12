import java.io.*;
import java.util.*;

/**
 * swea 1219. 길 찾기
 */
public class swea1219 {

    static int n;
    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int a, b;
        for (int t=1; t<=10; t++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken(); // 테스트 케이스의 번호
            n = Integer.parseInt(st.nextToken()); // 길의 총 개수

            st = new StringTokenizer(br.readLine());
            initGraph(); // 그래프 초기화
            for (int i=0; i<n; i++) {
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
            }

            sb.append("#").append(t).append(" ").append(bfs()).append("\n");
        }

        System.out.print(sb);
    }

    static void initGraph() {
        graph = new ArrayList[100];
        for (int i=0; i<100; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    static int bfs() {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(0);

        visited = new boolean[100];
        visited[0] = true;

        int q;
        while (!queue.isEmpty()) {
            q = queue.poll();

            for (int g: graph[q]) {
                // 도착!
                if (g == 99) {
                    return 1;
                }

                // 방문한 적 없는 경우
                if (visited[g] != true) {
                    visited[g] = true;
                    queue.add(g);
                }
            }
        }

        return 0;
    }
}
