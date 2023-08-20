import java.io.*;
import java.util.*;

// 백준 1260: DFS와 BFS
public class boj1260 {

    static int n, m, v;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for (int i=1; i<n+1; i++) {
            graph[i] = new ArrayList();
        }
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        // 정점 번호가 더 작은 것을 먼저 방문하도록 하기 위해 정렬
        for (int i=1; i<n+1; i++) {
            Collections.sort(graph[i]);
        }

        // dfs
        visited = new boolean[n+1];
        dfs(v);

        sb.append("\n");

        // bfs
        visited = new boolean[n+1];
        bfs();

        System.out.println(sb);
    }

    static void dfs(int node) {
        sb.append(node + " ");
        visited[node] = true;

        for (int g: graph[node]) {
            if (visited[g]) {
                continue;
            }

            dfs(g);
        }
    }

    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int q = queue.poll();
            sb.append(q + " ");

            for (int g: graph[q]) {
                if (visited[g]) {
                    continue;
                }

                queue.add(g);
                visited[g] = true;
            }
        }
    }
}
