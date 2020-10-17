import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BitStrings implements AutoCloseable {
    public static void main(String[] args) {
        try(
                BitStrings bitStrings = new BitStrings();
        ) {
            bitStrings.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    int INF =  (int)1e9+7;
    long pow(int numb, int a) {
        if (a == 1)
            return numb;

        if (a % 2 == 0) {
            long prevPow = pow(numb, a / 2);
            return (prevPow * prevPow) % INF;
        } else {
            long prevPow =  pow(numb, (a-1)/2);
            return (((prevPow  *  prevPow) % INF) * numb) % INF;
        }
    }

    public void solve() {
        int n = nextInt();

        printLine(pow(2, n));
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

