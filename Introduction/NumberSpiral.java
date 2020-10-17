import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.util.StringTokenizer;

public class NumberSpiral implements AutoCloseable {
    public static void main(String[] args) {
        try(NumberSpiral algo = new NumberSpiral()) {
            algo.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public void solve() {
        int t = nextInt();
        for (int i = 0; i < t; i++) {
            long x = nextInt();
            long y = nextInt();

            long lead = Math.max(x, y);
            long carry = Math.min(x, y) - 1;
            int res = x >= y ? 0: 1;

            if (x == 1 && y == 1) {
                printLine(1);
                continue;
            }

            long n;
            if (lead % 2 == res) {
                n = lead * lead;
                printLine(n - carry);
                continue;
            }
            else {
                n = (lead-1)*(lead-1) + 1;
                printLine(n + carry);
                continue;
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

