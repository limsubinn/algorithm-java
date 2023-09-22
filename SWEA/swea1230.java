import java.io.*;
import java.util.*;

// swea 1230. 암호문3
public class swea1230 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t=1; t<=10; t++) {
            int n = Integer.parseInt(br.readLine());
            ArrayList<String> pw = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i=0; i<n; i++) {
                pw.add(st.nextToken());
            }

            int m = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                // 명령어
                String s = st.nextToken();

                // 삽입
                if (s.equals("I")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());

                    for (int i=0; i<y; i++) {
                        pw.add(x++, st.nextToken());
                    }

                    continue;
                }

                // 삭제
                if (s.equals("D")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());

                    for (int i=0; i<y; i++) {
                        pw.remove(x++);
                    }

                    continue;
                }

                // 추가
                int y = Integer.parseInt(st.nextToken());
                for (int i=0; i<y; i++) {
                    pw.add(st.nextToken());
                }
            }

            sb.append("#").append(t);
            for (int i=0; i<10; i++) { // 앞 10개의 암호문 출력
                sb.append(" ").append(pw.get(i));
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
