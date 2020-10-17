import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MissingNumber {
    public static void main(String[] args) throws java.lang.Exception {
        MissingNumber algo = new MissingNumber();
        algo.solve();
        algo.output.flush();
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public void solve() throws IOException {
        int n = nextInt();
        int sum = 0;
        int total = n;
        for (int i = 1; i <= n - 1; i++) {
            total += i;
            sum += nextInt();
        }

        print(total - sum);

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

    private long nextLong() throws IOException {
        String token = getToken();
        if(token != null)
            return Long.parseLong(token);

        throw new IOException();
    }

    private int nextInt() throws IOException {
        String token = getToken();
        if(token != null)
            return Integer.parseInt(token);

        throw new IOException();
    }


    private String nextString() throws IOException {
        String token = getToken();
        if(token != null)
            return token;

        throw new IOException();
    }


    private String getToken() throws IOException {
        if (tokens == null || !tokens.hasMoreTokens()){
            getLineTokenizer();
        }

        return tokens.hasMoreTokens() ? tokens.nextToken(): null;

    }
    private boolean getLineTokenizer() throws IOException {
        String  line = reader.readLine();
        if (line == null)
            return false;

        tokens = new StringTokenizer(line);
        return tokens.hasMoreTokens();
    }
}

