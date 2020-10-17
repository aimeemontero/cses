import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class RemovingDigits implements AutoCloseable {
    public static void main(String[] args) {
        try(
                RemovingDigits removingDigits = new RemovingDigits();
        ) {
            removingDigits.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    int INF = Integer.MAX_VALUE/2;
    public void solve() {
        int n = nextInt();
        int[] sum = new int[n+1];
        for (int i = 0; i < n; i++) {
            sum[i] = INF;
        }
        sum[n] = 0;
        for (int i = n; i >= 0; i--) {
            int tmp = i;
            while(sum[i] != INF && tmp > 0) {
                int digit = tmp % 10;
                if (i - digit >= 0)
                    sum[i-digit] = Math.min(sum[i-digit], sum[i] + 1);
                tmp = tmp / 10;
            }
        }
        printLine(sum[0]);
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

