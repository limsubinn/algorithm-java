import java.io.*;

// 백준 17615: 볼 모으기
public class boj17615 {

    static int n;
    static char[] ball;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        ball = br.readLine().toCharArray();

        right('R'); // 오른쪽으로 빨간 공 모으기
        right('B'); // 오른쪽으로 파란 공 모으기
        left('R'); // 왼쪽으로 빨간 공 모으기
        left('B'); // 왼쪽으로 파란 공 모으기

        System.out.println(answer);
    }

    static void right(char target) {
        int cnt = 0;
        int result = 0;

        // 오른쪽으로 ~
        for (int i=0; i<n; i++) {
            // 옮길 공일 경우 개수 증가
            if (ball[i] == target) {
                cnt++;
                continue;
            }

            // 다른 공을 만났을 때 결과값 증가
            if (ball[i] != target && cnt > 0) {
                result += cnt;
                cnt = 0;
            }
        }

        // 최솟값 갱신
        answer = Math.min(answer, result);
    }

    static void left(char target) {
        int cnt = 0;
        int result = 0;

        // 왼쪽으로 ~
        for (int i=n-1; i>=0; i--) {
            // 옮길 공일 경우 개수 증가
            if (ball[i] == target) {
                cnt++;
                continue;
            }

            // 다른 공을 만났을 때 결과값 증가
            if (ball[i] != target && cnt > 0) {
                result += cnt;
                cnt = 0;
            }
        }

        // 최솟값 갱신
        answer = Math.min(answer, result);
    }
}
