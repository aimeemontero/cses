import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MoneySums implements AutoCloseable {
    public static void main(String[] args) {
        try(
                MoneySums moneySums  = new MoneySums();
        ) {
            moneySums.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public void solve() {
        int n = nextInt();
        int[] x = new int[n];
        int total = 0;

        for (int i = 0; i < x.length; i++) {
            x[i] = nextInt();
            total += x[i];
        }

        boolean[] dp = new boolean[total+1];
        dp[0] = true;
        int count = 0;
        for (int j = 0; j < n; j++) {
            for (int i = total; i >= 0; i--) {
                if (i - x[j] >= 0)
                    dp[i] = dp[i-x[j]] || dp[i];
            }
        }

        for (int i = 1; i <= total; i++) {
            if (dp[i]) {
                count++;
            }
        }
        printLine(count);
        for (int i = 1; i <= total; i++) {
            if (dp[i]) {
                print(i + " ");
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


