import java.io.*;
import java.util.*;

// 백준 1244: 스위치 켜고 끄기
public class boj1244 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 스위치 개수
        int n = Integer.parseInt(br.readLine());

        // 각 스위치의 상태
        int[] switches = new int[n+1];
        switches[0] = 0;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i=1; i<n+1; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        // 학생 수
        int s = Integer.parseInt(br.readLine());

        for (int i=0; i<s; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            // 성별, 학생이 받은 수
            int gender = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            if (gender == 1) { // 남자일 경우
                int temp = num;
                while (temp <= n) {
                    // 스위치 상태 바꾸기
                    if (switches[temp] == 1) {
                        switches[temp] = 0;
                    } else {
                        switches[temp] = 1;
                    }
                    temp += num; // 배수 증가
                }
            } else { // 여자일 경우
                // 현재 위치의 스위치 상태 바꾸기
                if (switches[num] == 1) {
                    switches[num] = 0;
                } else {
                    switches[num] = 1;
                }

                // 현재 위치를 중심으로 대칭으로 뻗어나간다.
                int start = num - 1;
                int end = num + 1;

                // 인덱스 범위를 벗어나지 않을 때까지 반복
                while (start > 0 && end <= n) {
                    // 스위치 상태가 좌우대칭이 아닐 경우 종료
                    if (switches[start] != switches[end]) {
                        break;
                    }

                    // 스위치 상태 바꾸기
                    if (switches[start] == 1) {
                        switches[start] = 0;
                        switches[end] = 0;
                    } else {
                        switches[start] = 1;
                        switches[end] = 1;
                    }

                    // 인덱스 조정
                    start--;
                    end++;
                }
            }

        }

        int cnt = 0;
        for (int i=1; i<n+1; i++) {
            bw.write(switches[i] + " ");
            // 한 줄에 20개씩 출력
            if (++cnt >= 20) {
                bw.write("\n");
                cnt = 0;
            }
        }
        bw.write("\n");
        bw.flush();
        bw.close();

    }

}