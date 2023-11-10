import java.io.*;
import java.util.*;

// 백준 17182: 우주 탐사선
public class boj17182 {

    static int n, k;
    static int answer = Integer.MAX_VALUE;
    static boolean[] visited;
    static int[][] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new int[n][n];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 최소 시간으로 갱신
        for (int c=0; c<n; c++) {
            for (int a=0; a<n; a++) {
                for (int b=0; b<n; b++) {
                    if (graph[a][c] + graph[c][b] < graph[a][b]) {
                        graph[a][b] = graph[a][c] + graph[c][b];
                    }
                }
            }
        }

        visited = new boolean[n];
        visited[k] = true;

        find(k, 1, 0);
        System.out.println(answer);
    }

    static void find(int start, int cnt, int result) {
        // 모든 행성을 탐색한 경우
        if (cnt >= n) {
            // 최솟값 갱신
            answer = Math.min(answer, result);
            return;
        }

        for (int i=0; i<n; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            find(i, cnt+1, result + graph[start][i]);
            visited[i] = false;
        }
    }
}
