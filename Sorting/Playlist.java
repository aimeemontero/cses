import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Playlist implements AutoCloseable {
    public static void main(String[] args) {
        try(
                Playlist playlist = new Playlist();//Initialize class element
        ) {
            playlist.solve();
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

        HashMap<Integer, Integer> map = new HashMap<>();
        int ini = 0;
        int best = 0;
        int currCount = 0;
        for (int end = 0; end < n; end++) {
            if (map.containsKey(p[end])) {
                ini = Math.max(ini, map.get(p[end]) + 1);
            }
            map.put(p[end], end);
            currCount = end - ini + 1;
            if (best < currCount)
                best = currCount;
        }
        printLine(best);
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

