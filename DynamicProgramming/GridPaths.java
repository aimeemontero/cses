import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.util.StringTokenizer;

public class GridPaths implements AutoCloseable {
    public static void main(String[] args) {
        try(
                GridPaths2 gridPaths = new GridPaths2(); //Initialize class element
        ) {
            gridPaths.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;
    int INF = (int)1e9+7;
    public void solve() {
        int n = nextInt();
        int[][] grid = new int[n][n];
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = nextString();
            for (int j = 0; j < n; j++) {
                grid[i][j] = line.charAt(j);
            }
        }
        res[0][0] = grid[0][0] == '*' ? 0: 1;
        int[] dx = {-1, 0};
        int[] dy = {0, -1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != '*') {
                    for (int k = 0; k < 2; k++) {
                        if (i + dx[k] >= 0 && j + dy[k] >= 0) {
                            res[i][j] += res[i + dx[k]][j + dy[k]];
                        }
                    }
                    res[i][j] = res[i][j] % INF;
                }
            }
        }
        printLine(res[n-1][n-1]);
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
        if (tokens == null || !tokens.hasMoreTokens()){
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

