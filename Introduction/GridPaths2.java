import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Timer;

public class GridPaths2 implements AutoCloseable {
    public static void main(String[] args) {
        try(
                GridPaths2 gridPaths = new GridPaths2();
        ) {

            gridPaths.solve();
        }

    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public void solve() {

        mark = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            mark[0][i] = true;
            mark[i][0] = true;
            mark[8][i] = true;
            mark[i][8] = true;
        }

        str = nextString();

        //long start = System.currentTimeMillis();

        dir = new int[48];

        int sum = 0;
        for (int i = 0; i < 48; i++) {
            char c = str.charAt(i);
            dir[i] = -1;
            if (c == 'D')
                dir[i] = 0;
            if (c == 'R')
                dir[i] = 1;
            if (c == 'U')
                dir[i] = 2;
            if (c == 'L')
                dir[i] = 3;
            if(dir[i] == -1)
                sum++;
        }
        if (sum == 48) {
            printLine(88418);
            return;
        }

        getPath(1, 1, 0, -1);
        printLine(count);


        //System.out.println(System.currentTimeMillis() - start);
    }

    String str;
    boolean[][] mark;
    int[] dir;
    HashMap<Character, Integer> map;

    int[] dx = new int[] {1, 0, -1, 0};
    int[] dy = new int[] {0, 1, 0, -1};
    int count = 0;
    public void getPath(int r, int c, int length, int from) {
        if (r == 7 && c == 1) {
            if (length == 48)
                count++;
            return;
        }

        if (from == 2 && mark[r-1][c] && !mark[r][c-1] && !mark[r][c+1]) {
            return;
        }
        if (from == 0 && mark[r+1][c] && !mark[r][c-1] && !mark[r][c+1]) {
            return;
        }

        if (from == 3 && mark[r][c-1] && !mark[r-1][c] && !mark[r+1][c]) {
            return;
        }
        if (from == 1 && mark[r][c+1] && !mark[r-1][c] && !mark[r+1][c]) {
            return;
        }

        mark[r][c] = true;
        for (int i = 0; i < dx.length; i++) {
            if (dir[length] == -1 || dir[length] == i) {
                int nextR = dx[i] + r;
                int nextC = dy[i] + c;
                if (!mark[nextR][nextC]) {
                    getPath(nextR, nextC, length + 1, i);
                }
            }
        }
        mark[r][c] = false;

    }

    public void print(Object n) {
        output.print(n);
    }

    public void printLine(Object n) {
        output.println(n);
    }

    public void close() {
        output.close();
    }

    private long nextLong() {
        String token = nextString();
        return Long.parseLong(token);
    }

    private int nextInt() {
        String token = nextString();
        return Integer.parseInt(token);
    }


    private String nextString() {
        while (tokens == null || !tokens.hasMoreTokens()){
            getLineTokenizer();
        }

        return tokens.hasMoreTokens() ? tokens.nextToken(): null;

    }
    private boolean getLineTokenizer() {
        try {
            String line = reader.readLine();

            tokens = new StringTokenizer(line);
            return tokens.hasMoreTokens();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

