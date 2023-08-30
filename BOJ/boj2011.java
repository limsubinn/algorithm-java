import java.io.*;

// 백준 2011: 암호코드
public class boj2011 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] password = br.readLine().split("");

        // 잘못된 암호
        if (password[0].equals("0")) {
            System.out.println(0);
            System.exit(0);
        }

        // 암호의 길이
        int n = password.length;

        // 정답을 저장할 배열
        int[] result = new int[n+1];
        // 초기값
        result[0] = 1;
        result[1] = 1;

        for (int i=1; i<n; i++) {
            int pw = Integer.parseInt(password[i]);

            // 해당 자리의 숫자가 0이 아니면 전값 더하기
            if (pw > 0) {
                result[i+1] += result[i];
            }

            // 해당 자리 수 + 앞자리 수
            StringBuilder sb = new StringBuilder();
            sb.append(password[i-1]);
            sb.append(password[i]);
            int p = Integer.parseInt(sb.toString());

            // 위에서 더한 수가 알파벳 범위에 있고 해당 자리의 숫자와 다른 숫자일 경우 전전값 더하기
            if (p >= 1 && p <= 26 && pw != p) {
                result[i+1] += result[i-1];
            }

            result[i+1] %= 1000000;
        }

        System.out.println(result[n]);
    }
}