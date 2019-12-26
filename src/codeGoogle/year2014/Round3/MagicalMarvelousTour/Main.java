package codeGoogle.year2014.Round3.MagicalMarvelousTour;

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
			
			if (init != 53)
				continue;
			
			int N = Integer.parseInt(lineInSplit[0]);
			int p = Integer.parseInt(lineInSplit[1]);
			int q = Integer.parseInt(lineInSplit[2]);
			int r = Integer.parseInt(lineInSplit[3]);
			int s = Integer.parseInt(lineInSplit[4]);
			
			long[] transistors = new long[N];
			for (int j = 0; j < N; j++) {
				transistors[j] = ((j * p + q) % r + s);
			}
//			
//			N = 4;
//			long[] transistors = {1, 2, 2, 1};
			// start of code
			
			long[] partialSums = new long[N+1];
			partialSums[0] = 0;
			for (int i=1; i<=N; i++) {
				partialSums[i] = partialSums[i-1] + transistors[i-1];
			}
		
			long best = 0;
			for (int p1=0; p1<=N; p1++) {
				int left = p1, right = N;
				int pointer = 0;
				long midSum = partialSums[pointer] - partialSums[p1];
				long rightSum = partialSums[N] - partialSums[pointer];
				while (left < right) {
					pointer = (left + right) / 2;
					midSum = partialSums[pointer] - partialSums[p1];
					rightSum = partialSums[N] - partialSums[pointer];
					
					best = Math.max(best, partialSums[N] - Math.max(Math.max(partialSums[p1], midSum), rightSum));
					System.out.printf("1st: %d, %d, %d | %d, %d, %d\n", left, right, pointer, partialSums[p1], midSum, rightSum);
					
					if (midSum > rightSum) {
						if (right == pointer) break;
						right = pointer;
					} else if (midSum < rightSum) {
						if (left == pointer) break;
						left = pointer;
					} else {
						break;
					}
					
					System.out.printf("2nd: %d, %d, %d | %d, %d, %d\n", left, right, pointer, partialSums[p1], midSum, rightSum);
				}
				
				System.out.printf("final: %d, %d, %d | %d, %d, %d\n", left, right, pointer, partialSums[p1], midSum, rightSum);
				System.out.println("===");
			}
			double answer = (double) best / partialSums[N];
//			System.out.println(answer);
			fw.append(String.format("Case #%d: %.10f\n", init, answer));
		}
		
		fw.close();
		br.close();
	}
}
