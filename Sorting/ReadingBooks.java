import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.util.*;


public class ReadingBooks implements AutoCloseable {
    public static void main(String[] args) {
        try(
                ReadingBooks readingBooks = new ReadingBooks();//Initialize class element
        ) {
            readingBooks.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    static class IntList extends AbstractList<Long> {
        final private long[] array;
        private IntList(long[] array) {
            this.array = array;
        }

        @Override
        public Long get(int index) {
            return array[index];
        }

        @Override
        public int size() {
            return array.length;
        }

        @Override
        public Long set(int index, Long element) {
            long old = array[index];
            array[index] = element;
            return old;
        }
    }

    // Tutorial solution return if (max > sum) return 2*max else return sum;
    public void solve() {
        int n = nextInt();
        long[] first = new long[n];
        long[] second = new long[n];;

        for (int i = 0; i < first.length; i++) {
            first[i] = nextInt();
        }

        new IntList(first).sort(Comparator.comparingLong(x -> x));

        for (int i = 1; i < n; i++) {
            second[i] = first[i-1];
        }
        second[0] = first[n-1];

        int f = 0;
        int s = 0;
        long sum = 0;
        while (f < n && s < n) {
            long min = Math.min(first[f], second[s]);
            if (min <= 0) {
                min = first[f] == 0? second[s]: first[f];
            }

            if ((f + 1) % n == s) {
                sum += min;
                second[s] -= min;
                s++;
            } else {
                sum += min;
                first[f] -= min;
                second[s] -= min;

                f = first[f] == 0  ? f + 1: f;
                s = second[s] == 0  ? s + 1: s;
            }
        }
        int t = Math.min(f, s);
        while(t < n) {
            sum += first[t] + second[t];
            t++;
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

