import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JoinRecordWithStationNameTest {

    @Test
    public void Test() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        conf.set("mapreduce.framework.name", "local");
        conf.setInt("mapreduce.task.io.sort.mb", 1);

        Path inputNcdc = new Path("inputNcdc");
        Path inputStation = new Path("inputStation");
        Path output = new Path("output");

        FileSystem fs = FileSystem.getLocal(conf);
        fs.delete(output, true);

        JoinRecordWithStationName join = new JoinRecordWithStationName();
        join.setConf(conf);

        int exitCode = join.run(new String[] {
                inputNcdc.toString(), inputStation.toString(), output.toString()
        });
        assertThat(exitCode, is(0));

    }
}
