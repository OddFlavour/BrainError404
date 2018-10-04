package codeGoogle.MagicalMarvelousTour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.IntStream;

//https://code.google.com/codejam/contest/3024486/dashboard#s=p0
public class Main {

	/*
	 * Idea was to have three sums, left, mid, right. Mid would be Arnah's initial range.
	 * Now knowing that Arnah's opponent will pick the best choice between the three, we must guarantee
	 * that they don't pick mid (this is because Arnah gets to take a series of consecutive elements, so if
	 * mid is not taken then Arnah can take either left-mid, or mid-right)
	 * 
	 * The above is a false understanding because Arnah just takes whatever transistors is left. So not only
	 * limited to consecutive elements.
	 */
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("src/codeGoogle/MagicalMarvelousTour/A-small-practice.in"));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= T; i++) {
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
			
			int[] sums = new int[3];
			final int LEFT = 0, MID = 1, RIGHT = 2;
			int pLeft = 0;
			int pRight = N - 1;
			sums[MID] = IntStream.of(transistors).sum();
			
			while (
					!(pLeft == -1 && pRight == -1) && (pLeft <= pRight)
					) {
				if (sums[LEFT] < sums[MID]) {
					sums[LEFT] += transistors[pLeft];
					sums[MID] -= transistors[pLeft];
					pLeft++;
				} else {
					pLeft = -1;
				}
				
				if (sums[RIGHT] < sums[MID]) {
					sums[RIGHT] += transistors[pRight];
					sums[MID] -= transistors[pRight];
					pRight--;
				} else {
					pRight = -1;
				}
			}
			
			int arnahTransistors = sums[LEFT] > sums[RIGHT] ? sums[MID] + sums[RIGHT] : sums[MID] + sums[LEFT];
			
			System.out.printf("Case #%d: %.10f\n", i, (double) arnahTransistors / IntStream.of(transistors).sum());
		}
	}
}
