import java.io.*;

import org.apache.hadoop.io.*;

public class TextPair implements WritableComparable<TextPair> {
    private static Text first;
    private static Text second;

    public static class KeyComparator extends WritableComparator {
        protected KeyComparator() {
            super(TextPair.class, true);
        }

        @Override
        public int compare(WritableComparable w1, WritableComparable w2) {
            TextPair tp1 = (TextPair) w1;
            TextPair tp2 = (TextPair) w2;
            int cmp = tp2.getFirst().compareTo(tp2.getFirst());

            return cmp;

        }
    }

    public static class FirstComparator extends WritableComparator {
        protected FirstComparator() {
            super(TextPair.class, true);
        }

        @Override
        public int compare(WritableComparable t1, WritableComparable t2) {
            TextPair tp1 = (TextPair) t1;
            TextPair tp2 = (TextPair) t2;
            System.out.println("tp1 first" + tp1.getFirst());
            System.out.println("tp2 first" + tp2.getFirst());

            return tp1.getFirst().compareTo(tp2.getFirst());
        }

    }

    public TextPair() {
        set(new Text(), new Text());
    }

    public TextPair(String first, String second) {
        set(new Text(first), new Text(second));
    }

    public TextPair(Text first, Text second) {
        set(first, second);
    }

    public void set(Text first, Text second) {
        this.first = first;
        this.second = second;
        System.out.println("first: " + first.toString());
        System.out.println("second: " + second.toString());
    }

    public Text getFirst() {
        return first;
    }

    public Text getSecond() {
        return second;
    }

    @Override
    public int hashCode() {
        return first.hashCode() * 163 + second.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TextPair) {
            TextPair tp = (TextPair) o;
            return first.equals(tp.first) && second.equals(tp.second);
        }
        return false;
    }

    @Override
    public String toString() {
        return first + "\t" + second;
    }

    @Override
    public int compareTo(TextPair tp) {
        int cmp = first.compareTo(tp.first);
        if (cmp != 0) {
            return cmp;
        }
        return second.compareTo(tp.second);
    }

    /**
     * Serialize the fields of this object to <code>out</code>.
     *
     * @param out <code>DataOuput</code> to serialize this object into.
     * @throws IOException
     */
    @Override
    public void write(DataOutput out) throws IOException {
        first.write(out);
        second.write(out);
    }

    /**
     * Deserialize the fields of this object from <code>in</code>.
     *
     * <p>For efficiency, implementations should attempt to re-use storage in the
     * existing object where possible.</p>
     *
     * @param in <code>DataInput</code> to deseriablize this object from.
     * @throws IOException
     */
    @Override
    public void readFields(DataInput in) throws IOException {
        first.readFields(in);
        second.readFields(in);
    }

}