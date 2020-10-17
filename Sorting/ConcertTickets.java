import java.io.*;
import java.util.*;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;

public final class ConcertTickets implements AutoCloseable {
    public static void main(String[] args) {
        try(
                ConcertTickets concertTickets = new ConcertTickets();
        ) {
            concertTickets.solve();
        }
    }

    InputReader reader = new InputReader(System.in);
    PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    _Ez_Int__Int_TreeMap map;

    public void solve() {
        int n = nextInt();
        int m = nextInt();
        map = new _Ez_Int__Int_TreeMap();
        for (int i = 0; i < n; i++) {
            int key= nextInt();
            int value = map.get(key);
            if (map.returnedNull()) {
                map.put(key, 1);
            } else {
                map.put(key, value  + 1);
            }
        }
        for (int i = 0; i < m; i++) {
            int customer = nextInt();
            _Ez_Int__Int_Pair entry = map.floorEntry(customer);
            if (!map.returnedNull()) {
                int key = entry.first;
                int value = entry.second;
                output.println(key);
                if (value > 1) {
                    map.put(key, value - 1);
                } else {
                    map.remove(key);
                }
            } else {
                output.println(-1);
            }
        }
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
        private InputReader.SpaceCharFilter filter;

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

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }

    static class _Ez_Int__Int_TreeMap implements _Ez_Int__Int_SortedMap {
        private static final int DEFAULT_CAPACITY = 10;
        private static final double ENLARGE_SCALE = 2.0;
        private static final int HASHCODE_INITIAL_VALUE = 0x811c9dc5;
        private static final int HASHCODE_MULTIPLIER = 0x01000193;

        private static final boolean BLACK = false;
        private static final boolean RED = true;
        private static final int NULL = 0;
        private static final /*C1*/int/*C1*/ DEFAULT_NULL_KEY = (new /*C1*/int/*C1*/[1])[0];
        private static final /*T2*/int/*T2*/ DEFAULT_NULL_VALUE = (new /*T2*/int/*T2*/[1])[0];

        // Arrays are 1-indexed. Index 0 is a null node.
        private /*C1*/int/*C1*/[] keys;
        private /*T2*/int/*T2*/[] values;
        private int[] left;
        private int[] right;
        private int[] p;
        private boolean[] color;

        private int size;
        private int root;
        private boolean returnedNull;

        public _Ez_Int__Int_TreeMap() {
            this(DEFAULT_CAPACITY);
        }

        public _Ez_Int__Int_TreeMap(int capacity) {
            if (capacity < 0) {
                throw new IllegalArgumentException("Capacity must be non-negative");
            }
            capacity++;
            keys = new /*C1*/int/*C1*/[capacity];
            values = new /*T2*/int/*T2*/[capacity];
            left = new int[capacity];
            right = new int[capacity];
            p = new int[capacity];
            color = new boolean[capacity];
            color[NULL] = BLACK;
            size = 0;
            root = NULL;
            returnedNull = false;
        }

        public _Ez_Int__Int_TreeMap(_Ez_Int__Int_Map map) {
            this(map.size());
            for (_Ez_Int__Int_MapIterator it = map.iterator(); it.hasNext(); it.next()) {
                put(it.getKey(), it.getValue());
            }
        }

