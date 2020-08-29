package ccc.year2017;

import ccc.tools.FolderReader;
import ccc.tools.InReader;
import ccc.tools.OutWriter;

import java.io.File;
import java.io.IOException;

public class S3 {
    static final String source = "src/ccc/year2017/testData/senior_data" + "/s3";

    public void solution(File f) throws IOException {
//        InReader in = new InReader();
        InReader in = new InReader(f.getAbsolutePath());
        OutWriter out = new OutWriter(source, f);

        int N = in.readInt();

        int[] list = in.readListOfInt();
        int[] pieces = new int[4001];  // each index represents how many pieces of height

        for (int e : list) {
            pieces[e]++;
        }

        int[] heights = new int[4001];  // each index represents how long the fence can be built of height

        for (int height = 1; height <= 4000; height++) {
            for (int j = 0; j <= height / 2; j++) {
                if (j == height - j) {
                    heights[height] += pieces[j] / 2;
                } else {
                    heights[height] += Math.min(pieces[j], pieces[height - j]);
                }
            }
        }

        int a = 0;
        int b = 1;

        // Collect answers
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] > a) {
                a = heights[i];
                b = 0;
            }

            if (heights[i] == a) {
                b++;
            }
        }

        String answer = String.format("%d %d\n", a, b);
//        System.out.println(answer);
        out.write(answer);
        out.close();
    }

    public static void main(String[] args) throws IOException {
        for (File f : FolderReader.getFilesInDir(source)) {
            if (f.getName().endsWith(".in")) {
                S3 s = new S3();
                s.solution(f);
            }
        }
    }
}
