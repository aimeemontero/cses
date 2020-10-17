import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class SumofTwoValues implements AutoCloseable {
    public static void main(String[] args) {
        try(
                SumofTwoValues sumofTwoValues = new SumofTwoValues();//Initialize class element
        ) {
            sumofTwoValues.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    int[] array;
    long[] values;
    public void solve() {
        int n = nextInt();
        int x = nextInt();

        array = new int[n];
        values = new long[n];

        for (int i = 0; i < array.length; i++) {
            array[i] = i;
            values[i] = nextInt();
        }

        Collections.sort(new IntervalList(array),
                        Comparator.comparingLong(o -> values[o]));

        for (int i = 0; i < array.length; i++) {
            long value = x - values[array[i]];
            int pos = bs(value, i+1);
            if (pos != -1) {
                printLine((array[pos]+1) + " " + (array[i]+1));
                return;
            }
        }
        printLine("IMPOSSIBLE");
    }

    public int bs(long n, int pos) {
        int l = pos;
        int h = array.length - 1;

        while(l <= h) {
            int mid = (l + h) / 2;
            if (n == values[array[mid]]) {
                return mid;
            } else if (values[array[mid]] < n) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return -1;
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

    public final static class IntervalList extends AbstractList<Integer> {
        final int[] array;
        public IntervalList(int[] array) {
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
            Integer prev = array[index];
            array[index] = element;
            return prev;
        }
    }
}

