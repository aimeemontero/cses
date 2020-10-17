import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class Apartments implements AutoCloseable {
    public static void main(String[] args) {
        try(
                Apartments apartments = new Apartments();
        ) {
            apartments.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    int[] applicants;
    int[] aps;
    int k;
    int m;
    int n;
    public void solve() {
        n = nextInt();
       m = nextInt();
       k = nextInt();

       applicants =  new int[n];
       aps =  new int[m];
       for (int i = 0; i < n; i++) {
            applicants[i] = nextInt();
       }
        for (int i = 0; i < m; i++) {
            aps[i] = nextInt();
        }

        quickSort(0, n-1, applicants);
        quickSort(0, m -1, aps);

        int aplcIndex =  0;
        int apIndex = 0;
        long count = 0;
        while (aplcIndex < n && apIndex < m) {
            if (valid(applicants[aplcIndex], aps[apIndex])) {
                aplcIndex++;
                apIndex++;
                count++;
            } else if (applicants[aplcIndex] > aps[apIndex]) {
                apIndex++;
            }  else {
                aplcIndex++;
            }
        }
        printLine(count);
    }

    public boolean valid(int applicant, int apt) {
        return  (Math.abs(applicant - apt) <= k);
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

    public void quickSort(int ini, int end, int[] values) {
        if (ini >= end)
            return;

        int part = partition(ini, end, values);

        quickSort(ini, part-1, values);
        quickSort(part+1, end, values);
    }

    private void swap(int ini, int end, int[] letters) {
        int tmp = letters[ini];
        letters[ini] = letters[end];
        letters[end] = tmp;
    }

}

