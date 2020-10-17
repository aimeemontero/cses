import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class StickLengths implements AutoCloseable {
    public static void main(String[] args) {
        try(
                StickLengths stickLengths = new StickLengths();//Initialize class element
        ) {
            stickLengths.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public void solve() {
        int n = nextInt();
        int[] p = new  int[n];

        for (int i = 0; i < n; i++) {
            p[i] = nextInt();
        }
        Arrays.sort(p);
        int middle = p[n/2];

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.abs(p[i] - middle);
        }
        print(sum);

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


