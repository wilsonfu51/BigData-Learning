import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RangeTest {
    @Test
    public void rangeTest() throws Exception {
        List<Range> ranges = Range.parse("5-10,11-15,16-19,88-92,93-93");
        assertThat(ranges.size(),is(5));
        String str = ranges.get(0).getSubstring("0000055555000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        System.out.println(str);
        assertThat(str,is("55555"));
    }
}
