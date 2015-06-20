package thevoice;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author anna
 */
public class FilesManager {
    
    static File[] TxtFilesFromFolder(String path) {
        File f = new File(path);
        if (f.exists() && f.isDirectory())
            return f.listFiles(
                    (File directory, String fileName) -> fileName.endsWith(".txt")
            );
        return new File[]{};
    }
    
    static String textStringFromFile(String path) {
        File f = new File(path);
        if (f.exists() && f.isFile())
            try {
                byte bytes[] = java.nio.file.Files.readAllBytes(f.toPath());
                return new String(bytes, StandardCharsets.UTF_8);
            } catch (IOException e) {
                System.err.println("Error during reading from file");
            }
        return "";
    }
}
