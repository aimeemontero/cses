import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class NearestSmallerValues implements AutoCloseable {
    public static void main(String[] args) {
        try(
                NearestSmallerValues nearestSmallerValues = new NearestSmallerValues();//Initialize class element
        ) {
            nearestSmallerValues.solve();
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

        int[] res = new int[n];

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        res[0] = 0;
        for (int i = 1; i < n; i++) {
            while (!stack.empty() && p[stack.peek()] >= p[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                res[i] = 0;
            } else {
                res[i] = stack.peek()+1;
            }

            stack.add(i);
        }

        for (int i = 0; i < res.length; i++) {
            print(res[i] + " ");
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
        String token = nextString();
        return Long.parseLong(token);
    }

    private int nextInt() {
        String token = nextString();
        return Integer.parseInt(token);
    }


    private String nextString() {
        while (tokens == null || !tokens.hasMoreTokens()){
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


