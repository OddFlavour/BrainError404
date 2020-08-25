package ccc.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutWriter {

    private FileWriter out;

    public OutWriter(String source, File f) throws IOException {
        out = new FileWriter(new File(source + "/" + f.getName().replace(".in", ".out") + ".answer"));
    }

    public void write(String words) throws IOException {
        out.write(words);
    }

    public void close() throws IOException {
        out.close();
    }
}
