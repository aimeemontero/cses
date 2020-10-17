import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DistinctNumbers implements AutoCloseable {
    public static void main(String[] args) {
        try(DistinctNumbers algo = new DistinctNumbers()) {
            algo.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public void solve() {
        int n  = nextInt();
        int[] elements = new int[n];
        for (int i = 0; i < n; i++) {
            elements[i] = nextInt();
        }
        Arrays.sort(elements);
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (elements[i-1] - elements[i] != 0)
                count++;
        }
        printLine(count+1);
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

