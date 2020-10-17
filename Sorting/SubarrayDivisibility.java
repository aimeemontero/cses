import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
import java.util.*;

public class SubarrayDivisibility implements AutoCloseable {
    public static void main(String[] args) {
        try(
                SubarrayDivisibility subarrayDivisibility = new SubarrayDivisibility(); //Initialize class element
        ) {
            subarrayDivisibility.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public void solve() {
        int n = nextInt();
        int[] p = new int[n];

        HashMap<Long, Integer> map = new HashMap();
        for (int i = 0; i < p.length; i++) {
            p[i] = nextInt();
        }

        long[] prefSum = new long[n];
        prefSum[0] = p[0];
        for (int i = 1; i < n; i++) {
            prefSum[i] = prefSum[i-1] + p[i];
        }

        long count = 0;
        map.put(0L, 1);
        for (int i = 0; i < prefSum.length; i++) {
            long mod = Math.floorMod(prefSum[i], n);
            if (map.containsKey(mod)) {
                count += map.get(mod);

            }
            map.put(mod,  map.getOrDefault(mod, 0) + 1);
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

