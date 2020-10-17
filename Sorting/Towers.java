import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Towers implements AutoCloseable {
    public static void main(String[] args) {
        try(
                Towers towers = new Towers();//Initialize class element
        ) {
            towers.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public void solve() {
        int n = nextInt();

        TreeMap<Integer, Integer> tree = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            int next = nextInt();

            Integer key = tree.higherKey(next);

            if (key != null && tree.get(key) > 1) {
                tree.put(key, tree.get(key)-1);
            } else if (key != null) {
                tree.remove(key);
            }

            tree.put(next, tree.getOrDefault(next, 0) + 1);
        }

        long sum = 0;
        while (!tree.isEmpty()) {
            sum += tree.firstEntry().getValue();
            tree.remove(tree.firstKey());
        }

        printLine(sum);



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

