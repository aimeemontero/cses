import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class TaskAndDeadline implements AutoCloseable {
    public static void main(String[] args) {
        try(
                TaskAndDeadline factoryMachines = new TaskAndDeadline();//Initialize class element
        ) {
            factoryMachines.solve();
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

    int[] task;
    int[] deadline;
    int[] index;
    int k;
    public void solve() {
        int n = nextInt();

        task = new int[n];
        deadline = new int[n];
        index = new int[n];
        for (int i = 0; i < task.length; i++) {
            task[i] = nextInt();
            deadline[i] = nextInt();
            index[i] = i;
        }

        new IntList(index).sort(Comparator.comparingInt(i -> task[i]));

        long sum = 0;
        long res = 0;
        for (int i = 0; i < index.length; i++) {
            sum += task[index[i]];
            res += deadline[index[i]] - sum;
        }
        printLine(res);
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

