import java.io.*;

// swea1289. 원재의 메모리 복구하기
public class swea1289 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int t=1; t<T+1; t++) {
            // 메모리 원래 값
            String[] result = br.readLine().split("");
            int n = result.length;

            // 초기화된 메모리
            int[] values = new int[n];
            for (int i=0; i<n; i++) {
                values[i] = 0;
            }

            int answer = 0;
            for (int i=0; i<n; i++) {
                int r = Integer.parseInt(result[i]);
                // 원래의 값과 다르면 교체
                if (r != values[i]) {
                    for (int j=i; j<n; j++) {
                        values[j] = r;
                    }
                    answer++;
                }
            }
            bw.write("#" + t + " " + answer + "\n");
        }

        bw.flush();
        bw.close();
    }
}