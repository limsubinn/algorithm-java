import java.io.*;
import java.util.*;

// swea 10726. 이진수 표현
public class swea10726 {

    static int n, m;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            
            String bin = changeToBin(); // 이진수 변환
            sb.append("#").append(t).append(" ").append(isOn(bin)).append("\n");
        }
        
        System.out.print(sb);
    }
    
    static String changeToBin() {
        StringBuilder bin = new StringBuilder();
        
        // 0일 때
        if (m == 0) {
            return "0";
        }
        
        while (m > 0) {
            bin.append(m % 2);
            m /= 2;
        }
        bin = bin.reverse();
        
        return bin.toString();
    }
    
    static String isOn(String bin) {
        int len = bin.length();
        
        // 이진수 변환한 길이가 n보다 작을 때
        if (len < n) {
        	return "OFF";
        }
        
        // 마지막 n개의 비트 비교
        for (int i=len-1; i>=len-n; i--) {
        	// 1이 있을 경우 OFF
        	if (bin.charAt(i) != '1') {
            	return "OFF";
            }
        }
            
        return "ON";
    }

}