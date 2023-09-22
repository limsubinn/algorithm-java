import java.io.*;

// swea 1288. 새로운 불면증 치료법
public class swea1288 {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; t++) {
            int n = Integer.parseInt(br.readLine()); // 양을 세는 수
            boolean[] visited = new boolean[10];
            
            int k = 1; // 배수
            int cnt = 0; // 0~9 숫자를 본 횟수
            
            while (true) {
                long num = n * k;
                String[] snum = Long.toString(num).split("");
                
                for (String s: snum) {
                    int i = Integer.parseInt(s);

                    if (visited[i]) {
                        continue;
                    }
                    
                    // 숫자 세기
                    visited[i] = true;
                    cnt++;
                }
                
                // 0~9 모든 숫자를 봤을 때
                if (cnt >= 10) {
                    sb.append("#").append(t).append(" ").append(num).append("\n");
                    break;
                }
                
                k++;
            }
        }

        System.out.print(sb);
    }

}