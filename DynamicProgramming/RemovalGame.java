import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.util.StringTokenizer;

public class RemovalGame implements AutoCloseable {
    public static void main(String[] args) {
        try(
                RemovalGame removalGame = new RemovalGame();//Initialize class element
        ) {
            removalGame.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public void solve() {
        int n = nextInt();
        memo = new long[n][n];
        list = new int[n];
        prefixSum = new long[n];
        for (int i = 0; i < n; i++) {
            list[i] = nextInt();
            prefixSum[i] = list[i];
            for (int j = 0; j < n; j++) {
                memo[i][j] = Long.MAX_VALUE;
            }
        }

        for (int i = 1; i < n; i++) {
            prefixSum[i] += prefixSum[i-1];
        }

        long res = bestScore(0, n - 1);
        printLine(res);
    }

    long[] prefixSum;

    int[] list;
    long[][] memo;
    public long bestScore(int ini, int end) {
        if (ini == end) {
            memo[ini][end] = list[ini];
            return list[ini];
        }

        if (memo[ini][end] != Long.MAX_VALUE) {
            return memo[ini][end];
        }

        long f = bestScore(ini + 1, end);
        long s = bestScore(ini, end - 1);
        long  total;
        if(ini == 0)
            total = prefixSum[end];

        else
            total = prefixSum[end] - prefixSum[ini - 1];

        return memo[ini][end] = Math.max(total-f, total-s);
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


