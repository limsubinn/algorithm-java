import java.io.*;
import java.util.*;

// 백준 2961: 도영이가 만든 맛있는 음식
public class boj2961 {

    static int n;
    static int answer = Integer.MAX_VALUE;
    static int[][] tastes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        tastes = new int[n][2];
        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            tastes[i][0] = Integer.parseInt(st.nextToken()); // 신맛
            tastes[i][1] = Integer.parseInt(st.nextToken()); // 쓴맛
        }

        cook(0, 0, 1, 0);

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

    public static void cook(int choose, int cnt, int s, int b) { // 고른 음식의 수, 탐색한 음식의 수, 신 맛, 쓴 맛
        if (cnt == n) {
            if (choose != 0) {
                // 모든 음식을 탐색했고, 적어도 하나의 음식을 사용했다면 최솟값 갱신
                answer = Math.min(answer, Math.abs(s - b));
            }
            return;
        }

        cook(choose, cnt+1, s, b); // 음식을 선택하지 않는 경우
        cook(choose+1, cnt+1, s * tastes[cnt][0], b + tastes[cnt][1]); // 음식을 선택하는 경우
    }
}
