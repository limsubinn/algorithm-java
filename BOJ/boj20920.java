import java.io.*;
import java.util.*;

/**
 * 백준 20920. 영단어 암기는 괴로워
 */
public class boj20920 {

    static class Word implements Comparable<Word> {

        String str; // 단어
        int frequency; // 빈도수
        int size; // 길이

        Word(String str) {
            this.str = str;
            this.frequency = 1;
            this.size = str.length();
        }

        void updateFrequency() {
            this.frequency++;
        }

        @Override
        public int compareTo(Word o) {
            // 빈도수 기준 내림차순
            int c1 = o.frequency - this.frequency;

            if (c1 == 0) {
                // 단어 길이 기준 내림차순
                int c2 = o.size - this.size;

                if (c2 == 0) {
                    // 알파벳 사전 순
                    return this.str.compareTo(o.str);
                }

                return c2;
            }

            return c1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, Word> map = new HashMap<>();
        ArrayList<Word> words = new ArrayList<>();
        Word word;
        String str;

        for (int i=0; i<n; i++) {
            str = br.readLine();

            // m보다 짧은 길이의 단어
            if (str.length() < m) {
                continue;
            }

            // 같은 단어가 있는 경우 빈도수 증가
            if (map.containsKey(str)) {
                map.get(str).updateFrequency();
                continue;
            }

            // 새로운 단어 추가
            word = new Word(str);
            map.put(str, word);
            words.add(word);
        }

        // 정렬
        Collections.sort(words);

        // 출력
        for (Word w: words) {
            sb.append(w.str).append("\n");
        }
        System.out.print(sb);
    }
}
