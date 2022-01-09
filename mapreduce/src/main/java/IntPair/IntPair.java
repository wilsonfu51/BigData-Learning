package IntPair;

import org.apache.hadoop.io.*;
import java.io.*;
import java.util.Objects;

public class IntPair implements WritableComparable<IntPair> {

    private int first;
    private int second;

    public IntPair() {
        set(0, 0);
    }

    public IntPair(int first, int second) {
        set(first, second);
    }

    public void set(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        new IntWritable(first).write(out);
        new IntWritable(second).write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        first = in.readInt();
        second = in.readInt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntPair intPair = (IntPair) o;
        return first == intPair.first &&
                second == intPair.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first) * 163 + Objects.hash(second);
    }

    @Override
    public String toString() {
        return first + "\t" + second;
    }

    @Override
    public int compareTo(IntPair ip) {
        int cmp = this.first < ip.first ? -1 : (this.first == ip.first ? 0 : 1);
        if (cmp != 0) {
            return cmp;
        }
        return this.second < ip.second ? -1 : (this.second == ip.second ? 0 : 1);
    }

    public static int compare(int cp1, int cp2) {
        return cp1 < cp2 ? -1 : (cp1 == cp2 ? 0 : 1);
    }

}
