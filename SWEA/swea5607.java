import java.io.*;
import java.util.*;

// swea 5607. 조합
public class swea5607 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			sb.append("#").append(t).append(" ").append(nCr(n, r, 1234567891)).append("\n");
		}
		
		System.out.print(sb);

	}
	
	/* 
	 * 페르마 소정리
	 */
	
	static long nCr(int n, int r, int p) {
		if (r == 0) {
			return 1L;
		}
		
		long[] fac = new long[n+1];
		fac[0] = 1;
		
		for (int i=1; i<=n; i++) {
			fac[i] = fac[i-1] * i % p;
		}
		
		return (fac[n] * power(fac[r], p-2, p)
				% p * power(fac[n-r], p-2, p) % p) % p;
	}

	static long power(long x, long y, long p) {
		long res = 1L;
		x = x % p;
		
		while (y > 0) {
			if (y % 2 == 1)  {
				res = (res * x) % p;
			}
			y = y >> 1;
			x = (x * x) % p;
		}
		
		return res;
	}
}

/*
이해 안 가서 받은 재민 코드

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5607 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int mod = 1234567891;

    static long pow(long n, long indices) {
        if (indices == 0)
            return 1;
        long ret = pow(n, indices / 2);
        if (indices % 2 == 0)
            return (ret * ret) % mod;
        else
            return (ret * ret) % mod * n % mod;
    }

    public static void main(String[] args) throws Exception {
        long[] fact = new long[1000001];
        fact[0] = 1;
        for (int i = 1; i < 1000001; i++) {
            fact[i] = (fact[i - 1] * i) % mod;
        }

        long ans;
        int T, N, R;
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            ans = fact[N] * pow((fact[R] * fact[N - R]) % mod, 1234567889) % mod;
            sb.append('#').append(t).append(' ').append(ans).append('\n');
        }
        System.out.println(sb);
    }
}
*/