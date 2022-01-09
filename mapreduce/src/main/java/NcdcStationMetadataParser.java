import org.apache.hadoop.io.Text;

public class NcdcStationMetadataParser {

    private String stationId;
    private String stationName;

    public boolean parse(Text value) {
        String stationInfo = value.toString();
        stationId = stationInfo.substring(0,11);
        stationName = stationInfo.substring(11, stationInfo.length());
        return true;
    }

    public String getStationId() {
        return stationId;
    }

    public String getStationName() {
        return stationName;
    }

}
