import java.io.*;
import java.util.*;

// 백준 13549: 숨바꼭질 3
public class boj13549 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 수빈이가 있는 위치
        int k = Integer.parseInt(st.nextToken()); // 동생이 있는 위치

        System.out.println(bfs(n, 0, k));
    }

    static int bfs(int x, int cnt, int k) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, cnt});

        boolean visited[] = new boolean[100001];
        visited[x] = true;

        while (!queue.isEmpty()) {
            int[] q = queue.poll();
            x = q[0]; // 현재 위치
            cnt = q[1]; // 이동한 시간

            // 동생을 찾은 경우
            if (x == k) {
                return cnt;
            }

            // 순간 이동
            if (2*x <= 100000 && !visited[2*x]) {
                visited[2*x] = true;
                queue.add(new int[]{2*x, cnt});
            }

            // X-1로 이동
            if (x-1 >= 0 && !visited[x-1]) {
                visited[x-1] = true;
                queue.add(new int[]{x-1, cnt+1});
            }

            // X+1로 이동
            if (x+1 <= 100000 && !visited[x+1]) {
                visited[x+1] = true;
                queue.add(new int[]{x+1, cnt+1});
            }
        }

        return cnt;
    }
}
