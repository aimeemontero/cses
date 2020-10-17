import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Repetitions {
    public static void main(String[] args) throws java.lang.Exception {
        Repetitions algo = new Repetitions();
        algo.solve();
        algo.output.flush();
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public void solve() throws IOException {
        String gene = nextString();
        int sum = 0;
        int count = 1;
        char prev  = gene.charAt(0);
        int best = 0;
        for (int i = 1; i < gene.length(); i++) {
            char curr = gene.charAt(i);
            if  (curr == prev)
                count++;
            else {
                if (count >  best) {
                    best  = count;
                }
                count = 1;
            }
            prev = curr;
        }

        print(Math.max(best, count));

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

