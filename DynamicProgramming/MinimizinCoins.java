import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.util.StringTokenizer;

public class MinimizinCoins implements AutoCloseable {
    public static void main(String[] args) {
        try(
            MinimizinCoins minimizinCoins = new MinimizinCoins();
        ) {
            minimizinCoins.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;
    final int  INF = Integer.MAX_VALUE / 2;

    public void solve() {
        int n = nextInt();
        int x = nextInt() + 1;
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] =  nextInt();
        }

        long[] amounts = new long[x];
        for (int i = 0; i < x; i++) {
            amounts[i] = INF;
            for (int j = 0; j < n; j++) {
                if (i -  c[j] > 0 && amounts[i-c[j]] != INF) {
                    amounts[i] = Math.min(amounts[i], amounts[i-c[j]] + 1);
                } else if (i == c[j]) {
                    amounts[i] = 1;
                }
            }
        }
        if (amounts[x-1] != INF)
            printLine(amounts[x-1]);
        else
            printLine(-1);
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

