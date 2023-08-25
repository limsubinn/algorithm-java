import java.io.*;
import java.util.*;

// swea 1767. 프로세서 연결하기
public class swea1767 {

    static int n; // 멕시노스의 크기
    static int size; // 벽에 붙어있지 않은 코어의 개수
    static int answer; // 최소가 되는 전선 길이의 합
    static int maxCore; // 최대가 되는 코어의 개수

    static int[][] board; // 멕시노스
    static ArrayList<int[]> coreList; // 벽에 붙어있지 않은 코어

    // 이동 배열
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            n = Integer.parseInt(br.readLine());

            board = new int[n][n];
            coreList = new ArrayList<>();

            for (int i=0; i<n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");

                for (int j=0; j<n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());

                    if (board[i][j] == 1) {
                        if (i == 0 || i == n-1 || j == 0 || j == n-1) {
                            continue;
                        }
                        // 코어 저장
                        coreList.add(new int[] {i, j});
                    }
                }
            }

            answer = Integer.MAX_VALUE;
            maxCore = 0;

            size = coreList.size();
            find(0, 0,0);

            sb.append("#" + t + " " + answer + "\n");
        }

        System.out.print(sb);
    }

    static void find(int cnt, int core, int result) {
        if (cnt == size) { // 선택 완료
            // 최대 코어 개수일 때 최댓값 갱신
            if (core > maxCore) {
                answer = result;
                maxCore = core;
                return;
            }

            // 코어 수가 같으면 최댓값 갱신
            if (core == maxCore) {
                answer = Math.min(answer, result);
            }

            return;
        }

        // 현재 선택한 코어의 좌표
        int x = coreList.get(cnt)[0];
        int y = coreList.get(cnt)[1];

        for (int i=0; i<4; i++) {
            int count = 1; // 이동 칸 수

            while (true) {
                // 이동
                int mx = x + dx[i] * count;
                int my = y + dy[i] * count;

                // 범위를 벗어나면 이동 멈추기
                if (mx < 0 || mx >= n || my < 0 || my >= n) {
                    break;
                }

                // 전선 or 코어를 만나면 전선을 연결할 수 없다.
                if (board[mx][my] == 1) {
                    count = 1;
                    break;
                }

                count++;
            }

            count--;

            if (count == 0) { // 전선을 연결할 수 없는 경우
                // 다음 코어 탐색
                find(cnt+1, core, result);
            } else { // 전선을 연결할 수 있는 경우
                // 전선 표시
                for (int j = 1; j <= count; j++) {
                    int mx = x + dx[i] * j;
                    int my = y + dy[i] * j;
                    board[mx][my] = 1;
                }

                // 다음 코어 탐색
                find(cnt+1, core+1, result+count);

                // 전선 없애기
                for (int j = 1; j <= count; j++) {
                    int mx = x + dx[i] * j;
                    int my = y + dy[i] * j;
                    board[mx][my] = 0;
                }
            }
        }

    }
}
