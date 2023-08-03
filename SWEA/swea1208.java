import java.io.*;
import java.util.*;

// swea1208. Flatten
public class swea1208 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] heights = new int[100];

        for (int t=1; t<=10; t++) {
            // 덤프
            int dump = Integer.parseInt(br.readLine());

            // 상자 높이
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i=0; i<100; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
            }

            // 정렬
            Arrays.sort(heights);

            for (int i=0; i<dump; i++) {
                heights[99]--; // 최댓값 하나 빼기
                heights[0]++; // 최솟값 하나 더하기

                Arrays.sort(heights); // 정렬
            }

            // 최댓값과 최솟값의 차이 출력
            bw.write("#" + t + " " + (heights[99] - heights[0]) + "\n");
        }

        bw.flush();
        bw.close();
    }

}