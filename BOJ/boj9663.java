import java.util.*;

// 백준 9663: N-Queen
public class boj9663 {

    static int n;
    static int answer = 0;
    static int[] array;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        array = new int[n];

        nQueen(0);
        System.out.println(answer);
    }

    static void nQueen(int cnt) {
        // n개의 말을 놓았으면 종료
        if (cnt == n) {
            answer++;
            return;
        }

        for (int i=0; i<n; i++) {
            // cnt행에 차례대로 말을 놓는다.
            array[cnt] = i;

            // 말을 놓을 수 있으면 다음 행으로 넘어간다.
            if (isPossible(cnt)) {
                nQueen(cnt + 1);
            }
        }

    }

    static boolean isPossible(int cnt) {
        for (int i=0; i<cnt; i++) {
            // 같은 열에 말이 있으면 말을 놓을 수 없다.
            if (array[i] == array[cnt]) {
                return false;
            }

            // 대각선에 말이 있으면 말을 놓을 수 없다.
            if (Math.abs(cnt - i) == Math.abs(array[cnt] - array[i])) {
                return false;
            }
        }

        return true;
    }
}
