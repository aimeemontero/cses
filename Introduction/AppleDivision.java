import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.util.StringTokenizer;

public class AppleDivision implements AutoCloseable {
    public static void main(String[] args) {
        try(
                AppleDivision appleDivision = new AppleDivision();//Initialize class element
        ) {
            appleDivision.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public void solve() {
        int n = nextInt();
        int[] p = new int[n];

        for (int i = 0; i < n; i++) {
            p[i] = nextInt();
        }

        long total = 0;
        for (int i = 0; i < n; i++) {
            total += p[i];
        }

        long first;
        long best = Long.MAX_VALUE;
        int numb = 1 << n;
        for (int i = 1; i < numb; i++) {
            int pos = 0;
            first = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1<<j)) != 0) {
                    first += p[pos];
                }
                pos++;
            }
            long diff = Math.abs(total - 2*first);
            if(diff < best) {
                best = diff;
            }
        }
        print(best);

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

