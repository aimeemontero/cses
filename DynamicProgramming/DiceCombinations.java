import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DiceCombinations implements AutoCloseable {
    public static void main(String[] args) {
        try(DiceCombinations algo = new DiceCombinations()) {
            algo.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    long MOD = (long)(1e9 + 7);
    public void solve() {
        int n = nextInt();
        long[] sums = new long[n+1];

        sums[0] = 0;
        for (int i = 1; i < sums.length; i++) {
            for (int j = 1; j <= 6; j++) {
                if (i - j > 0 && sums[i-j] > 0)
                    sums[i] = (sums[i] + sums[i-j]) % MOD;
                if (i == j)
                    sums[i] = (sums[i]+1) % MOD;
            }
        }

        printLine(sums[n]);

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
        return Long.parseLong(nextString());
    }

    private int nextInt() {
        return Integer.parseInt(nextString());
    }


    private String nextString() {
        if (tokens == null || !tokens.hasMoreTokens()){
            getLineTokenizer();
        }
        return tokens.nextToken();

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



