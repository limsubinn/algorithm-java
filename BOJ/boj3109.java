import java.io.*;
import java.util.*;

// 백준 3109: 빵집
public class boj3109 {

    static int r, c;
    static char[][] array;
    static int[] dx = {-1, 0, 1}; // x 이동 좌표

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        array = new char[r][c];
        for (int i=0; i<r; i++) {
            array[i] = br.readLine().toCharArray();
        }

        int answer = 0;
        for (int i=0; i<r; i++) {
            if (findPipeline(i, 0)) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    static boolean findPipeline(int x, int y) {
        // 방문 표시
        array[x][y] = '-';

        // 마지막 열에 도달하면 파이프라인을 만들 수 있다.
        if (y == c-1) {
            return true;
        }

        int mx, my;

        for (int i=0; i<3; i++) {
            // 이동할 좌표
            mx = x + dx[i];
            my = y + 1;

            // 범위를 벗어나면 통과
            if (mx < 0 || mx >= r || my >= c) {
                continue;
            }

            // 파이프라인을 놓을 수 있는 경우
            if (array[mx][my] == '.' && findPipeline(mx, my)) {
                return true;
            }
        }

        return false;
    }
}
