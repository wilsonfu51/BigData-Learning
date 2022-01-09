
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.hadoop.util.GenericOptionsParser;
import org.hamcrest.core.Is;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.io.ByteArrayOutputStream;


/**
 * example from chapter 12: Avro Generic API
 *
 * oct 20th 2021
 */
public class AvroStringPairAPITest {

    @Test
    public void avroStringPairSpecificAPItest() throws Exception {

        StringPair result = AvroStringPairSpecificAPI.parse();
        assertThat(result.getLeft().toString(), is("L"));
        assertThat(result.getRight().toString(), is("R"));
    }

    @Test
    public void avroStringPairGenericAPItest() throws Exception {

        ByteArrayOutputStream out = AvroStringPairGenericAPI.parse();

        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(
                AvroStringPairGenericAPI.class.getResourceAsStream("StringPair.avsc"));

        DatumReader<GenericRecord> reader =
                new GenericDatumReader<GenericRecord>(schema);
        Decoder decoder = DecoderFactory.get().binaryDecoder(out.toByteArray(), null);

        GenericRecord result = reader.read(null, decoder);
        assertThat(result.get("left").toString(), is("L"));
        assertThat(result.get("right").toString(), is("R"));
    }
}
