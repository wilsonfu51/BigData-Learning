import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;

import java.io.IOException;

public class JoinStationMapperTest {

    @Test
    public void processValidRecode() throws IOException, InterruptedException {
        Text value = new Text("02907099999DAKOTAH");
        new MapDriver<LongWritable, Text, TextPair, Text>()
                .withMapper(new JoinStationMapper())
                .withInput(new LongWritable(0), value)
                .withOutput(new TextPair("02907099999", "0"), new Text("DAKOTAH"))
                .runTest();
    }
}
