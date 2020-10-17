import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.util.StringTokenizer;

public class IncreasingSubsequence implements AutoCloseable {
    public static void main(String[] args) {
        try(
                IncreasingSubsequence increasingSubsequence = new IncreasingSubsequence();//Initialize class element
        ) {
            increasingSubsequence.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public void solve() {
        int n = nextInt();
        int[] p = new int[n];
        int[] t = new int[n];

        for (int i = 0; i < n; i++) {
            p[i] = nextInt();
            t[i] = Integer.MAX_VALUE;
        }

        int pos = 0;
        int best = 0;
        for (int i = 0; i < n; i++) {
            int tmp = getPos(t, pos, p[i]);
            t[tmp] = p[i];
            pos = Math.max(pos, tmp);
            if (pos > best) {
                best = pos;
            }
        }

        printLine(pos+1);
    }

    public int getPos(int[] t, int n, int k) {
        int l = 0;
        int h = n;

        while ( l <= h) {
            int mid = (l + h) / 2;
            if (k > t[mid]) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return l;
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

