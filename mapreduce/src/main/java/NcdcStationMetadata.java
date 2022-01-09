import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class NcdcStationMetadata {
    private HashMap<String, String> stationMetas = new HashMap<>();

    public String getStationName(String stationId) {

        String stationName;
        stationName = stationMetas.get(stationId);

        return stationName;
    }

    public void initialize(File file) {
        String fileName = file.getName();
        String path = file.getPath();
        try{
            Scanner sc = new Scanner(new FileReader(path));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                stationMetas.put(line.substring(0,11), path + line.substring(11));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
