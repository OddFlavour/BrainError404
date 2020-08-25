package ccc.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FolderReader {
    public static List<File> getFilesInDir(String path) {
        List<File> ret = new ArrayList<>();

        File main = new File(path);
        for (File f : main.listFiles()) {
            if (f.isFile()) {
                ret.add(f);
            }
        }

        return ret;
    }
}
