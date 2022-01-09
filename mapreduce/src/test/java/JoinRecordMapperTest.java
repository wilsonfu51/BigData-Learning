import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;

import java.io.IOException;

public class JoinRecordMapperTest {

    @Test
    public void processValidRecode() throws IOException, InterruptedException {
        Text value = new Text("0035029070999991901123120004+64333+023450FM-12+0005" +
                "99999V0200901N009819999999N0000001N9-00831+99999100711ADDGF108991999999999999999999MW1721");
        new MapDriver<LongWritable, Text, TextPair, Text>()
                .withMapper(new JoinRecordMapper())
                .withInput(new LongWritable(0), value)
                .withOutput(new TextPair("02907099999", "1"), value)
                .runTest();
    }
}
