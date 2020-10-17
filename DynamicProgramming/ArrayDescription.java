import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ArrayDescription implements AutoCloseable {
    public static void main(String[] args) {
        try(
                ArrayDescription arrayDescription = new ArrayDescription();
        ) {
            arrayDescription.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    final int INF = (int)1e9+7;
    public void solve() {
        int n = nextInt();
        int m = nextInt();

        int[] numb = new int[n];
        for (int i = 0; i < n; i++) {
            numb[i] = nextInt();
        }

        long[][] dp = new long[n][m+1];
        int last = 0;

        if(numb[0] != 0) {
            dp[0][numb[0]] = 1;
        } else {
            for (int i = 1; i <= m; i++) {
                dp[0][i] = 1;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                if (numb[i] == 0 || numb[i] == j) {
                    dp[i][j] += dp[i - 1][j];
                    if (j - 1 >= 0)
                        dp[i][j] += dp[i - 1][j - 1];
                    if (j + 1 <= m)
                        dp[i][j] += dp[i - 1][j + 1];
                    dp[i][j] = dp[i][j] % INF;
                }
            }
        }

        long sum= 0;
        for (int i = 1; i <= m; i++) {
            sum = (sum  + dp[n-1][i]) % INF;
        }
        printLine(sum);
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
        String token = getString();
        return Long.parseLong(token);
    }

    private int nextInt() {
        String token = getString();
        return Integer.parseInt(token);
    }


    private String getString() {
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


