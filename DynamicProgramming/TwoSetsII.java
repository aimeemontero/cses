import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TwoSetsII implements AutoCloseable {
    public static void main(String[] args) {
        try (
                TwoSetsII twoSets = new TwoSetsII();//Initialize class element
        ) {
            twoSets.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    ;
    PrintWriter output = new PrintWriter(System.out);
    ;
    StringTokenizer tokens;

    final static int MOD = (int)1e9+7;
    final static long INF = MOD * 1_000_000L;
    public void solve() {
        int n = nextInt();

        int t = (n*(n+1)/2);
        if (t % 2 != 0) {
            printLine(0);
            return;
        }
        t = t / 2;


        long[] dp = new long[t+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = Math.min((i*(i+1))/2, t); j >= i; j--) {
                dp[j] = dp[j] + dp[j - i];
                if (dp[j] > INF) {
                    dp[j] = dp[j] % MOD;
                }
            }
        }

        dp[t] = dp[t] % MOD;
        int inverse = pow(2, MOD-2);
        printLine((dp[t] * inverse) % MOD);
    }

    public int pow(int a, int p) {
        if (p == 0)
            return 1;

        if  (p % 2 == 0) {
            long tmp =  pow(a, p/2);
            return (int)(tmp*tmp % MOD);
        } else {
            long tmp =  pow(a, (p-1)/2);
            return (int)(((tmp*tmp % MOD) *a) % MOD);
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
        while (tokens == null || !tokens.hasMoreTokens()) {
            getLineTokenizer();
        }

        return tokens.hasMoreTokens() ? tokens.nextToken() : null;

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

