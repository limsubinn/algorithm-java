import java.io.*;
import java.util.*;

/**
 * 백준 1205. 등수 구하기
 */
public class boj1205 {

    static int n;
    static int s;
    static Integer[] scores;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 현재 랭킹 리스트에 있는 점수의 개수
        s = Integer.parseInt(st.nextToken()); // 태수의 새로운 점수
        int p = Integer.parseInt(st.nextToken()); // 랭킹 리스트에 올라갈 수 있는 점수의 개수

        if (n <= 0) {
            System.out.println(1);
            return;
        }

        st = new StringTokenizer(br.readLine());
        scores = new Integer[n]; // 랭킹 리스트
        for (int i = 0; i < n; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }

        // 내림차순 정렬
        Arrays.sort(scores, Collections.reverseOrder());

        // 랭킹 리스트에 올라갈 수 없는 경우
        if (scores[n-1] >= s && n == p) {
            System.out.println(-1);
            return;
        }

        // 태수의 등수 찾기
        int answer = 1;
        for (int i=0; i<n; i++) {
            if (s >= scores[i]) {
                break;
            }
            answer++;
        }

        System.out.println(answer);
    }
}
