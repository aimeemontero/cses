import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.util.StringTokenizer;

public class TwoSets implements AutoCloseable {
    public static void main(String[] args) {
        try(
                TwoSets twoSets  = new TwoSets();
        ) {
            twoSets.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    int n;
    public void solve() {
        n =  nextInt();
        long total  = ((n  * (long)(n+1)) / 2);
        if (total % 2 ==  1) {
            printLine("NO");
            return;
        }
        printLine("YES");
        if (n % 2 == 0) {
            int i = 1;
            printLine(n/2);
            for (; i <= n / 4; i++) {
                print(i + " " + (n-i+1) + " ");
            }
            printLine("");
            printLine(n/2);
            for (; i <= n / 2; i++) {
                print(i + " " + (n-i+1) + " ");
            }
        } else  {
            int count = 0;
            int middle = (n/2 + 1)/2;

            int firstElement = n / 2;
            int second = (n+1) / 2;
            printLine(firstElement);
            int i = 1;
            for (; count < firstElement;i++) {
                if (i != middle) {
                    print(i + " ");
                    count++;
                }
                print((n-i+1) + " ");
                count++;
            }
            printLine("");
            printLine(second);
            count = 1;
            print(middle + " ");
            while (count < second) {
                print(i + " ");
                count++;
                i++;
            }

        }
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

