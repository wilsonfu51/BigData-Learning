import org.apache.hadoop.io.Text;

public class NcdcRecordParser {

    private static final int MISSING_TEMPERATURE = 9999;

    private boolean isMalformed = false;
    private boolean isMissing = false;
    private String year;
    private int airTemperature;
    private String quality;
    private String stationId;

    public void parse(String record) {
        year = record.substring(15, 19);
        String airTemperatureString;

        // remove leading plus sign as parseInt doesn't like them (pre-Java 7)
        if (record.charAt(87) == '+') {
            airTemperatureString = record.substring(88, 92);
        } else if (record.charAt(87) == '-') {
            airTemperatureString = record.substring(87, 92);
        } else {
            isMalformed = true;
            airTemperatureString = record.substring(87, 92);
        }
        airTemperature = Integer.parseInt(airTemperatureString);
        isMissing = airTemperature == MISSING_TEMPERATURE;
        quality = record.substring(92, 93);
        stationId = record.substring(4,15);
    }

    public void parse(Text record) {
        parse(record.toString());
    }

    public boolean isValidTemperature() {
        return !isMalformed && airTemperature != MISSING_TEMPERATURE && quality.matches("[01459]");
    }

    public String getYear() {
        return year;
    }

    public int getYearInt() {
        return Integer.parseInt(year);
    }

    public int getAirTemperature() {
        return airTemperature;
    }

    public boolean isMalformedTemperature() {
        return isMalformed;
    }

    public String getStationId(){
        return stationId;
    }

    public boolean isMissingTemperature() {
        return isMissing;
    }

    public String getQuality() {
        return quality;
    }
}
