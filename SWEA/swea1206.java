import java.io.*;
import java.util.*;

/**
 * swea 1206. View
 */
public class swea1206 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n;
        int[] buildings;
        int highest;

        for (int t=1; t<=10; t++) {
            n = Integer.parseInt(br.readLine());
            buildings = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i=0; i<n; i++) {
                buildings[i] = Integer.parseInt(st.nextToken());
            }

            int answer = 0;
            for (int i=2; i<n-2; i++) {
                // 현재 건물을 제외한 양쪽 거리 2 이내의 최대 높이 구하기
                highest = buildings[i-2];
                for (int j=i-1; j<=i+2; j++) {
                    if (j == i) {
                        continue;
                    }
                    highest = Math.max(highest, buildings[j]);
                }
                // 현재 건물의 조망권이 확보된 세대 더하기
                answer += Math.max(buildings[i] - highest, 0);
            }

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }
}
