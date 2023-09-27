import java.io.*;
import java.util.*;

// 백준 3055: 탈출
public class boj3055 {

    static int r, c;
    static String[][] board;
    
    static int answer = 0;
    
    static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        board = new String[r][c];
        
        Queue<int[]> dochi = new LinkedList<>();
        Queue<int[]> water = new LinkedList<>();
        
        for (int i=0; i<r; i++) {
            board[i] = br.readLine().split("");
            
            for (int j=0; j<c; j++) {
                // 고슴도치
                if (board[i][j].equals("S")) {
                   dochi.add(new int[] {i, j});
                   continue;
                }
                
                // 물
                if (board[i][j].equals("*")) {
                	water.add(new int[] {i, j});
                }
            }
        }
        
        while (!dochi.isEmpty()) {
        	water = move(false, water);    	
        	dochi = move(true, dochi);  	
        	answer++;
        }
        
        System.out.println("KAKTUS");
        
    }
    
    static Queue<int[]> move(boolean isDochi, Queue<int[]> queue) {
    	Queue<int[]> nq = new LinkedList<>();

        while (!queue.isEmpty()) {
            int[] d = queue.poll();
            int x = d[0];
            int y = d[1];
            
            for (int i=0; i<4; i++) {
            	int mx = x + dx[i];
            	int my = y + dy[i];
            	
            	// 이동 불가능
            	if (mx < 0 || mx >= r || my < 0 || my >= c) { 
            		continue;
            	}
            	
            	// 이동 불가능
            	if (board[mx][my].equals("*") || board[mx][my].equals("X")) {
            		continue;
            	}
            	
            	// 고슴도치 이동 불가능
            	if (isDochi && board[mx][my].equals("S")) {
            		continue;
            	}
            	
            	// 비버의 굴을 만난 경우
            	if (board[mx][my].equals("D")) {
            		// 고슴도치이면 도착
            		if (isDochi) {
            			System.out.println(answer+1);
                		System.exit(0);
            		}
            		
            		// 물이면 이동 불가능
            		continue;
            	}
            
            	// 이동 가능한 경우
            	nq.add(new int[] {mx, my});
            	
            	// 고슴도치
            	if (isDochi) {
            		board[mx][my] = "S";
            	} 
            	
            	// 물
            	else {
            		board[mx][my] = "*";
            	}
            }
        }
        
        return nq;
    }


}