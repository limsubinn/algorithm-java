import java.io.*;
import java.util.*;

// 백준 20437: 문자열 게임 2
public class boj20437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t=0; t<T; t++) {
            char[] string = br.readLine().toCharArray();
            int k = Integer.parseInt(br.readLine());

            // 각 문자별 방문 인덱스 구하기
            ArrayList<Integer>[] visited = new ArrayList[26];
            for (int i=0; i<26; i++) {
                visited[i] = new ArrayList();
            }
            for (int i=0; i<string.length; i++) {
                visited[alphaToNum(string[i])].add(i);
            }

            boolean flag = false; // 만족하는 연속 문자열이 있는지 체크
            int max = 0; // 가장 짧은 연속 문자열의 길이
            int min = Integer.MAX_VALUE; // 가장 긴 연속 문자열의 길이

            for (int i=0; i<26; i++) {
                // k개를 포함하는 문자열이 없으면 통과
                if (visited[i].size() < k) {
                    continue;
                }

                // 연속 문자열의 길이 구하기
                for (int j=0; j<visited[i].size()-k+1; j++) {
                    int size = visited[i].get(j+k-1) - visited[i].get(j) + 1;
                    max = Math.max(max, size);
                    min = Math.min(min, size);
                    flag = true;
                }
            }

            if (flag) {
                sb.append(min + " " + max + "\n");
            } else {
                sb.append("-1\n");
            }
        }

        System.out.print(sb);
    }

    static int alphaToNum(char alpha) {
        return alpha - 'a';
    }
}
