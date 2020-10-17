import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class WeirdAlgorithm {
    public static void main(String[] args) throws java.lang.Exception {
        WeirdAlgorithm algo = new WeirdAlgorithm();
        algo.solve();
        algo.output.flush();
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public void solve() throws IOException {
        long numb = nextInt();
        while(numb != 1){
            print(numb + " ");
            if (numb % 2 == 0) {
                numb =  numb / 2;
            } else {
                numb =  numb * 3  + 1;
            }
        }

        print(1);

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

