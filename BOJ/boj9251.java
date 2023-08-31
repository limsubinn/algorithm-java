import java.io.*;

// 백준 9251: LCS
public class boj9251 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();

        int aLen = a.length;
        int bLen = b.length;

        int[][] result = new int[aLen+1][bLen+1];

        for (int i=1; i<=aLen; i++) {
            for (int j=1; j<=bLen; j++) {
                if (a[i-1] == b[j-1]) { // 같은 문자인 경우 갱신
                    result[i][j] = result[i-1][j-1] + 1;
                } else { // 다른 문자인 경우 최댓값 넣어주기
                    result[i][j] = Math.max(result[i-1][j], result[i][j-1]);
                }
            }
        }

        System.out.println(result[aLen][bLen]);
    }
}
