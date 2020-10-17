import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Permutations implements AutoCloseable {
    public static void main(String[] args) {
        try(Permutations algo = new Permutations()) {
            algo.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter output = new PrintWriter(System.out);
    StringTokenizer tokens;

    public void solve() {
        int n = nextInt();

        if (n == 1)
            printLine(1);

        if (n < 4) {
            printLine("NO SOLUTION");
            return;
        }

        int curr = 0;
        for (int i = 0; i < n; i++) {
            curr += 2;
            if (curr > n)
                curr = 1;

            print(curr + " ");
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

