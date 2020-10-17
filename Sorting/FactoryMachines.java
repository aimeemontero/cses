import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class FactoryMachines implements AutoCloseable {
    public static void main(String[] args) {
        try(
                FactoryMachines factoryMachines = new FactoryMachines();//Initialize class element
        ) {
            factoryMachines.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    int[] p;
    int k;
    public void solve() {
        int n = nextInt();
        k = nextInt();

        p = new int[n];
        long max = 0;
        for (int i = 0; i < p.length; i++) {
            p[i] = nextInt();
            if (p[i] > max) {
                max = p[i];
            }
        }

        long maxtime = ((k + p.length - 1) / p.length) * max;

        long res = bs(maxtime);
        printLine(res);
    }

    public boolean check (long time) {
        long sum = 0;
        for (int i = 0; i < p.length; i++) {
            sum += time / p[i];
            if (sum >= k)
                return true;
        }
        return false;
    }


    public long bs(long t) {
        long l = 0;
        long h = t;

        while (l <= h) {
            long mid = (l + h) / 2;
            if (!check(mid)) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return h + 1;
    }

    Random random = new Random(123);
    public int partition(int l, int h, int[] values) {
        int pivot = l + random.nextInt(h - l + 1);
        swap(l,  pivot, values);
        int ini = l;
        for (int i = ini+1; i <= h; i++) {
            if (values[i] < values[l]) {
                ini++;
                swap(ini, i, values);
            }
        }

        swap(ini, l, values);
        return ini;
    }

    private void swap(int ini, int end, int[] letters) {
        int tmp = letters[ini];
        letters[ini] = letters[end];
        letters[end] = tmp;
    }

    public void quickSort(int ini, int end, int[] values) {
        if (end - ini + 1 < 72) {
            // insertion sort
            for (int i = ini, j = i; i < end; j = ++i) {
                int ai = values[i + 1];
                while (ai < values[j]) {
                    values[j + 1] = values[j];
                    if (j-- == ini) {
                        break;
                    }
                }
                values[j + 1] = ai;
            }
            return;
        }
        int part = partition(ini, end, values);
        quickSort(ini, part-1, values);
        quickSort(part+1, end, values);
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

