import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class ArrayDivision implements AutoCloseable {
    public static void main(String[] args) {
        try(
                ArrayDivision arrayDivision = new ArrayDivision();//Initialize class element
        ) {
            arrayDivision.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    int[] p;
    int k;
    public int getKForSum(long sum) {
        LinkedList<Integer> t = new LinkedList<>();
        t.get(8);

        int res =  0;
        long prevSum = 0;
        for (int i = 0; i < p.length; i++) {
            if (prevSum + p[i] <= sum) {
                prevSum += p[i];
            } else {
                res++;
                prevSum = p[i];
            }
        }

        return res+1;
    }

    long bs(long ini, long end) {
        long l = ini;
        long h = end;

        while(l <= h) {
            long mid = (l + h) / 2;
            int minK = getKForSum(mid);

            if (minK <= k) {
                h = mid - 1;

            } else {
                l = mid + 1;
            }
        }
        return h + 1;
    }

    public void solve() {
        int n = nextInt();
        k = nextInt();
        
        p = new int[n];
        long sum = 0;
        int max = 0;
        for (int i = 0; i < p.length; i++) {
            p[i] = nextInt();
            sum += p[i];
            if (p[i] > max)
                max = p[i];
        }
        long rest = bs(max, sum);
        printLine(rest);

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

