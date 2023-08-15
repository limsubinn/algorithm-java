import java.io.*;
import java.util.*;

// 백준 1629: 곱셈
public class boj1629 {

    static int a, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        System.out.println(pow(b));
    }

    static long pow(int b) {
        // b가 0인 경우 거듭제곱은 1
        if (b == 0) {
            return 1;
        }

        // 반 나눠서 곱하기
        long answer = pow(b/2);
        answer = answer * answer % c;

        // b가 홀수이면 a 한번 더 곱해서 리턴
        if (b % 2 != 0) {
            return answer * a % c;
        }

        // b가 짝수이면 그대로 리턴
        return answer;
    }
}
