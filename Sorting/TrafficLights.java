import java.io.*;
import java.util.InputMismatchException;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class TrafficLights implements AutoCloseable {
    public static void main(String[] args) {
        try(
                TrafficLights  trafficLights = new TrafficLights();//Initialize class element
        ) {
            trafficLights.solve();
        }
    }

    InputReader reader = new InputReader(new BufferedInputStream(System.in));
    PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    StringTokenizer tokens;

    public void removeElement(TreeMap<Integer, Integer> map, int element) {
        int prevDistanceCount = map.get(element);
        if ( prevDistanceCount <= 1)
            map.remove(element);
        else
            map.put(element, prevDistanceCount-1);
    }

    public void addElement(TreeMap<Integer, Integer> map, int element){
        int distCont = map.getOrDefault(element, 0);
        map.put(element, distCont+1);
    }

    public void solve() {
        int x =nextInt();
        int n =nextInt();

        TreeSet<Integer> lights = new TreeSet<>();
        TreeMap<Integer, Integer> distances = new TreeMap<>();

        lights.add(0);
        lights.add(x);
        distances.put(x,  1);

        for (int i = 0; i < n; i++) {
            int light = nextInt();

            int prev = lights.floor(light);
            int next = lights.ceiling(light);
            lights.add(light);

            int prevDistance = next - prev;
            removeElement(distances, prevDistance);

            addElement(distances, next-light);
            addElement(distances, light-prev);

            print(distances.lastKey() + " ");
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

