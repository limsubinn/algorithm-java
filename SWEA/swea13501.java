import java.io.*;
import java.util.*;

// swea 13501. 수열 편집
public class swea13501 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken()); // 수열의 길이
            int m = Integer.parseInt(st.nextToken()); // 추가 횟수
            int l = Integer.parseInt(st.nextToken()); // 출력할 인덱스 번호

            // 수열
            ArrayList<String> list = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i=0; i<n; i++) {
                list.add(st.nextToken());
            }

            for (int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());
                String s = st.nextToken(); // 명령어

                // 추가
                if (s.equals("I")) {
                    int x = Integer.parseInt(st.nextToken());
                    String y = st.nextToken();
                    list.add(x, y);
                    continue;
                }

                // 지우기
                if (s.equals("D")) {
                    int x = Integer.parseInt(st.nextToken());
                    list.remove(x);
                    continue;
                }

                // 바꾸기
                int x = Integer.parseInt(st.nextToken());
                String y = st.nextToken();
                list.add(x++, y);
                list.remove(x);
            }

            sb.append("#").append(t).append(" ");
            // 인덱스 l이 존재하지 않는 경우 -1 출력
            if (list.size() < l) {
                sb.append(-1);
            }
            // l자리 출력
            else {
                sb.append(list.get(l));
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
