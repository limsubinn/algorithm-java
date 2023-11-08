import java.io.*;
import java.util.*;

// 백준 18808: 스티커 붙이기
public class boj18808 {

    static int n, m;

    static int[][] graph;
    static List<int[][]> stickers = new ArrayList<>();

    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 노트북
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];

        // 스티커
        int k = Integer.parseInt(st.nextToken());
        int r, c;
        for (int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());

            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[r][c];

            for (int x=0; x<r; x++) {
                st = new StringTokenizer(br.readLine());

                for (int y=0; y<c; y++) {
                    sticker[x][y] = Integer.parseInt(st.nextToken());
                }
            }

            stickers.add(sticker);
        }

        // 스티커 붙이기 
        for (int[][] sticker: stickers) {
            solution(sticker);
        }

        // 정답 출력
        System.out.println(answer);

    }

    static void solution(int[][] sticker) {
        int r = 0;
        while (true) {
            // 스티커를 붙인 경우
            if (findPosition(sticker)) {
                return;
            }

            if (r++ > 3) {
                return;
            }

            // 스티커를 붙이지 못한 경우
            int[][] temp = cloneArray(sticker);
            sticker = new int[temp[0].length][temp.length];
            // 90도 회전
            for (int x=0; x<temp[0].length; x++) {
                for (int y=0; y<temp.length; y++) {
                    sticker[x][y] = temp[temp.length-1-y][x];
                }
            }
        }
    }
    static boolean findPosition(int[][] sticker) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 스티커를 붙일 수 있으면
                if (canAttach(sticker, i, j)) {
                    // 스티커를 붙이고 종료
                    attachSticker(sticker, i, j);
                    return true;
                }
            }
        }
        return false;
    }

    static boolean canAttach(int[][] sticker, int sx, int sy) {
        int r = sticker.length;
        int c = sticker[0].length;

        // 스티커 사이즈만큼 공간이 있는지 확인
        if (sx+r > n || sy+c > m) {
            return false;
        }

        // 해당 공간에 스티커를 붙일 수 있는지 확인
        for (int i=sx; i<sx+r; i++) {
            for (int j=sy; j<sy+c; j++) {
                // 스티커를 붙일 자리가 비어있지 않으면 붙일 수 없다.
                if (sticker[i-sx][j-sy] == 1 && graph[i][j] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    static void attachSticker(int[][] sticker, int sx, int sy) {
        int r = sticker.length;
        int c = sticker[0].length;

        for (int i=sx; i<sx+r; i++) {
            for (int j=sy; j<sy+c; j++) {
                if (sticker[i-sx][j-sy] == 1) {
                    graph[i][j] = 1;
                    answer++;
                }
            }
        }
    }

    static int[][] cloneArray(int[][] array) {
        int r = array.length;
        int c = array[0].length;

        int[][] temp = new int[r][c];

        for (int i=0; i<r; i++) {
            System.arraycopy(array[i], 0, temp[i], 0, c);
        }

        return temp;
    }
}