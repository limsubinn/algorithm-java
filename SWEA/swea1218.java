import java.io.*;
import java.util.*;

// swea1218. °ýÈ£ Â¦Áþ±â
public class swea1218 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder st = new StringBuilder();
		
		for (int t=1; t<=10; t++) {
			int n = Integer.parseInt(br.readLine());
			char[] str = br.readLine().toCharArray();
			
			Stack<Character> stack = new Stack<>();
			char[][] bracket = {{'{', '}'}, {'[', ']'}, {'(', ')'}, {'<', '>'}}; // °ýÈ£
			
			for (int i=0; i<n; i++) {
				if (!stack.isEmpty()) {
					boolean flag = false;
					
					for (char[] b: bracket) {
						// °ýÈ£ Â¦ÀÌ ¸ÂÀ» ¶§ ½ºÅÃ¿¡¼­ ²¨³»±â
						if (stack.peek() == b[0] && str[i] == b[1]) {
							stack.pop();
							flag = true;
							break;
						}
					}
					
					if (flag) continue;
				}
				// °ýÈ£ Â¦ÀÌ ¸ÂÁö ¾ÊÀ» ¶§ ½ºÅÃ¿¡ ³Ö±â
				stack.push(str[i]);
			}
			
			
			st.append("#" + t + " ");
			if (stack.isEmpty()) {
				st.append(1);
			} else {
				st.append(0);
			}
			st.append("\n");
		}
		
		System.out.println(st.toString());

	}
}
