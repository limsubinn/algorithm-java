import java.io.*;
import java.util.*;

// 백준 6987: 월드컵
public class boj6987 {

    // 팀 매칭 경우의 수
    static int[] A = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
    static int[] B = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};

    // 나라별 승, 무승부, 패
    static int[] win = new int[6];
    static int[] draw = new int[6];
    static int[] lose = new int[6];

    // 결과값
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t=0; t<4; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            // 6개국의 결과 입력받기
            for (int i=0; i<6; i++) {
                win[i] = Integer.parseInt(st.nextToken());
                draw[i] = Integer.parseInt(st.nextToken());
                lose[i] = Integer.parseInt(st.nextToken());
            }

            // 가능한 결과인지 확인
            result = 0;
            isPossible(0);
            sb.append(result + " ");
        }

        System.out.println(sb);
    }

    static void isPossible(int cnt) { // cnt: 경기를 진행한 수
        // 가능한 결과이면 종료
        if (result == 1) {
            return;
        }

        // 경기를 총 15번 진행한 경우
        if (cnt == 15) {
            // 모든 승, 무승부, 패가 맞아 떨어진 경우 가능한 결과 (1 리턴)
            for (int i=0; i<6; i++) {
                if (win[i] != 0 || draw[i] != 0 || lose[i] != 0) {
                    result = 0;
                    return;
                }
            }

            result = 1;
            return;
        }

        // 서로 경기를 치를 팀의 번호
        int a = A[cnt];
        int b = B[cnt];

        // A - 승, B - 패
        if (win[a] > 0 && lose[b] > 0) {
            win[a]--;
            lose[b]--;

            isPossible(cnt+1);

            win[a]++;
            lose[b]++;
        }

        // A - 무승부, B - 무승부
        if (draw[a] > 0 && draw[b] > 0) {
            draw[a]--;
            draw[b]--;

            isPossible(cnt+1);

            draw[a]++;
            draw[b]++;
        }

        // A - 패, B - 승
        if (lose[a] > 0 && win[b] > 0) {
            lose[a]--;
            win[b]--;

            isPossible(cnt + 1);

            lose[a]++;
            win[b]++;
        }
    }
}
