import java.io.*;
import java.util.*;

// 백준 1922: 네트워크 연결
public class boj1922 {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 컴퓨터의 수
        int m = Integer.parseInt(br.readLine()); // 연결할 선의 수

        ArrayList<int[]> edges = new ArrayList<>(); // 간선

        for (int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges.add(new int[]{a, b, c});
        }

        Collections.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2]; // 비용 순으로 정렬
            }
        });

        // 부모 테이블
        parent = new int[n+1];
        for (int i=1; i<n+1; i++) {
            parent[i] = i;
        }

        int answer = 0;
        for (int[] edge: edges) {
            int a = edge[0];
            int b = edge[1];
            int c = edge[2];

            // 사이클이 발생하지 않을 경우 합치기
            if (find(a) != find(b)) {
                union(a, b);
                answer += c;
            }
        }

        System.out.println(answer);
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
