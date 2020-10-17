import java.io.*;
import java.util.*;

public final class RoomAllocation implements AutoCloseable {
    public static void main(String[] args) {
        try(
                RoomAllocation roomAllocation = new RoomAllocation();//Initialize class element
        ) {
            roomAllocation.solve();
        }
    }

    InputReader reader = new InputReader(new BufferedInputStream(System.in));
    PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    StringTokenizer tokens;

    static final class Interval implements Comparable<Interval> {
        final int id;
        final int pos;
        final boolean type;
        public Interval(int id, int pos, boolean  type) {
            this.id = id;
            this.pos =  pos;
            this.type = type;
        }
        @Override
        public int compareTo(Interval o) {
            if (pos != o.pos) {
                return Integer.compare(pos, o.pos);
            }
            return Boolean.compare(o.type, type); // reverse
        }
    }

    Random random = new Random(123);
    public int partition(int l, int h, Interval[] values) {
        int pivot = l + random.nextInt(h - l + 1);
        swap(l,  pivot, values);
        int ini = l;
        for (int i = ini+1; i <= h; i++) {
            if (values[i].compareTo(values[l]) < 0) {
                ini++;
                swap(ini, i, values);
            }
        }

        swap(ini, l, values);
        return ini;
    }

    public void quickSort(int ini, int end, Interval[] values) {
        if (end - ini + 1 < 72) {
            // insertion sort
            for (int i = ini, j = i; i < end; j = ++i) {
                Interval ai = values[i + 1];
                while (ai.compareTo(values[j]) < 0) {
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

    private void swap(int ini, int end, Interval[] letters) {
        Interval tmp = letters[ini];
        letters[ini] = letters[end];
        letters[end] = tmp;
    }


    public void solve() {
        //long ticks = System.currentTimeMillis();
        int n = nextInt();

        Interval[] intervals = new Interval[2*n];
        for (int i = 0; i < n; i++) {
            int b = nextInt();
            int e = nextInt();
            intervals[i*2] =  new Interval(i, b, true);
            intervals[i*2 + 1] = new Interval(i, e, false);
        }
        //printLine("Time READ " + (System.currentTimeMillis() - ticks));
        //long beforeSort = System.currentTimeMillis();
        //Arrays.sort(intervals);
        quickSort(0, intervals.length - 1, intervals);
        //printLine("Time sort " + (System.currentTimeMillis() - beforeSort));
        //if (true) return ;

        int available = 0;
        int rooms = 0;
        LinkedList<Integer> list = new LinkedList<>();

        int[] taken = new int[n];
        for (int i = 0; i < intervals.length; i++) {
            Interval next = intervals[i];
            if (next.type && available == 0) {
                taken[next.id] = rooms;
                rooms++;
            } else if (next.type && available > 0) {
                available--;
                taken[next.id] = list.removeFirst();
            } else {
                available++;
                list.addFirst(taken[next.id]);
            }
        }
        printLine(rooms);
        for (int i = 0; i < taken.length; i++) {
            print((taken[i]+1) + " ");
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


    private int nextInt() {
        return reader.readInt();
    }


    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[16 << 10];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return isWhitespace(c);
        }

        public static boolean isWhitespace(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public double readDouble() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.') {
                if (c == 'e' || c == 'E') {
                    return res * Math.pow(10, readInt());
                }
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E') {
                        return res * Math.pow(10, readInt());
                    }
                    if (c < '0' || c > '9') {
                        throw new InputMismatchException();
                    }
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }
    }
    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);

    }
}

