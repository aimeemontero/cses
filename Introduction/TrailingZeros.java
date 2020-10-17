import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.util.StringTokenizer;

public class TrailingZeros implements AutoCloseable {
    public static void main(String[] args) {
        try(
                TrailingZeros trailingZeros = new TrailingZeros();
        ) {
            trailingZeros.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    int INF = (int)1e9;
    public void solve() {
       int n = nextInt();

       int count5 = 0;
       for (int i  = 5; i <= n; i+=5) {
           int pow = 25;
           count5++;
           while (i % pow == 0){
               count5++;
               pow *= 5;
           }
       }
       printLine(count5);
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


