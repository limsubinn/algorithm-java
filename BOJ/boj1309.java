import java.io.*;

// 백준 1309: 동물원
public class boj1309 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[][] result = new long[n][3];
        for (int i=0; i<3; i++) {
            result[0][i] = 1;
        }

        for (int i=1; i<n; i++) {
            // 사자를 한 마리도 배치하지 않는 경우
            result[i][0] = (result[i-1][0] + result[i-1][1] + result[i-1][2]) % 9901;
            // 1열에 사자를 배치하는 경우
            result[i][1] = (result[i-1][0] + result[i-1][2]) % 9901;
            // 2열에 사자를 배치하는 경우
            result[i][2] = (result[i-1][0] + result[i-1][1]) % 9901;
        }

        System.out.println((result[n-1][0] + result[n-1][1] + result[n-1][2]) % 9901);
    }
}
