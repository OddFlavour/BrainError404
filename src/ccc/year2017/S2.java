package ccc.year2017;

import ccc.tools.FolderReader;
import ccc.tools.InReader;
import ccc.tools.OutWriter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class S2 {
    static String source = "src/ccc/year2017/testData/senior_data/s2";

    public void solution(File f) throws IOException {
        InReader in = new InReader(f.getAbsolutePath());
        OutWriter out = new OutWriter(source, f);

        int N = in.readInt();

        int[] measurements = in.readListOfInt();

        Arrays.sort(measurements);

        int lo = 0, hi = N - 1;

        int[] answer = new int[N];

        int curr = 0;

        if (N % 2 != 0) {
            answer[curr++] = measurements[lo++];
        }

        while (lo < hi) {
            answer[curr++] = measurements[hi--];
            answer[curr++] = measurements[lo++];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = N - 1; i >= 0; i--) {
            sb.append(answer[i] + " ");
        }

//        System.out.println(sb.toString().trim() + "\n");
        out.write(sb.toString().trim() + " \n");
        out.close();
    }

    public static void main(String[] args) throws IOException {
        for (File f : FolderReader.getFilesInDir(source)) {
            if (f.getName().endsWith(".in")) {
                S2 s = new S2();
                s.solution(f);
            }
        }

//        S2 s = new S2();
//        s.solution(null);
    }
}
