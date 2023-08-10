import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class FileUtilsWorker {
    public static long getFileSize(File file) {
        return file.length();
    }
    public static void writeToFile(File file, String content) {
        try {
            FileUtils.writeStringToFile(file, content, "UTF-8", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}