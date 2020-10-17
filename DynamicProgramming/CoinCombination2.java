
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CoinCombination2 implements AutoCloseable {
    public static void main(String[] args) {
        try(
                CoinCombination2 coinCombination2 = new CoinCombination2();
        ) {
            coinCombination2.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;
    int MOD =  (int)1e9+7;
    long INF =  (long)100000*MOD;
    public void solve() {
        int n = nextInt();
        int total = nextInt();

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = nextInt();
        }

        long[] dp = new long[total+1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= total; j++) {
                dp[j] = (dp[j] + dp[j-coins[i]]);
                if (dp[j] >= INF) {
                    dp[j] %= MOD;
                }
            }
        }
        printLine(dp[total] % MOD);
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