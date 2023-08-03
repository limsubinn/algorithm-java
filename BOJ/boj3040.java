import java.io.*;

// 백준 3040: 백설 공주와 일곱 난쟁이
public class boj3040 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] numbers = new int[9];

        int sum = 0; // 모든 수의 합
        for (int i=0; i<9; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
            sum += numbers[i];
        }

        int result;
        int a = 0, b = 0;

        for (int i=0; i<8; i++) {
            for (int j=i+1; j<9; j++) {
                result = sum - numbers[i] - numbers[j]; // 두 수 골라서 빼기
                if (result == 100) { // 합이 100이면 해당 인덱스 저장하고 종료
                    a = i;
                    b = j;
                    break;
                }
            }
        }

        for (int i=0; i<9; i++) {
            // 해당 숫자 빼고 출력
            if (i == a || i == b) {
                continue;
            }
            bw.write(numbers[i] + "\n");
        }

        bw.flush();
        bw.close();

    }
}
