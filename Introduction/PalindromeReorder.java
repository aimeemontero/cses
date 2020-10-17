import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.util.StringTokenizer;

public class PalindromeReorder implements AutoCloseable {
    public static void main(String[] args) {
        try(
                PalindromeReorder palindromeReorder = new PalindromeReorder();  //Initialize class element
        ) {
            palindromeReorder.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    StringBuilder builder;
    public StringBuilder getString(int count, char character) {
        if (count == 1) {
            StringBuilder b = new StringBuilder();
            b.append(character);
            return b;
        }
        StringBuilder tmp;
        if (count % 2 == 0) {
            tmp = getString(count / 2, character);
            return tmp.append(tmp);
        } else {
            tmp = getString((count-1)/2, character);
            return tmp.append(tmp).append(character);
        }
    }


    public void concatElements(int count, char character) {
        StringBuilder tmp = getString(count/2, character);

        builder.insert(0, tmp);
        builder.append(tmp);
    }

    public void solve() {
        String str = nextString();

        int[] occurrences = new int[26];
        for (int i = 0; i < str.length(); i++) {
            char value = str.charAt(i);
            occurrences[value-'A']++;
        }
        builder = new StringBuilder();
        for (int i = 0; i < occurrences.length; i++) {
            if (occurrences[i] % 2 != 0) {
                builder.append((char)('A' + i));
                occurrences[i]--;
                break;
            }
        }
        for (int i = 0; i < occurrences.length; i++) {
            if (occurrences[i] > 0 && occurrences[i] % 2 == 0) {
                concatElements(occurrences[i], (char)('A' + i));
            } else if (occurrences[i] > 0){
                printLine("NO SOLUTION");
                return;
            }
        }
        printLine(builder.toString());
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


