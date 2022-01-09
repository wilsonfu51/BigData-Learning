import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;

import java.io.IOException;

public class StationTemperatureMapperText {

    @Test
    public void processValidRecode() throws IOException, InterruptedException {
        Text value = new Text("0043029070999991950051518004+68750+023550FM-12+0382" +
                "99999V0203201N00261220001CN9999999N9+00481+99999999999");
        new MapDriver<LongWritable, Text, Text, IntWritable>()
                .withMapper(new MaxTemperatureByStationNameUsingDistributedCacheFile.StationTemperatureMapper())
                .withInput(new LongWritable(0), value)
                .withOutput(new Text("02907099999"), new IntWritable(48))
                .runTest();
    }
}
