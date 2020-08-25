package ccc.year2017;

import ccc.tools.FolderReader;
import ccc.tools.InReader;
import ccc.tools.OutWriter;

import java.io.File;
import java.io.IOException;

public class S1 {
    static String source = "src/ccc/year2017/testData/senior_data/s1";

    public void solution(File f) throws IOException {
        InReader in = new InReader(f.getAbsolutePath());
        OutWriter out = new OutWriter(source, f);

        int N = in.readInt();

        int[] a = in.readListOfInt();
        int[] b = in.readListOfInt();

        int sum1 = 0;
        int sum2 = 0;

        int ans = 0;

        for (int i = 0; i < N; i++) {
            sum1 += a[i];
            sum2 += b[i];

            if (sum1 == sum2) {
                ans = i + 1;  // '+ 1' because index starts at 1
            }
        }

        out.write(String.valueOf(ans) + "\n");
        out.close();
    }

    public static void main(String[] args) throws IOException {
        for (File f : FolderReader.getFilesInDir(source)) {
            if (f.getName().endsWith(".in")) {
                S1 s = new S1();
                s.solution(f);
            }
        }
    }
}
