import java.io.*;
import java.util.*;

// 백준 12891: DNA 비밀번호
public class boj12891 {

    static int answer = 0;
    static int[] acgt = new int[4];
    static char[] ACGT = {'A', 'C', 'G', 'T'};
    static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int s = Integer.parseInt(st.nextToken()); // dna 문자열 길이
        int p = Integer.parseInt(st.nextToken()); // 부분문자열 길이

        char[] dna = br.readLine().toCharArray(); // dna 문자열

        st = new StringTokenizer(br.readLine(), " ");
        // 부분문자열에 포함되어야 할 'A', 'C', 'G', 'T'의 최소 개수
        for (int i=0; i<4; i++) {
            acgt[i] = Integer.parseInt(st.nextToken());
        }

        // 맵 초기화 (각 문자열이 나타난 횟수를 저장)
        for (int i=0; i<4; i++) {
            map.put(ACGT[i], 0);
        }

        int start = 0; // 부분문자열 시작 인덱스
        for (int i=start; i<start+p; i++) {
            map.put(dna[i], map.get(dna[i]) + 1); // 부분문자열의 각 알파벳이 나온 횟수 저장
        }
        getCount(); // 비밀번호를 만들 수 있는지 확인
        start++; // 시작 인덱스 증가

        // 부분문자열의 끝 인덱스가 문자열의 범위를 넘지 않을 때까지 반복
        while (start+p <= s) {
            map.put(dna[start-1], map.get(dna[start-1]) - 1); // 바로 앞 문자 빼기
            map.put(dna[start+p-1], map.get(dna[start+p-1]) + 1); // 바로 뒤 문자 더하기
            getCount();
            start++;
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

    public static void getCount() {
        int cnt = 0;

        for (int i=0; i<4; i++) {
            // 부분문자열에 포함되어야 할 'A', 'C', 'G', 'T'의 최소 개수를 넘지 않을 경우 break
            if (map.get(ACGT[i]) < acgt[i]) {
                break;
            }
            cnt++;
        }

        // 네 문자가 모두 최소 개수를 넘을 경우 정답 추가
        if (cnt == 4) {
            answer++;
        }
    }
}