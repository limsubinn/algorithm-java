import java.io.*;
import java.util.*;

// 백준 1253: 좋다
public class boj1253 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);

        int answer = 0;
        for (int k=0; k<n; k++) {
            int start = 0;
            int end = n-1;
            int num = numbers[k];

            while (start < end) {
                int sum = numbers[start] + numbers[end];

                if (sum == num) {
                    // 자기 자신을 더하면 안 됨.
                    if (start == k) {
                        start++;
                        continue;
                    }

                    if (end == k) {
                        end--;
                        continue;
                    }

                    // 서로 다른 수 두 개의 합으로 나타낼 수 있는 경우
                    answer++;
                    break;
                }

                if (sum > num) {
                    end--;
                    continue;
                }

                // sum < num
                start++;
            }
        }

        System.out.println(answer);
    }
}
