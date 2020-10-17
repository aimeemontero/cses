import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.util.StringTokenizer;

public class BookShop implements AutoCloseable {
    public static void main(String[] args) {
        try(
                BookShop bookShop = new BookShop();//Initialize class element
        ) {
            bookShop.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public void solve() {
        int n = nextInt();
        int x = nextInt();

        int[] cost = new int[n];
        int[] pages = new int[n];
        for (int i = 0; i < n; i++) {
            cost[i] = nextInt();
        }
        for (int i = 0; i < n; i++) {
            pages[i] = nextInt();
        }

        int[] dp = new int[x+1];
        long best = 0;
        for (int i = 0; i < n; i++) {
            for (int j = x; j >= 0; j--) {
                if (j - cost[i] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - cost[i]] + pages[i]);
                    best = Math.max(best, dp[j]);
                }

            }
        }

        printLine(best);

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

