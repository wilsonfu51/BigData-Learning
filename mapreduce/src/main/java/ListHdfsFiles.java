import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class ListHdfsFiles {
    private static FileSystem fs;

    static {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "file:///");

        try {
            fs = FileSystem.get(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listFileDir(Path p) throws IOException {
        FileStatus[] statuses = fs.listStatus(p);
        for (FileStatus status: statuses) {
            Path path = status.getPath();
            String name = path.getName();
            System.out.println(name);
        }
    }

    public static void main(String[] args) throws IOException {
        Path path = new Path("/");
        listFileDir(path);
    }
}
