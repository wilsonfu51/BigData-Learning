import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class JoinReduceTest {

    @Test
    public void validReducer() throws IOException, InterruptedException {
        new ReduceDriver<TextPair, Text, Text, Text>()
                .withReducer(new JoinReducer())
                .withInput(new TextPair("02907099999","0"), Arrays.asList(new Text("DAKOTAH"), new Text("testtest")))
                .withOutput(new Text("02907099999"), new Text("DAKOTAH\ttesttest"));
    }
}
