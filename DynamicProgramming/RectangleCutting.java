import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.util.StringTokenizer;

public class RectangleCutting implements AutoCloseable {
    public static void main(String[] args) {
        try(
                RectangleCutting rectangleCutting = new RectangleCutting();
        ) {
            rectangleCutting.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public void solve() {
        int r = nextInt();
        int c = nextInt();

        int[][] dp = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (i == j) {
                    dp[i][j] = 0;
                    continue;
                }

                int maxCutting = Math.max(i, j);
                for (int k = 1; k <= maxCutting; k++) {
                    int cut;
                    if (k <= i && k <= j) {
                        cut = Math.min(dp[k-1][j] + dp[i-k][j],
                                       dp[i][k-1] + dp[i][j-k]) + 1;
                    } else if (k <= i) {
                        cut = dp[k-1][j] + dp[i-k][j] + 1;
                    } else {
                        cut = dp[i][k-1] + dp[i][j-k] + 1;
                    }
                    dp[i][j] = Math.min(dp[i][j], cut);
                }
            }
        }
        printLine(dp[r-1][c-1]);
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

