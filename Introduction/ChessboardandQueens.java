import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.util.StringTokenizer;

public class ChessboardandQueens implements AutoCloseable {
    public static void main(String[] args) {
        try(
                ChessboardandQueens queens = new ChessboardandQueens();//Initialize class element
        ) {
            queens.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public void solve() {
        board = new int[8][8];
        count = 0;
        for (int i = 0; i < board.length; i++) {
            String line = nextString();
            for (int j = 0; j < board.length; j++) {
                if (line.charAt(j) == '*')
                    board[i][j] = -1;
            }
        }
        queens(0, 0);
        printLine(count);
    }

    int[] dx = {-1, 1, -1, 1};
    int[] dy = {-1, 1, 1, -1};
    int[][] board;
    int count;

    public boolean possible(int r, int c) {
        for (int i = 0; i < board.length; i++) {
            if (i != c && board[r][i] == 1)
                return false;
        }

        for (int i = 0; i < board.length; i++) {
            if (i != r && board[i][c] == 1)
                return false;
        }

        for (int i = 0; i < dx.length; i++) {
            int tr = r;
            int tc = c;
            while(tr + dx[i] >= 0 && tr + dx[i] < board.length &&
                    tc + dy[i] >= 0 && tc + dy[i] < board.length) {
                if (board[tr+dx[i]][tc+dy[i]] == 1)
                    return false;
                tr += dx[i];
                tc += dy[i];

            }
        }
        return true;
    }

    public void queens(int r, int q) {
        if (q == 8)
            count++;

        if (r >= board.length) {
            return;
        }

        for (int i = 0; i < board.length; i++) {
            if (board[r][i] == 0 && possible(r, i)) {
                    board[r][i] = 1;
                    queens(r+1, q+1);
                    board[r][i] = 0;
            }
        }
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

