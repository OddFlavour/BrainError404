package ccc.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InReader {
    private BufferedReader in;

    public InReader() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    public InReader(String path) throws IOException {
        in = new BufferedReader(new FileReader(path));
    }

    public int readInt() throws IOException {
        return Integer.parseInt(in.readLine());
    }

    public int[] readListOfInt(String splitter) throws IOException {
        String[] tokens = in.readLine().split(splitter);

        List<Integer> parsed = Arrays.stream(tokens).map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        return parsed.stream().mapToInt(value -> value).toArray();
    }

    public int[] readListOfInt() throws IOException {
        return readListOfInt(" ");
    }
}
