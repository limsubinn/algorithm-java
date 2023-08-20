import java.util.*;

// 백준 14226: 이모티콘
public class boj14226 {

    static int s;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        s = sc.nextInt();
        System.out.println(bfs(1, 0, 0));
    }

    static int bfs(int cnt, int time, int save) { // 현재 이모티콘의 개수, 걸린 시간, 클립보드에 있는 이모티콘의 개수
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{cnt, time, save});

        boolean[][] visited = new boolean[1001][1001];
        visited[cnt][save] = true;

        while (!queue.isEmpty()) {
            int[] q = queue.poll();

            cnt = q[0];
            time = q[1];
            if (cnt == s) { // s개의 이모티콘을 만들 수 있으면 종료
                return time;
            }
            save = q[2];

            // 화면에 있는 이모티콘을 복사해서 클립보드에 저장
            if (cnt > 0 && !visited[cnt][cnt]) {
                queue.add(new int[]{cnt, time + 1, cnt});
                visited[cnt][cnt] = true;
            }

            // 클립보드에 있는 이모티콘을 붙여넣기
            if (save > 0 && cnt+save <= 1000 && !visited[cnt+save][save]) {
                queue.add(new int[]{cnt + save, time + 1, save});
                visited[cnt+save][save] = true;
            }

            // 화면에 있는 이모티콘 하나를 삭제
            if (cnt-1 > 0 && save <= 1000 && !visited[cnt-1][save]) {
                queue.add(new int[]{cnt - 1, time + 1, save});
                visited[cnt-1][save] = true;
            }
        }

        return time;
    }
}
