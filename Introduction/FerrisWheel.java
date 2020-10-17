import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class FerrisWheel implements AutoCloseable {
    public static void main(String[] args) {
        try(
            FerrisWheel ferrisWheel = new FerrisWheel();
        ) {
             ferrisWheel.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public void solve() {
        int n = nextInt();
        int p = nextInt();

        int[] kids = new int[n];
        for (int i = 0; i < n; i++) {
            kids[i] = nextInt();
        }

        new IntList(kids).sort(Integer::compareTo);

        int l = 0;
        int r = n-1;

        int  count  = 0;
        while (l < r) {
            if (kids[l] + kids[r] <= p) {
                l++;
                r--;
                count++;
            } else {
                r--;
                count++;
            }
        }
        if (l == r)
            count++;

        printLine(count);
    }

    static class IntList extends AbstractList<Integer> {
        final private int[] array;
        private IntList(int[] array) {
            this.array = array;
        }

        @Override
        public Integer get(int index) {
            return array[index];
        }

        @Override
        public int size() {
            return array.length;
        }

        @Override
        public Integer set(int index, Integer element) {
            int old = array[index];
            array[index] = element;
            return old;
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


