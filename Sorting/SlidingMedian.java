import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class SlidingMedian implements AutoCloseable {
    public static void main(String[] args) {
        try(
                SlidingMedian slidingMedian = new SlidingMedian();//Initialize class element
        ) {
            slidingMedian.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    TreeSet<Integer> maxHeap;
    TreeSet<Integer> minHeap;

    public void balance() {
        int cmp = maxHeap.size() - minHeap.size();
        if (cmp >= 0 && cmp <= 1)
            return;

        if (cmp < 0) {
            int element = minHeap.pollFirst();
            maxHeap.add(element);

        }
        else {
            int element = maxHeap.pollFirst();
            minHeap.add(element);
        }
    }

    public void addElement(int element) {
        if (maxHeap.isEmpty() && minHeap.isEmpty()) {
            maxHeap.add(element);
            return;
        }
        if (maxHeap.isEmpty()) {
            if (p[element] > p[minHeap.first()])
                minHeap.add(element);
            else
                maxHeap.add(element);
            return;
        }

        if (p[element] > p[maxHeap.first()]) {
            minHeap.add(element);
        } else {
            maxHeap.add(element);
        }
        balance();
    }

    public void removeElement(int element) {
        if (maxHeap.contains(element))
            maxHeap.remove(element);

        else minHeap.remove(element);

        balance();
    }

    int[] p;
    public void solve() {

        int n = nextInt();
        int k = nextInt();
        p = new int[n];

        Comparator<Integer> cmp = Comparator.<Integer>comparingInt(o -> p[o]).thenComparingInt(o->o);
        minHeap = new TreeSet<>(cmp);
        maxHeap = new TreeSet<>(cmp.reversed());

        for (int i = 0; i < n; i++) {
            p[i] =  nextInt();
        }

        int ini = 0;
        for (int i = 0; i < n; i++) {
            addElement(i);
            if (i - ini + 1 >= k) {
                int index = maxHeap.first();
                print(p[index] + " ");
                removeElement(ini);
                ini++;
            }
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

