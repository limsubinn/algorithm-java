import java.util.*;

// ���� 2023: �ű��� �Ҽ�
public class boj2023 {

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		dfs(0, n);
		
		System.out.println(sb.toString());

	}

	static boolean isPrimeNumber(int num) {
		if (num < 2) return false;
		
		for (int i=2; i*i<=num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	static void dfs(int num, int n) { // �Ҽ�, �ڸ���
		// �ڸ����� �ϼ����� �� (���� Ž�� ��)
		if (n == 0) {
			sb.append(num + "\n");
		}

		// ���� �����
		for (int i=1; i<=9; i++) {
			int temp = 10 * num + i;
			// �Ҽ��̸� ���� �ڸ� Ž�� ?.. ?? ��
			if (n > 0 && isPrimeNumber(temp)) {
				dfs(temp, n-1);
			}
		}
	}
}