        public _Ez_Int__Int_TreeMap(Map</*WC1*/Integer/*WC1*/, /*W2*/Integer/*W2*/> javaMap) {
            this(javaMap.size());
            for (Map.Entry</*WC1*/Integer/*WC1*/, /*W2*/Integer/*W2*/> e : javaMap.entrySet()) {
                put(e.getKey(), e.getValue());
            }
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public boolean containsKey(/*C1*/int/*C1*/ key) {
            int x = root;
            while (x != NULL) {
                if (key < keys[x]) {
                    x = left[x];
                } else if (key > keys[x]) {
                    x = right[x];
                } else {
                    return true;
                }
            }
            return false;
        }

        @Override
        public /*T2*/int/*T2*/ get(/*C1*/int/*C1*/ key) {
            int x = root;
            while (x != NULL) {
                if (key < keys[x]) {
                    x = left[x];
                } else if (key > keys[x]) {
                    x = right[x];
                } else {
                    returnedNull = false;
                    return values[x];
                }
            }
            returnedNull = true;
            return DEFAULT_NULL_VALUE;
        }

        @Override
        public /*T2*/int/*T2*/ put(/*C1*/int/*C1*/ key, /*T2*/int/*T2*/ value) {
            int y = NULL;
            int x = root;
            while (x != NULL) {
                //noinspection SuspiciousNameCombination
                y = x;
                if (key < keys[x]) {
                    x = left[x];
                } else if (key > keys[x]) {
                    x = right[x];
                } else {
                    final /*T2*/int/*T2*/ oldValue = values[x];
                    values[x] = value;
                    returnedNull = false;
                    return oldValue;
                }
            }
            if (size == color.length - 1) {
                enlarge();
            }
            int z = ++size;
            keys[z] = key;
            values[z] = value;
            p[z] = y;
            if (y == NULL) {
                root = z;
            } else {
                if (key < keys[y]) {
                    left[y] = z;
                } else {
                    right[y] = z;
                }
            }
            left[z] = NULL;
            right[z] = NULL;
            color[z] = RED;
            fixAfterAdd(z);
            returnedNull = true;
            return DEFAULT_NULL_VALUE;
        }

        @Override
        public /*T2*/int/*T2*/ remove(/*C1*/int/*C1*/ key) {
            int z = root;
            while (z != NULL) {
                if (key < keys[z]) {
                    z = left[z];
                } else if (key > keys[z]) {
                    z = right[z];
                } else {
                    final /*T2*/int/*T2*/ removedValue = values[z];
                    removeNode(z);
                    returnedNull = false;
                    return removedValue;
                }
            }
            returnedNull = true;
            return DEFAULT_NULL_VALUE;
        }

        private void removeNode(int z) {
            int y = (left[z] == NULL || right[z] == NULL) ? z : successorNode(z);
            int x = (left[y] != NULL) ? left[y] : right[y];
            p[x] = p[y];
            if (p[y] == NULL) {
                root = x;
            } else {
                if (y == left[p[y]]) {
                    left[p[y]] = x;
                } else {
                    right[p[y]] = x;
                }
            }
            if (y != z) {
                keys[z] = keys[y];
                values[z] = values[y];
            }
            //noinspection PointlessBooleanExpression
            if (color[y] == BLACK) {
                fixAfterRemove(x);
            }
            // Swap with last
            if (y != size) {
                // copy fields
                keys[y] = keys[size];
                values[y] = values[size];
                left[y] = left[size];
                right[y] = right[size];
                p[y] = p[size];
                color[y] = color[size];
                // fix the children's parents
                p[left[size]] = y;
                p[right[size]] = y;
                // fix one of the parent's children
                if (left[p[size]] == size) {
                    left[p[size]] = y;
                } else {
                    right[p[size]] = y;
                }
                // fix root
                if (root == size) {
                    root = y;
                }
            }
            size--;
        }

        private int successorNode(int x) {
            if (right[x] != NULL) {
                x = right[x];
                while (left[x] != NULL) {
                    x = left[x];
                }
                return x;
            } else {
                int y = p[x];
                while (y != NULL && x == right[y]) {
                    //noinspection SuspiciousNameCombination
                    x = y;
                    y = p[y];
                }
                return y;
            }
        }

        @SuppressWarnings("PointlessBooleanExpression")
        private void fixAfterAdd(int z) {
            while (color[p[z]] == RED) {
                if (p[z] == left[p[p[z]]]) {
                    int y = right[p[p[z]]];
                    if (color[y] == RED) {
                        color[p[z]] = BLACK;
                        color[y] = BLACK;
                        color[p[p[z]]] = RED;
                        z = p[p[z]];
                    } else {
                        if (z == right[p[z]]) {
                            z = p[z];
                            rotateLeft(z);
                        }
                        color[p[z]] = BLACK;
                        color[p[p[z]]] = RED;
                        rotateRight(p[p[z]]);
                    }
                } else {
                    int y = left[p[p[z]]];
                    if (color[y] == RED) {
                        color[p[z]] = BLACK;
                        color[y] = BLACK;
                        color[p[p[z]]] = RED;
                        z = p[p[z]];
                    } else {
                        if (z == left[p[z]]) {
                            z = p[z];
                            rotateRight(z);
                        }
                        color[p[z]] = BLACK;
                        color[p[p[z]]] = RED;
                        rotateLeft(p[p[z]]);
                    }
                }
            }
            color[root] = BLACK;
        }

        @SuppressWarnings("PointlessBooleanExpression")
        private void fixAfterRemove(int x) {
            while (x != root && color[x] == BLACK) {
                if (x == left[p[x]]) {
                    int w = right[p[x]];
                    if (color[w] == RED) {
                        color[w] = BLACK;
                        color[p[x]] = RED;
                        rotateLeft(p[x]);
                        w = right[p[x]];
                    }
                    if (color[left[w]] == BLACK && color[right[w]] == BLACK) {
                        color[w] = RED;
                        x = p[x];
                    } else {
                        if (color[right[w]] == BLACK) {
                            color[left[w]] = BLACK;
                            color[w] = RED;
                            rotateRight(w);
                            w = right[p[x]];
                        }
                        color[w] = color[p[x]];
                        color[p[x]] = BLACK;
                        color[right[w]] = BLACK;
                        rotateLeft(p[x]);
                        x = root;
                    }
                } else {
                    int w = left[p[x]];
                    if (color[w] == RED) {
                        color[w] = BLACK;
                        color[p[x]] = RED;
                        rotateRight(p[x]);
                        w = left[p[x]];
                    }
                    if (color[left[w]] == BLACK && color[right[w]] == BLACK) {
                        color[w] = RED;
                        x = p[x];
                    } else {
                        if (color[left[w]] == BLACK) {
                            color[right[w]] = BLACK;
                            color[w] = RED;
                            rotateLeft(w);
                            w = left[p[x]];
                        }
                        color[w] = color[p[x]];
                        color[p[x]] = BLACK;
                        color[left[w]] = BLACK;
                        rotateRight(p[x]);
                        x = root;
                    }
                }
            }
            color[x] = BLACK;
        }

        private void rotateLeft(int x) {
            int y = right[x];
            right[x] = left[y];
            if (left[y] != NULL) {
                p[left[y]] = x;
            }
            p[y] = p[x];
            if (p[x] == NULL) {
                root = y;
            } else {
                if (x == left[p[x]]) {
                    left[p[x]] = y;
                } else {
                    right[p[x]] = y;
                }
            }
            left[y] = x;
            p[x] = y;
        }

        private void rotateRight(int x) {
            int y = left[x];
            left[x] = right[y];
            if (right[y] != NULL) {
                p[right[y]] = x;
            }
            p[y] = p[x];
            if (p[x] == NULL) {
                root = y;
            } else {
                if (x == right[p[x]]) {
                    right[p[x]] = y;
                } else {
                    left[p[x]] = y;
                }
            }
            right[y] = x;
            p[x] = y;
        }

        @Override
        public boolean returnedNull() {
            return returnedNull;
        }

        @Override
        public void clear() {
            color[NULL] = BLACK;
            size = 0;
            root = NULL;
        }

        @Override
        public /*C1*/int/*C1*/[] keys() {
            /*C1*/int/*C1*/[] result = new /*C1*/int/*C1*/[size];
            for (int i = 0, x = firstNode(); x != NULL; x = successorNode(x), i++) {
                result[i] = keys[x];
            }
            return result;
        }

        @Override
        public /*T2*/int/*T2*/[] values() {
            /*T2*/int/*T2*/[] result = new /*T2*/int/*T2*/[size];
            for (int i = 0, x = firstNode(); x != NULL; x = successorNode(x), i++) {
                result[i] = values[x];
            }
            return result;
        }

        @Override
        public _Ez_Int__Int_MapIterator iterator() {
            return new _Ez_Int__Int_TreeMapIterator();
        }

        private void enlarge() {
            int newLength = Math.max(color.length + 1, (int) (color.length * ENLARGE_SCALE));
            keys = Arrays.copyOf(keys, newLength);
            values = Arrays.copyOf(values, newLength);
            left = Arrays.copyOf(left, newLength);
            right = Arrays.copyOf(right, newLength);
            p = Arrays.copyOf(p, newLength);
            color = Arrays.copyOf(color, newLength);
        }

        private int firstNode() {
            int x = root;
            while (left[x] != NULL) {
                x = left[x];
            }
            return x;
        }

        private int lastNode() {
            int x = root;
            while (right[x] != NULL) {
                x = right[x];
            }
            return x;
        }

        @Override
        public /*C1*/int/*C1*/ getFirstKey() {
            if (root == NULL) {
                returnedNull = true;
                return DEFAULT_NULL_KEY;
            }
            final int x = firstNode();
            returnedNull = false;
            return keys[x];
        }

        @Override
        public /*C1*/int/*C1*/ getLastKey() {
            if (root == NULL) {
                returnedNull = true;
                return DEFAULT_NULL_KEY;
            }
            final int x = lastNode();
            returnedNull = false;
            return keys[x];
        }

        @Override
        public _Ez_Int__Int_Pair getFirstEntry() {
            if (root == NULL) {
                returnedNull = true;
                return null;
            }
            final int x = firstNode();
            returnedNull = false;
            return new _Ez_Int__Int_Pair(keys[x], values[x]);
        }

        @Override
        public _Ez_Int__Int_Pair getLastEntry() {
            if (root == NULL) {
                returnedNull = true;
                return null;
            }
            final int x = lastNode();
            returnedNull = false;
            return new _Ez_Int__Int_Pair(keys[x], values[x]);
        }

        @Override
        public /*C1*/int/*C1*/ floorKey(/*C1*/int/*C1*/ key) {
            int x = root;
            while (x != NULL) {
                if (key > keys[x]) {
                    if (right[x] != NULL) {
                        x = right[x];
                    } else {
                        returnedNull = false;
                        return keys[x];
                    }
                } else if (key < keys[x]) {
                    if (left[x] != NULL) {
                        x = left[x];
                    } else {
                        int y = p[x];
                        while (y != NULL && x == left[y]) {
                            //noinspection SuspiciousNameCombination
                            x = y;
                            y = p[y];
                        }
                        if (y == NULL) {
                            returnedNull = true;
                            return DEFAULT_NULL_KEY;
                        } else {
                            returnedNull = false;
                            return keys[y];
                        }
                    }
                } else {
                    returnedNull = false;
                    return keys[x];
                }
            }
            returnedNull = true;
            return DEFAULT_NULL_KEY;
        }

        @Override
        public /*C1*/int/*C1*/ ceilingKey(/*C1*/int/*C1*/ key) {
            int x = root;
            while (x != NULL) {
                if (key < keys[x]) {
                    if (left[x] != NULL) {
                        x = left[x];
                    } else {
                        returnedNull = false;
                        return keys[x];
                    }
                } else if (key > keys[x]) {
                    if (right[x] != NULL) {
                        x = right[x];
                    } else {
                        int y = p[x];
                        while (y != NULL && x == right[y]) {
                            //noinspection SuspiciousNameCombination
                            x = y;
                            y = p[y];
                        }
                        if (y == NULL) {
                            returnedNull = true;
                            return DEFAULT_NULL_KEY;
                        } else {
                            returnedNull = false;
                            return keys[y];
                        }
                    }
                } else {
                    returnedNull = false;
                    return keys[x];
                }
            }
            returnedNull = true;
            return DEFAULT_NULL_KEY;
        }

        @Override
        public /*C1*/int/*C1*/ lowerKey(/*C1*/int/*C1*/ key) {
            int x = root;
            while (x != NULL) {
                if (key > keys[x]) {
                    if (right[x] != NULL) {
                        x = right[x];
                    } else {
                        returnedNull = false;
                        return keys[x];
                    }
                } else {
                    if (left[x] != NULL) {
                        x = left[x];
                    } else {
                        int y = p[x];
                        while (y != NULL && x == left[y]) {
                            //noinspection SuspiciousNameCombination
                            x = y;
                            y = p[y];
                        }
                        if (y == NULL) {
                            returnedNull = true;
                            return DEFAULT_NULL_KEY;
                        } else {
                            returnedNull = false;
                            return keys[y];
                        }
                    }
                }
            }
            returnedNull = true;
            return DEFAULT_NULL_KEY;
        }

        @Override
        public /*C1*/int/*C1*/ higherKey(/*C1*/int/*C1*/ key) {
            int x = root;
            while (x != NULL) {
                if (key < keys[x]) {
                    if (left[x] != NULL) {
                        x = left[x];
                    } else {
                        returnedNull = false;
                        return keys[x];
                    }
                } else {
                    if (right[x] != NULL) {
                        x = right[x];
                    } else {
                        int y = p[x];
                        while (y != NULL && x == right[y]) {
                            //noinspection SuspiciousNameCombination
                            x = y;
                            y = p[y];
                        }
                        if (y == NULL) {
                            returnedNull = true;
                            return DEFAULT_NULL_KEY;
                        } else {
                            returnedNull = false;
                            return keys[y];
                        }
                    }
                }
            }
            returnedNull = true;
            return DEFAULT_NULL_KEY;
        }

        @Override
        public _Ez_Int__Int_Pair floorEntry(/*C1*/int/*C1*/ key) {
            int x = root;
            while (x != NULL) {
                if (key > keys[x]) {
                    if (right[x] != NULL) {
                        x = right[x];
                    } else {
                        returnedNull = false;
                        return new _Ez_Int__Int_Pair(keys[x], values[x]);
                    }
                } else if (key < keys[x]) {
                    if (left[x] != NULL) {
                        x = left[x];
                    } else {
                        int y = p[x];
                        while (y != NULL && x == left[y]) {
                            //noinspection SuspiciousNameCombination
                            x = y;
                            y = p[y];
                        }
                        if (y == NULL) {
                            returnedNull = true;
                            return null;
                        } else {
                            returnedNull = false;
                            return new _Ez_Int__Int_Pair(keys[y], values[y]);
                        }
                    }
                } else {
                    returnedNull = false;
                    return new _Ez_Int__Int_Pair(keys[x], values[x]);
                }
            }
            returnedNull = true;
            return null;
        }

        @Override
        public _Ez_Int__Int_Pair ceilingEntry(/*C1*/int/*C1*/ key) {
            int x = root;
            while (x != NULL) {
                if (key < keys[x]) {
                    if (left[x] != NULL) {
                        x = left[x];
                    } else {
                        returnedNull = false;
                        return new _Ez_Int__Int_Pair(keys[x], values[x]);
                    }
                } else if (key > keys[x]) {
                    if (right[x] != NULL) {
                        x = right[x];
                    } else {
                        int y = p[x];
                        while (y != NULL && x == right[y]) {
                            //noinspection SuspiciousNameCombination
                            x = y;
                            y = p[y];
                        }
                        if (y == NULL) {
                            returnedNull = true;
                            return null;
                        } else {
                            returnedNull = false;
                            return new _Ez_Int__Int_Pair(keys[y], values[y]);
                        }
                    }
                } else {
                    returnedNull = false;
                    return new _Ez_Int__Int_Pair(keys[x], values[x]);
                }
            }
            returnedNull = true;
            return null;
        }

        @Override
        public _Ez_Int__Int_Pair lowerEntry(/*C1*/int/*C1*/ key) {
            int x = root;
            while (x != NULL) {
                if (key > keys[x]) {
                    if (right[x] != NULL) {
                        x = right[x];
                    } else {
                        returnedNull = false;
                        return new _Ez_Int__Int_Pair(keys[x], values[x]);
                    }
                } else {
                    if (left[x] != NULL) {
                        x = left[x];
                    } else {
                        int y = p[x];
                        while (y != NULL && x == left[y]) {
                            //noinspection SuspiciousNameCombination
                            x = y;
                            y = p[y];
                        }
                        if (y == NULL) {
                            returnedNull = true;
                            return null;
                        } else {
                            returnedNull = false;
                            return new _Ez_Int__Int_Pair(keys[y], values[y]);
                        }
                    }
                }
            }
            returnedNull = true;
            return null;
        }

        @Override
        public _Ez_Int__Int_Pair higherEntry(/*C1*/int/*C1*/ key) {
            int x = root;
            while (x != NULL) {
                if (key < keys[x]) {
                    if (left[x] != NULL) {
                        x = left[x];
                    } else {
                        returnedNull = false;
                        return new _Ez_Int__Int_Pair(keys[x], values[x]);
                    }
                } else {
                    if (right[x] != NULL) {
                        x = right[x];
                    } else {
                        int y = p[x];
                        while (y != NULL && x == right[y]) {
                            //noinspection SuspiciousNameCombination
                            x = y;
                            y = p[y];
                        }
                        if (y == NULL) {
                            returnedNull = true;
                            return null;
                        } else {
                            returnedNull = false;
                            return new _Ez_Int__Int_Pair(keys[y], values[y]);
                        }
                    }
                }
            }
            returnedNull = true;
            return null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            _Ez_Int__Int_TreeMap that = (_Ez_Int__Int_TreeMap) o;

            if (size != that.size) {
                return false;
            }
            for (int x = firstNode(), y = that.firstNode();
                 x != NULL;
                //noinspection SuspiciousNameCombination
                 x = successorNode(x), y = that.successorNode(y)
            ) {
                if (keys[x] != that.keys[y] || values[x] != that.values[y]) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = HASHCODE_INITIAL_VALUE;
            for (int x = firstNode(); x != NULL; x = successorNode(x)) {
                hash = (hash ^ PrimitiveHashCalculator.getHash(keys[x])) * HASHCODE_MULTIPLIER;
                hash = (hash ^ PrimitiveHashCalculator.getHash(values[x])) * HASHCODE_MULTIPLIER;
            }
            return hash;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('{');
            for (int x = firstNode(); x != NULL; x = successorNode(x)) {
                if (sb.length() > 1) {
                    sb.append(", ");
                }
                sb.append(keys[x]);
                sb.append('=');
                sb.append(values[x]);
            }
            sb.append('}');
            return sb.toString();
        }

        private class _Ez_Int__Int_TreeMapIterator implements _Ez_Int__Int_MapIterator {
            private int x;

            private _Ez_Int__Int_TreeMapIterator() {
                x = firstNode();
            }

            @Override
            public boolean hasNext() {
                return x != NULL;
            }

            @Override
            public /*C1*/int/*C1*/ getKey() {
                if (x == NULL) {
                    throw new NoSuchElementException("Iterator doesn't have more entries");
                }
                return keys[x];
            }

            @Override
            public /*T2*/int/*T2*/ getValue() {
                if (x == NULL) {
                    throw new NoSuchElementException("Iterator doesn't have more entries");
                }
                return values[x];
            }

            @Override
            public void next() {
                if (x == NULL) {
                    return;
                }
                x = successorNode(x);
            }
        }
    }

    /**
     * The interface for sorted maps.
     * <p>
     * Sorted map in EZ Collections is an analogue of {@link NavigableMap}. Besides standard map operations:
     * {@link #containsKey}, {@link #get}, {@link #put}, {@link #remove} it supports key ordering, so it's possible to
     * get first and last keys and entries, and to get lower / higher / floor / ceiling keys and entries for the given key.
     * See the javadocs of the methods to get more information.
     * @author Alexey Dergunov
     * @since 0.1.0
     */
    static interface _Ez_Int__Int_SortedMap extends _Ez_Int__Int_Map {
        // TODO null issues, e.g. when there is no higher key - consider returning special 'null value'

        /**
         * Returns the size of the map, i.e. the number of entries (key-value pairs) in it.
         * @return the size of the map
         */
        @Override
        int size();

        /**
         * Checks if the map contains at least one entry (key-value pair).
         * @return {@code true} if the map doesn't contain any entries, {@code false} otherwise
         */
        @Override
        boolean isEmpty();

        /**
         * Checks if the map contains the entry with the specified key.
         * @param key the key to be checked
         * @return {@code true} if the map contains the entry with the specified key, {@code false} otherwise
         */
        @Override
        boolean containsKey(/*C1*/int/*C1*/ key);

        /**
         * Returns the value for the specified key. If the map doesn't contain the entry with the specified key, this
         * method can return everything, but sets the special flag, you should check it immediately after using get() this
         * way:
         * <pre>
         * if (map.returnedNull()) {
         *     // The map doesn't contain the entry with the specified key.
         *     // Take it into account and don't use the value returned by get().
         * } else {
         *     // The call of get() was successful.
         *     // You can use the value returned by get().
         * }
         * </pre>
         * @param key the key of the entry
         * @return the value for the specified key
         * @see #returnedNull()
         */
        @Override
        /*T2*/int/*T2*/ get(/*C1*/int/*C1*/ key);

        /**
         * Puts the specified key-value pair into the map. That is, if the map already contains the entry for the specified
         * key, the previous value is overridden by the new one, and if it doesn't, the new entry is created. In the first
         * case, the previous value is returned, and in the second case it can return everything, but the special flag is
         * set. If you are going to use the returned value, you should check it immediately after using put() this way:
         * <pre>
         * if (map.returnedNull()) {
         *     // The map didn't contain the entry with the specified key. New entry was created.
         *     // Take it into account and don't use the value returned by put().
         * } else {
         *     // The map contained the entry with the specified key. The value was overridden.
         *     // You can use the previous value returned by put().
         * }
         * </pre>
         * @param key the key of the entry
         * @param value the value of the entry
         * @return the previous value for the specified key
         * @see #returnedNull()
         */
        @Override
        /*T2*/int/*T2*/ put(/*C1*/int/*C1*/ key, /*T2*/int/*T2*/ value);

        /**
         * Removes the entry with the specified key from the map. This method returns the value of the removed entry,
         * however, if the map doesn't contain the entry with the specified key, it can return everything, but sets the
         * special flag, you should check it immediately after using remove() this way:
         * <pre>
         * if (map.returnedNull()) {
         *     // The map doesn't contain the entry with the specified key. Nothing has been removed.
         *     // Take it into account and don't use the value returned by remove().
         * } else {
         *     // The call of remove() was successful.
         *     // You can use the value returned by remove().
         * }
         * </pre>
         * @param key the key of the entry to be removed
         * @return the value of the removed entry
         * @see #returnedNull()
         */
        @Override
        /*T2*/int/*T2*/ remove(/*C1*/int/*C1*/ key);

        /**
         * Checks if the last call of {@link #get}, {@link #put}, {@link #remove}, {@link #getFirstKey},
         * {@link #getLastKey}, {@link #floorKey}, {@link #ceilingKey}, {@link #lowerKey}, {@link #higherKey},
         * {@link #getFirstEntry}, {@link #getLastEntry}, {@link #floorEntry}, {@link #ceilingEntry}, {@link #lowerEntry}
         * or {@link #higherEntry} has returned 'null'. Some of these methods return primitive types, and as we can't
         * return {@code null} using the primitive types, they can return everything but set the special flag. You should
         * check the using this method. Other methods from the list return object types, so they are able to return
         * {@code null}. They set this flag too. See the javadocs of the listed methods for more detailed information.
         * <p>
         * In other words, this method returns {@code true} if and only if the map from the Java Collections Library
         * ({@link NavigableMap}) which contains the same entries returns {@code null} after calling the same
         * method.
         * @return {@code true} if the last call of one of the methods listed above has returned incorrect value (for the
         * methods that return primitive types) or has returned {@code null} itself (for the methods that return object
         * types), {@code false} if that call was successful and the returned value is correct and not null
         */
        @Override
        boolean returnedNull();

        /**
         * Removes all entries from the map.
         */
        @Override
        void clear();

        /**
         * Returns the array which contains all keys in this map. The array will be sorted in ascending order.
         * Additionally, two consecutive calls of {@code keys()} and {@link #values()} methods return two arrays related
         * to each other such that for every index {@code i} the entry {@code (keys[i], values[i])} is contained in the
         * map. This method always allocates new array, so you can modify it and the original map won't be changed, and
         * vice versa.
         * @return the array which contains all keys in this map
         */
        @Override
        /*C1*/int/*C1*/[] keys();

        /**
         * Returns the array which contains all values in this map. The array will be sorted in the ascending order of the
         * corresponding keys. That is, two consecutive calls of {@link #keys()} and {@code values()} methods return two
         * arrays related to each other such that for every index {@code i} the entry {@code (keys[i], values[i])} is
         * contained in the map. This method always allocates new array, so you can modify it and the original map won't be
         * changed, and vice versa.
         * @return the array which contains all values in this map
         */
        @Override
        /*T2*/int/*T2*/[] values();

        /**
         * Returns the iterator which can be used to go through the entries of this map. The iterator will return the
         * entries in ascending order of the keys.
         * @return the iterator to go through the entries of this map
         */
        @Override
        _Ez_Int__Int_MapIterator iterator();

        /**
         * Checks if this map is equal to other object. For equality the passed object should be of the same class as this
         * map and contain the same key-value pairs as this map.
         * @param object the object to be checked for equality
         * @return {@code true} if this map is equal to the specified object, {@code false} otherwise
         */
        @Override
        boolean equals(Object object);

        /**
         * Returns the hashcode of the map. If two maps are equal, their hashcodes are also equal. If the hashcodes of two
         * maps are different, these maps are also different. Please note that different maps can have equal hashcodes,
         * though the probability of this fact is low.
         * @return the hashcode of the map
         */
        @Override
        int hashCode();

        /**
         * Returns the human-readable string representation of the map.
         * @return the string representation of the map
         */
        @Override
        String toString();

        /**
         * Returns the smallest key in the map. If the map is empty, it can return everything, but sets the special flag,
         * you should check it immediately after using getFirstKey() this way:
         * <pre>
         * if (map.returnedNull()) {
         *     // The map is empty.
         *     // Take it into account and don't use the key returned by getFirstKey().
         * } else {
         *     // The call of getFirstKey() was successful.
         *     // You can use the key returned by getFirstKey().
         * }
         * </pre>
         * @return the smallest key, in the case the map isn't empty
         * @see #returnedNull()
         */
        /*C1*/int/*C1*/ getFirstKey();

        /**
         * Returns the largest key in the map. If the map is empty, it can return everything, but sets the special flag,
         * you should check it immediately after using getLastKey() this way:
         * <pre>
         * if (map.returnedNull()) {
         *     // The map is empty.
         *     // Take it into account and don't use the key returned by getLastKey().
         * } else {
         *     // The call of getLastKey() was successful.
         *     // You can use the key returned by getLastKey().
         * }
         * </pre>
         * @return the largest key, in the case the map isn't empty
         * @see #returnedNull()
         */
        /*C1*/int/*C1*/ getLastKey();

        /**
         * Returns the entry with the smallest key. The field {@code first} of the returned pair will contain the key, and
         * the field {@code second} will contain the value. If the map is empty, it returns {@code null} and sets the
         * special flag which can be checked by calling {@link #returnedNull()} immediately after using this method.
         * @return the entry with the smallest key, or {@code null} if the map is empty
         * @see #returnedNull()
         */
        _Ez_Int__Int_Pair getFirstEntry();

        /**
         * Returns the entry with the largest key. The field {@code first} of the returned pair will contain the key, and
         * the field {@code second} will contain the value. If the map is empty, it returns {@code null} and sets the
         * special flag which can be checked by calling {@link #returnedNull()} immediately after using this method.
         * @return the entry with the largest key, or {@code null} if the map is empty
         * @see #returnedNull()
         */
        _Ez_Int__Int_Pair getLastEntry();

        /**
         * Returns the greatest key in the map that is less than or equal to the specified one. If the map doesn't contain
         * such a key, this method can return everything, but sets the special flag, you should check it immediately after
         * using floorKey() this way:
         * <pre>
         * if (map.returnedNull()) {
         *     // The map doesn't contain any key that is less than or equal to the specified one.
         *     // Take it into account and don't use the key returned by floorKey().
         * } else {
         *     // The call of floorKey() was successful.
         *     // You can use the key returned by floorKey().
         * }
         * </pre>
         * @param key the key to compare
         * @return the greatest key in the map that is less than or equal to the specified one
         * @see #returnedNull()
         */
        /*C1*/int/*C1*/ floorKey(/*C1*/int/*C1*/ key);

        /**
         * Returns the least key in the map that is greater than or equal to the specified one. If the map doesn't contain
         * such a key, this method can return everything, but sets the special flag, you should check it immediately after
         * using ceilingKey() this way:
         * <pre>
         * if (map.returnedNull()) {
         *     // The map doesn't contain any key that is greater than or equal to the specified one.
         *     // Take it into account and don't use the key returned by ceilingKey().
         * } else {
         *     // The call of ceilingKey() was successful.
         *     // You can use the key returned by ceilingKey().
         * }
         * </pre>
         * @param key the key to compare
         * @return the least key in the map that is greater than or equal to the specified one
         * @see #returnedNull()
         */
        /*C1*/int/*C1*/ ceilingKey(/*C1*/int/*C1*/ key);

        /**
         * Returns the greatest key in the map that is strictly less than the specified one. If the map doesn't contain
         * such a key, this method can return everything, but sets the special flag, you should check it immediately after
         * using lowerKey() this way:
         * <pre>
         * if (map.returnedNull()) {
         *     // The map doesn't contain any key that is strictly less than the specified one.
         *     // Take it into account and don't use the key returned by lowerKey().
         * } else {
         *     // The call of lowerKey() was successful.
         *     // You can use the key returned by lowerKey().
         * }
         * </pre>
         * @param key the key to compare
         * @return the greatest key in the map that is strictly less than the specified one
         * @see #returnedNull()
         */
        /*C1*/int/*C1*/ lowerKey(/*C1*/int/*C1*/ key);

        /**
         * Returns the least key in the map that is strictly greater than the specified one. If the map doesn't contain
         * such a key, this method can return everything, but sets the special flag, you should check it immediately after
         * using higherKey() this way:
         * <pre>
         * if (map.returnedNull()) {
         *     // The map doesn't contain any key that is strictly greater than the specified one.
         *     // Take it into account and don't use the key returned by higherKey().
         * } else {
         *     // The call of higherKey() was successful.
         *     // You can use the key returned by higherKey().
         * }
         * </pre>
         * @param key the key to compare
         * @return the least key in the map that is strictly greater than the specified one
         * @see #returnedNull()
         */
        /*C1*/int/*C1*/ higherKey(/*C1*/int/*C1*/ key);

        /**
         * Returns the entry with the greatest key that is less than or equal to the specified one. The field {@code first}
         * of the returned pair will contain the key, and the field {@code second} will contain the value. If the map
         * doesn't contain such a key, it returns {@code null} and sets the special flag which can be checked by calling
         * {@link #returnedNull()} immediately after using this method.
         * @return the entry with the greatest key that is less than or equal to the specified one, or {@code null} if the
         * map is empty
         * @see #returnedNull()
         */
        _Ez_Int__Int_Pair floorEntry(/*C1*/int/*C1*/ key);

        /**
         * Returns the entry with the least key that is greater than or equal to the specified one. The field {@code first}
         * of the returned pair will contain the key, and the field {@code second} will contain the value. If the map
         * doesn't contain such a key, it returns {@code null} and sets the special flag which can be checked by calling
         * {@link #returnedNull()} immediately after using this method.
         * @return the entry with the least key that is greater than or equal to the specified one, or {@code null} if the
         * map is empty
         * @see #returnedNull()
         */
        _Ez_Int__Int_Pair ceilingEntry(/*C1*/int/*C1*/ key);

        /**
         * Returns the entry with the greatest key that is strictly less than the specified one. The field {@code first}
         * of the returned pair will contain the key, and the field {@code second} will contain the value. If the map
         * doesn't contain such a key, it returns {@code null} and sets the special flag which can be checked by calling
         * {@link #returnedNull()} immediately after using this method.
         * @return the entry with the greatest key that is strictly less than the specified one, or {@code null} if the
         * map is empty
         * @see #returnedNull()
         */
        _Ez_Int__Int_Pair lowerEntry(/*C1*/int/*C1*/ key);

        /**
         * Returns the entry with the least key that is strictly greater than the specified one. The field {@code first}
         * of the returned pair will contain the key, and the field {@code second} will contain the value. If the map
         * doesn't contain such a key, it returns {@code null} and sets the special flag which can be checked by calling
         * {@link #returnedNull()} immediately after using this method.
         * @return the entry with the least key that is strictly greater than the specified one, or {@code null} if the
         * map is empty
         * @see #returnedNull()
         */
        _Ez_Int__Int_Pair higherEntry(/*C1*/int/*C1*/ key);
    }

    /**
     * The root interface for map iterators.
     * <p>
     * Map iterators are used to go through a map and get its keys and values. The analogues in Java Collections are sets
     * {@link Map#keySet()} and {@link Map#entrySet()} and collection {@link Map#values()}
     * which can give you iterators. The only difference is that iterators in EZ Collections are read-only and cannot
     * remove entries.
     * @author Alexey Dergunov
     * @since 0.1.0
     */
    static interface _Ez_Int__Int_MapIterator {
        /**
         * Checks if the iterator has more available entries.
         * @return {@code true} if the iterator has more entries, {@code false} otherwise
         */
        boolean hasNext();

        /**
         * Returns the key of the entry at which the iterator currently points.
         * @return the key of the entry at which the iterator currently points
         * @throws NoSuchElementException if the iterator doesn't have more entries
         */
        /*T1*/int/*T1*/ getKey();

        /**
         * Returns the value of the entry at which the iterator currently points.
         * @return the value of the entry at which the iterator currently points
         * @throws NoSuchElementException if the iterator doesn't have more entries
         */
        /*T2*/int/*T2*/ getValue();

        /**
         * Moves the iterator to the next entry in the map. If there are no remaining entries, does nothing.
         */
        void next();
    }

    /**
     * The root interface for maps.
     * <p>
     * Map in EZ Collections is an analogue of {@link Map}. Map (also known as dictionary) is a data structure
     * that stores keys and values: all keys are unique, and each key has its own value. It's possible to do all standard
     * map operations: check if the key is contained in the map, get the value by the key, put / override the value for
     * the key, or remove the key and its value from the map. Child interfaces can provide you more functionality.
     * @author Alexey Dergunov
     * @since 0.1.0
     */
    static interface _Ez_Int__Int_Map {
        // TODO null issues, e.g. when there is no value for a certain key - consider returning special 'null value'

        /**
         * Returns the size of the map, i.e. the number of entries (key-value pairs) in it.
         * @return the size of the map
         */
        int size();

        /**
         * Checks if the map contains at least one entry (key-value pair).
         * @return {@code true} if the map doesn't contain any entries, {@code false} otherwise
         */
        boolean isEmpty();

        /**
         * Checks if the map contains the entry with the specified key.
         * @param key the key to be checked
         * @return {@code true} if the map contains the entry with the specified key, {@code false} otherwise
         */
        boolean containsKey(/*T1*/int/*T1*/ key);

        /**
         * Returns the value for the specified key. If the map doesn't contain the entry with the specified key, this
         * method can return everything, but sets the special flag, you should check it immediately after using get() this
         * way:
         * <pre>
         * if (map.returnedNull()) {
         *     // The map doesn't contain the entry with the specified key.
         *     // Take it into account and don't use the value returned by get().
         * } else {
         *     // The call of get() was successful.
         *     // You can use the value returned by get().
         * }
         * </pre>
         * @param key the key of the entry
         * @return the value for the specified key
         * @see #returnedNull()
         */
        /*T2*/int/*T2*/ get(/*T1*/int/*T1*/ key);

        /**
         * Puts the specified key-value pair into the map. That is, if the map already contains the entry for the specified
         * key, the previous value is overridden by the new one, and if it doesn't, the new entry is created. In the first
         * case, the previous value is returned, and in the second case it can return everything, but the special flag is
         * set. If you are going to use the returned value, you should check it immediately after using put() this way:
         * <pre>
         * if (map.returnedNull()) {
         *     // The map didn't contain the entry with the specified key. New entry was created.
         *     // Take it into account and don't use the value returned by put().
         * } else {
         *     // The map contained the entry with the specified key. The value was overridden.
         *     // You can use the previous value returned by put().
         * }
         * </pre>
         * @param key the key of the entry
         * @param value the value of the entry
         * @return the previous value for the specified key
         * @see #returnedNull()
         */
        /*T2*/int/*T2*/ put(/*T1*/int/*T1*/ key, /*T2*/int/*T2*/ value);

        /**
         * Removes the entry with the specified key from the map. This method returns the value of the removed entry,
         * however, if the map doesn't contain the entry with the specified key, it can return everything, but sets the
         * special flag, you should check it immediately after using remove() this way:
         * <pre>
         * if (map.returnedNull()) {
         *     // The map doesn't contain the entry with the specified key. Nothing has been removed.
         *     // Take it into account and don't use the value returned by remove().
         * } else {
         *     // The call of remove() was successful.
         *     // You can use the value returned by remove().
         * }
         * </pre>
         * @param key the key of the entry to be removed
         * @return the value of the removed entry
         * @see #returnedNull()
         */
        /*T2*/int/*T2*/ remove(/*T1*/int/*T1*/ key);

        /**
         * Checks if the last call of {@link #get}, {@link #put} or {@link #remove} has returned 'null'. Since we can't
         * return {@code null} using the primitive types, the methods listed above can return everything but set the
         * special flag. You should check this flag using this method.
         * <p>
         * In other words, this method returns {@code true} if and only if the map from the Java Collections Library
         * ({@link Map}) which contains the same entries returns {@code null} after calling the same method.
         * @return {@code true} if the last call of one of the methods listed above has returned incorrect value ('null'),
         * {@code false} if that call was successful and the returned value is correct
         */
        boolean returnedNull();

        /**
         * Removes all entries from the map.
         */
        void clear();

        /**
         * Returns the array which contains all keys in this map. It is not guaranteed that the array will be ordered in
         * any particular order. However, two consecutive calls of {@code keys()} and {@link #values()} methods return two
         * arrays related to each other such that for every index {@code i} the entry {@code (keys[i], values[i])} is
         * contained in the map. This method always allocates new array, so you can modify it and the original map won't
         * be changed, and vice versa.
         * @return the array which contains all keys in this map
         */
        /*T1*/int/*T1*/[] keys();

        /**
         * Returns the array which contains all values in this map. It is not guaranteed that the array will be ordered in
         * any particular order. However, two consecutive calls of {@link #keys()} and {@code values()} methods return two
         * arrays related to each other such that for every index {@code i} the entry {@code (keys[i], values[i])} is
         * contained in the map. This method always allocates new array, so you can modify it and the original map won't
         * be changed, and vice versa.
         * @return the array which contains all values in this map
         */
        /*T2*/int/*T2*/[] values();

        /**
         * Returns the iterator which can be used to go through the entries of this map. It is not guaranteed that the
         * iterator will return the entries in any particular order.
         * @return the iterator to go through the entries of this map
         */
        _Ez_Int__Int_MapIterator iterator();

        /**
         * Checks if this map is equal to other object. For equality the passed object should be of the same class as this
         * map and contain the same key-value pairs as this map.
         * @param object the object to be checked for equality
         * @return {@code true} if this map is equal to the specified object, {@code false} otherwise
         */
        @Override
        boolean equals(Object object);

        /**
         * Returns the hashcode of the map. If two maps are equal, their hashcodes are also equal. If the hashcodes of two
         * maps are different, these maps are also different. Please note that different maps can have equal hashcodes,
         * though the probability of this fact is low.
         * @return the hashcode of the map
         */
        @Override
        int hashCode();

        /**
         * Returns the human-readable string representation of the map.
         * @return the string representation of the map
         */
        @Override
        String toString();
    }

    /**
     * A mutable pair of two primitive types. Its fields are public to speedup the access.
     * @author Alexey Dergunov
     * @since 0.1.0
     */
    static class _Ez_Int__Int_Pair implements Comparable<_Ez_Int__Int_Pair> {
        private static final int HASHCODE_INITIAL_VALUE = 0x811c9dc5;
        private static final int HASHCODE_MULTIPLIER = 0x01000193;

        public /*T1*/int/*T1*/ first;
        public /*T2*/int/*T2*/ second;

        public _Ez_Int__Int_Pair(/*T1*/int/*T1*/ first, /*T2*/int/*T2*/ second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            _Ez_Int__Int_Pair that = (_Ez_Int__Int_Pair) o;

            return first == that.first && second == that.second;
        }

        @Override
        public int hashCode() {
            int hash = HASHCODE_INITIAL_VALUE;
            hash = (hash ^ PrimitiveHashCalculator.getHash(first)) * HASHCODE_MULTIPLIER;
            hash = (hash ^ PrimitiveHashCalculator.getHash(second)) * HASHCODE_MULTIPLIER;
            return hash;
        }

        @Override
        public int compareTo(_Ez_Int__Int_Pair o) {
            int res = /*W1*/Integer/*W1*/.compare(first, o.first);
            if (res == 0) {
                res = /*W2*/Integer/*W2*/.compare(second, o.second);
            }
            return res;
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    static final class PrimitiveHashCalculator {
        private PrimitiveHashCalculator() {
        }

        public static int getHash(boolean x) {
            return x ? 1231 : 1237; // as in Boolean.hashCode()
        }

        public static int getHash(byte x) {
            return x;
        }

        public static int getHash(short x) {
            return x;
        }

        public static int getHash(char x) {
            return x;
        }

        public static int getHash(int x) {
            return x;
        }

        public static int getHash(long x) {
            return (int)x ^ (int)(x >>> 32);
        }

        public static int getHash(float x) {
            return Float.floatToIntBits(x);
        }

        public static int getHash(double x) {
            long y = Double.doubleToLongBits(x);
            return (int)y ^ (int)(y >>> 32);
        }
    }
}


