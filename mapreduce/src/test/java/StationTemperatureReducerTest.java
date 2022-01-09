import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class StationTemperatureReducerTest {
    @Test
    public void returnsMaximumIntegerInValues() throws IOException, InterruptedException {
        new ReduceDriver<Text, IntWritable, Text, IntWritable>()
                .withReducer(new MaxTemperatureByStationNameUsingDistributedCacheFile.MaxTemperatureReducerWithStationLookup())
                .withInput(new Text("02907099999"), Arrays.asList(new IntWritable(10), new IntWritable(5)))
                .withOutput(new Text("DAKOTAH"), new IntWritable(10))
                .runTest();
    }
}
