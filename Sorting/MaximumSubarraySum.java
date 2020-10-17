import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.util.StringTokenizer;

public class MaximumSubarraySum implements AutoCloseable {
    public static void main(String[] args) {
        try(
                MaximumSubarraySum maximumSubarraySum = new MaximumSubarraySum();
        ) {
            maximumSubarraySum.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public void solve() {
        int n = nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = nextInt();
        }

        long count = array[0];
        long best = count;
        for (int i = 1; i < n; i++) {
            count = Math.max(array[i], count + array[i]);
            best = Math.max(best, count);
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




