import java.io.*;
import java.util.*;

// 백준 1759: 암호 만들기
public class boj1759 {

    static int l, c;
    static String[] array; // 암호로 사용했을 법할 문자들
    static String[] result; // 조합 저장

    static StringBuilder sb = new StringBuilder();
    static ArrayList<String> moeum = new ArrayList<>(List.of(new String[]{"a", "e", "i", "o", "u"})); // 모음

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        array = new String[c];
        result = new String[l];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i=0; i<c; i++) {
            array[i] = st.nextToken();
        }
        Arrays.sort(array); // 사전식으로 정답을 구하기 위해 정렬

        find(0, 0);
        System.out.print(sb);
    }

    static void find(int start, int cnt) {
        if (cnt == l) { // 조합이 완성되면
            if (isPossible()) { // 가능성 있는 암호인지 확인
                for (String res: result) {
                    sb.append(res);
                }
                sb.append("\n");
            }
            return;
        }

        for (int i=start; i<c; i++) {
            result[cnt] = array[i];
            find(i+1, cnt+1);
        }
    }

    static boolean isPossible() {
        int m = 0, j = 0; // 모음의 개수, 자음의 개수

        for (String res: result) {
            if (moeum.contains(res)) {
                m++;
            } else {
                j++;
            }
        }

        // 최소 한 개의 모음 + 최소 두 개의 자음 -> 암호를 만들 수 있다.
        if (m >= 1 && j >= 2) {
            return true;
        }

        return false;
    }
}
