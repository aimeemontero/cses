import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class SumofFourValues implements AutoCloseable {
    public static void main(String[] args) {
        try(
                SumofFourValues sumofFourValues = new SumofFourValues();//Initialize class element
        ) {
            sumofFourValues.solve();
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
    int INF = (int)1e9 + 1;
    HashMap<Integer, List<Integer>> map;


    public void getTwoSum(int limit, int[] values) {
        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < limit; j++) {
                if (j <= i)
                    values[i*limit + j] = INF;
                else {
                    int value = p[i] + p[j];
                    int index  =  (i << 16) | j;
                    values[i*limit + j] = value;
                    List<Integer> newList =  new ArrayList<>();
                    if (map.containsKey(value)) {
                        newList =  map.get(value);
                        newList.add(index);
                        map.put(value, newList);
                    } else {
                        newList.add(index);
                        map.put(p[i] + p[j], newList);
                    }
                }
            }
        }
    }

    public void solve() {
        int n = nextInt();
        int k = nextInt();
        p = new int[n];
        map =  new HashMap<>(10*n*n);

        for (int i = 0; i < p.length; i++) {
            p[i] = nextInt();
        }

        int[] first = new int[n*n];
        getTwoSum(n, first);

        for (int i = 0; i < first.length; i++) {
            if (first[i] < INF && map.containsKey(k - first[i])) {
                List<Integer> list =  map.get(k - first[i]);
                for (int l = 0; l < list.size(); l++) {
                    int j  = list.get(l);
                    int a = i / n;
                    int b = i % n;
                    int c = j >> 16;
                    int d = j & 0XFFFF;
                    if (a != c && a != d && b != c && b != d) {
                        printLine((a + 1) + " " + (b + 1) + " " + (c + 1) + " " + (d + 1));
                        return;
                    }
                }
            }
        }
        printLine("IMPOSSIBLE");
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

