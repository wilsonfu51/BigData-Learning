import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.commons.lang3.Range;

import java.io.ByteArrayOutputStream;



/**
 * example from chapter 12: Avro Generic API
 *
 * oct 20th 2021
 */
public class AvroStringPairSpecificAPI {

    public static StringPair parse() throws Exception {

        StringPair datum = new StringPair();

        datum.setLeft("L");
        datum.setRight("R");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DatumWriter<StringPair> writer =
                new SpecificDatumWriter<StringPair>(StringPair.class);
        Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
        writer.write(datum, encoder);
        encoder.flush();
        out.close();

        DatumReader<StringPair> reader =
                new SpecificDatumReader<StringPair>(StringPair.class);
        Decoder decoder = DecoderFactory.get().binaryDecoder(out.toByteArray(), null);
        StringPair result = reader.read(null, decoder);
        return result;
    }

}
