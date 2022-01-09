import java.util.ArrayList;
import java.util.List;

public class Range {

    private int start;
    private int end;

    public Range(String range) throws Exception{
        if (range.matches("^[0-9]+-[0-9]+")) {
            start = Integer.parseInt(range.split("-")[0]);
            end = Integer.parseInt(range.split("-")[1]);
        }
    }

    public static List<Range> parse(String cutPattern) throws Exception {
        String[] rangesStr = cutPattern.split(",");
        List<Range> ranges = new ArrayList<Range>();
        for (String str : rangesStr) {
            ranges.add(new Range(str));
        }
        return ranges;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public String getSubstring(String line) {
        return line.substring(start, end);
    }
}
