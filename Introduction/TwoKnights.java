import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TwoKnights implements AutoCloseable {
    public static void main(String[] args) {
        try(TwoKnights algo = new TwoKnights()) {
            algo.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    public long getPlaces(int dim, int rows,  int cols) {
        int[] dx = {-2, -2, 2, 2, -1, -1, 1, 1};
        int[] dy = {-1, 1, 1, -1, 2, -2, 2, -2};

        long totalPlaces = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                long invalid = 0;
                for (int k = 0; k < dx.length; k++) {
                    int nextCol =  j + dy[k];
                    int nextRow =  i + dx[k];
                    if  (nextRow >= 0 && nextCol < dim && nextCol >= 0 && nextRow < dim) {
                        invalid++;
                    }
                }
                totalPlaces += (dim*dim)-invalid-1;
            }
        }
        return totalPlaces;
    }

    public long getPlaces2(int dim, int rows,  int cols) {
        int invalid = 3;
        invalid += 8;
        invalid += 5;
        invalid += (rows - 2) * 5;
        invalid += (rows - 2) * 7;

        invalid += (cols - 2) * 5;
        invalid += (cols - 2) * 7;


        long insideSquare = (rows-2) * (cols-2);
        long calculated = rows * cols - insideSquare;
        long totalPlaces = calculated * (dim*dim) -  invalid;
        totalPlaces  += insideSquare * (dim*dim-9);
        return totalPlaces;
    }

    public void solve() {
        int n = nextInt();

        int[] res = {-1,  0, 6,28,96};
        for (int i = 1; i <= n; i++) {
            if (i < res.length) {
                printLine(res[i]);
                continue;
            }

            int row = i;
            int col = i;
            long result = 0;
            if (i % 2 == 0) {
                row = i / 2;
                col = i / 2;
                result = getPlaces2(i, row, col);
                result = result * 2;
            } else {
                row =  i / 2;
                col = i / 2 + 1;
                result = getPlaces2(i, row, col);
                result = (result*4 + ((i*i)-9)) / 2;
            }
            printLine(result);
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



