import java.io.*;
import java.util.*;

/**
 * swea 1251. 하나로
 */
public class swea1251 {

    static int n;

    static int[] x, y;
    static int[] parent;
    static PriorityQueue<Node> nodes;

    static class Node implements Comparable<Node> {

        // 연결 정보
        int a;
        int b;
        // 비용
        double c;

        public Node(int a, int b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            // 거리 작은 순으로 정렬
            return Double.compare(this.c, o.c);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        Node node;
        double e;
        double answer;

        for (int t=1; t<=T; t++) {
            // 섬의 개수
            n = Integer.parseInt(br.readLine());

            // 섬 정보
            x = new int[n];
            y = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i=0; i<n; i++) {
                x[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i=0; i<n; i++) {
                y[i] = Integer.parseInt(st.nextToken());
            }

            // 환경 부담 세율
            e = Double.parseDouble(br.readLine());

            // 부모 노드 초기화
            parent = new int[n];
            for (int i=0; i<n; i++) {
                parent[i] = i;
            }

            // 연결 정보
            nodes = new PriorityQueue<>();
            for (int i=0; i<n-1; i++) {
                for (int j=i+1; j<n; j++) {
                    nodes.add(new Node(i, j, distance(i, j)));
                }
            }

            // 섬 연결하기
            answer = 0;
            while (!nodes.isEmpty()) {
                node = nodes.poll();

                if (find(node.a) != find(node.b)) {
                    union(node.a, node.b);
                    answer += e * node.c;
                }

            }

            sb.append("#").append(t).append(" ").append(Math.round(answer)).append("\n");
        }

        System.out.print(sb);
    }

    static double distance(int a, int b) {
        return Math.pow(x[a] - x[b], 2) + Math.pow(y[a] - y[b], 2);
    }

    static int find(int a) {
        if (parent[a] != a) {
            parent[a] = find(parent[a]);
        }
        return parent[a];
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
