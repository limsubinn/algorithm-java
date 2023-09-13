import java.io.*;
import java.util.*;

// 백준 14891: 톱니바퀴
public class boj14891 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String[][] gear = new String[4][8];
        int[] top = new int[4];
        for (int i=0; i<4; i++) {
            // 톱니바퀴
            gear[i] = br.readLine().split("");
            // 톱니바퀴의 12시 방향
            top[i] = 0;
        }

        int k = Integer.parseInt(br.readLine());
        for (int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());

            // 회전시킬 톱니바퀴의 번호
            int n = Integer.parseInt(st.nextToken()) - 1;
            // 회전할 방향
            int d = Integer.parseInt(st.nextToken());

            // 각 톱니바퀴의 회전 방향을 저장
            int[] turn = new int[4];
            turn[n] = d;

            // 회전시킬 톱니바퀴의 왼쪽 확인
            for (int j=n-1; j>=0; j--) {
                // 맞닿은 극 확인
                int left = (top[j+1]-2 >= 0)? top[j+1]-2 : top[j+1]+6;
                int right = (top[j]+2 < 8)? top[j]+2 : top[j]-6;

                // 같으면 더이상 회전하지 않는다.
                if (gear[j+1][left].equals(gear[j][right])) {
                    break;
                }

                // 반대로 회전
                turn[j] = (turn[j+1] == 1)? -1 : 1;
            }

            // 회전시킬 톱니바퀴의 오른쪽 확인
            for (int j=n+1; j<4; j++) {
                // 맞닿은 극 확인
                int right = (top[j-1]+2 < 8)? top[j-1]+2 : top[j-1]-6;
                int left = (top[j]-2 >= 0)? top[j]-2 : top[j]+6;

                // 같으면 더이상 회전하지 않는다.
                if (gear[j][left].equals(gear[j-1][right])) {
                    break;
                }

                // 반대로 회전
                turn[j] = (turn[j-1] == 1)? -1 : 1;
            }

            // 12시 방향 극 바꾸기
            for (int j=0; j<4; j++) {
                top[j] -= turn[j];
                top[j] = (top[j] < 0)? 7: top[j];
                top[j] = (top[j] >= 8)? 0: top[j];
            }
        }

        // 정답
        int answer = 0;
        for (int i=0; i<4; i++) {
            if (gear[i][top[i]].equals("1")) {
                answer += Math.pow(2, i);
            }
        }
        System.out.println(answer);

    }
}
