package codeGoogle.MagicalMarvelousTour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;

//https://code.google.com/codejam/contest/3024486/dashboard#s=p0
public class Main {

	/*
	 * Needs to be sped up
	 */
	public static void main(String[] args) throws IOException {
		
		String path = "src/codeGoogle/MagicalMarvelousTour/";
		BufferedReader br = new BufferedReader(new FileReader(path + "A-large-practice.in"));
		FileWriter fw = new FileWriter(path + "solution.txt");
		
		int T = Integer.parseInt(br.readLine());
		
		for (int init = 1; init <= T; init++) {
			String[] lineInSplit = br.readLine().split(" ");
			
			int N = Integer.parseInt(lineInSplit[0]);
			int p = Integer.parseInt(lineInSplit[1]);
			int q = Integer.parseInt(lineInSplit[2]);
			int r = Integer.parseInt(lineInSplit[3]);
			int s = Integer.parseInt(lineInSplit[4]);
			
			int[] transistors = new int[N];
			for (int j = 0; j < N; j++) {
				transistors[j] = ((j * p + q) % r + s);
			}
			
			// start of code
			
			int[] partialSums = new int[N];
			partialSums[0] = transistors[0];
			for (int i=1; i<N; i++) {
				partialSums[i] = partialSums[i-1] + transistors[i];
			}
		
			// have two pointers
			int maxTransistors = 0;
			// go through all possible ranges
			for (int p1=0; p1<N; p1++) {
				for (int p2=p1; p2<N-1; p2++) {
					int sum1 = partialSums[p1] - transistors[p1];
					int sum2;
					try {
						sum2 = partialSums[p2] - partialSums[p1-1];
					} catch (Exception e) {
						sum2 = partialSums[p2];
					}
					int sum3 = partialSums[N-1] - partialSums[p2];
					
					int total = sum1 + sum2 + sum3 - Math.max(Math.max(sum1, sum2), sum3);
					maxTransistors = Math.max(total, maxTransistors);
				}
			}
			//System.out.println(Arrays.toString(transistors));
			double answer = (double) maxTransistors / partialSums[N-1];
			fw.append(String.format("Case #%d: %.10f\n", init, answer));
		}
		
		fw.close();
		br.close();
	}
}
