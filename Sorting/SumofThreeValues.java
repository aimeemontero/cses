import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.AbstractList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SumofThreeValues implements AutoCloseable {
    public static void main(String[] args) {
        try(
                SumofThreeValues sumofThreeValues = new SumofThreeValues();//Initialize class element
        ) {
            sumofThreeValues.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

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

    int[] index;
    int[] p;
    public void solve() {
        int n = nextInt();
        int k = nextInt();
        p = new int[n];
        index = new int[n];


        for (int i = 0; i < p.length; i++) {
            p[i] = nextInt();
            index[i] = i;
        }

        new IntList(index).sort(Comparator.comparingInt(x -> p[x]));
        for (int i = 0; i < index.length; i++) {
            for (int j = i+1; j < index.length; j++) {
                int sum = p[index[i]] + p[index[j]];
                int next = ds(k - sum);
                if (next >= 0 && next != i && next != j) {
                    printLine((index[i] + 1) +  " " + (index[j] + 1 )+  " " + (index[next]+1));
                    return;
//                        return;
//                    int tmp = next;
//                    while (tmp < index.length && (index[tmp] == j || index[tmp] == i) && p[index[tmp]] == p[index[next]]) {
//                        tmp++;
//                    }
//                    if (tmp < index.length && index[tmp] != i && index[tmp] != j && p[index[tmp]] == p[index[next]]) {
//                        printLine((i+1) +  " " + (j+1) +  " " + index[tmp]);
//                        return;
//                    }
                }
            }
        }
        printLine("IMPOSSIBLE");
    }

    public int ds(int n) {
        int l = 0;
        int h = index.length - 1;

        while (l <= h) {
            int mid = (l + h) / 2;
            if (p[index[mid]] <= n)
                l = mid + 1;
            else {
                h = mid - 1;
            }
        }
        if (l - 1 < 0)
            return -1;

        return p[index[l-1]] == n ? l - 1 : - 1;
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

