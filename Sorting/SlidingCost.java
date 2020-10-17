import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SlidingCost implements AutoCloseable {
    public static void main(String[] args) {
        try(
                SlidingCost slidingCost = new SlidingCost();//Initialize class element
        ) {
            slidingCost.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    TreeSet<Integer> maxHeap;
    TreeSet<Integer> minHeap;

    long sumMax;
    long sumMin;

    public void balance() {
        int cmp = maxHeap.size() - minHeap.size();
        if (cmp >= 0 && cmp <= 1)
            return;

        if (cmp < 0) {
            int element = minHeap.pollFirst();
            maxHeap.add(element);
            sumMin -= p[element];
            sumMax += p[element];

        }
        else {
            int element = maxHeap.pollFirst();
            minHeap.add(element);
            sumMax -= p[element];
            sumMin += p[element];
        }
    }

    public void addElement(int element) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(element);
            sumMax += p[element];
            return;
        }
        
        if (p[element] > p[maxHeap.first()]) {
            minHeap.add(element);
            sumMin += p[element];
        } else {
            maxHeap.add(element);
            sumMax += p[element];
        }
        balance();
    }

    public void removeElement(int element) {
        if (maxHeap.contains(element)) {
            maxHeap.remove(element);
            sumMax -= p[element];
        }
        else {
            minHeap.remove(element);
            sumMin -= p[element];
        }

        balance();
    }

    int n;
    int k;
    int[] p;
    int[] prefSum;
    public void getMedian() {
        int ini = 0;
        for (int i = 0; i < n; i++) {
            addElement(i);
            if (i - ini + 1 >= k) {
                int index = maxHeap.first();
                int median = p[index];

                long before = median*maxHeap.size() - (sumMax);
                long after = sumMin - median*minHeap.size();

                print((before + after) + " ");
                removeElement(ini);
                ini++;
            }
        }

    }

    public void solve() {
        n = nextInt();
        k = nextInt();
        p = new int[n];
        prefSum = new int[n];

        sumMax = 0;
        sumMin = 0;

        Comparator<Integer> cmp = Comparator.<Integer>comparingInt(o -> p[o]).thenComparingInt(o->o);
        minHeap = new TreeSet<>(cmp);
        maxHeap = new TreeSet<>(cmp.reversed());

        for (int i = 0; i < n; i++) {
            p[i] =  nextInt();
        }

        getMedian();
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



