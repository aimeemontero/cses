import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
import java.util.AbstractList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MovieFestival implements AutoCloseable {
    public static void main(String[] args) {
        try(
                MovieFestival movieFestival = new MovieFestival();
        ) {
            movieFestival.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    int MAX = 400002;

    class Interval implements Comparable<Interval>{
        int id;
        int ini;
        int pos;
        boolean type;

        public Interval(int id, int ini, int pos, boolean type) {
            this.id = id;
            this.ini=ini;
            this.pos=pos;
            this.type=type;
        }

        @Override
        public int compareTo(Interval o) {
            if (pos == o.pos)
                return Integer.compare(ini, o.ini);
            return Integer.compare(pos, o.pos);
        }
    }

    public void solve() {
        int n = nextInt();
        Interval[] intervals = new Interval[2*n];
        int[] closedIntervals = new int[MAX];

        for (int i = 0; i < 2*n; i+=2) {
            int ini = nextInt();
            int end = nextInt();
            intervals[i] = new Interval(i, ini, ini, true) ;
            intervals[i+1] = new Interval(i, ini, end, false) ;
        }

        Collections.sort(new IntervalList(intervals));

        int closed = 0;
        for (int i = 0; i < 2*n; i++) {
            Interval interval = intervals[i];
            if (!interval.type) {
                if (closed - closedIntervals[interval.id] == 0)
                    closed++;
            }
            else {
                closedIntervals[interval.id] = closed;
            }
        }
        printLine(closed);

    }

    public class IntervalList extends AbstractList<Interval> {
        final Interval[] array;
        public IntervalList(Interval[] array) {
            this.array = array;
        }

        @Override
        public Interval get(int index) {
            return array[index];
        }

        @Override
        public int size() {
            return array.length;
        }

        @Override
        public Interval set(int index, Interval element) {
            Interval prev = array[index];
            array[index] = element;
            return prev;
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

