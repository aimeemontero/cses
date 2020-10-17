import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CreatingStringsI implements AutoCloseable {
    public static void main(String[] args) {
        try(
                CreatingStringsI creatingStringsI = new CreatingStringsI();
        ) {
            creatingStringsI.solve();
        }
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
    PrintWriter output = new PrintWriter(System.out);;
    StringTokenizer tokens;

    String str;
    int[] characters;
    List<String> list = new ArrayList<String>();
    public void solve () {
        str = nextString();
        characters = new int[26];

        for (int i = 0; i < str.length(); i++) {
            char value = str.charAt(i);
            characters[value - 'a']++;
        }

        getString(0, "");
        printLine(list.size());
        for (int i = 0; i < list.size(); i++) {
            printLine(list.get(i));
        }
    }

    public void getString(int pos, String builder) {
        if (pos == str.length()) {
            list.add(builder);
        }

        for (int i = 0; i < characters.length; i++) {
            if(characters[i] > 0) {
                characters[i]--;
                getString(pos+1, builder + (char)(i + 'a'));
                characters[i]++;
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

