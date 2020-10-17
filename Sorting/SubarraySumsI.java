import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SubarraySumsI implements AutoCloseable {
    public static void main(String[] args) {
        try(
                SubarraySumsI subarraySumsI = new SubarraySumsI();//Initialize class element
        ) {
            subarraySumsI.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public void solve() {
        int n = nextInt();
        int k = nextInt();
        long[] p = new long[n];
        HashMap<Long, Long> map = new HashMap<>();

        for (int i = 0; i < p.length; i++) {
            p[i] = nextInt();
        }
        long[] prefixSum = new long[n];
        prefixSum[0] = p[0];
        for (int i = 1; i < p.length; i++) {
            prefixSum[i] = prefixSum[i-1] + p[i];
        }

        map.put(0L, 1L);
        long count = 0;
        for (int i = 0; i < p.length; i++) {
            long prev = prefixSum[i] - k;
            if (map.containsKey(prev)) {
                count += map.get(prev);
            }
            long next = map.getOrDefault(prefixSum[i], 0L) + 1;
            map.put(prefixSum[i], next);
        }

        printLine(count);

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

