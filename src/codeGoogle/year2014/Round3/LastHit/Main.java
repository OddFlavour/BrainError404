package codeGoogle.year2014.Round3.LastHit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//https://code.google.com/codejam/contest/3024486/dashboard#s=p1
public class Main {

	static int P, Q, N;
	static int[] hitpoints;
	static int[] golds;
	static int[][][] d;
	
	public static void main(String[] args) throws IOException {
		
		// Inputters
		BufferedReader br = new BufferedReader(new FileReader("src/codeGoogle/LastHit/B-large-practice.in"));

		int numCases = Integer.parseInt(br.readLine());

		for (int i = 0; i < numCases; i++) {
			// Read P, Q, N
			String[] lineInSplit = br.readLine().split(" ");
			P = Integer.parseInt(lineInSplit[0]);
			Q = Integer.parseInt(lineInSplit[1]);
			N = Integer.parseInt(lineInSplit[2]);
			
			hitpoints = new int[N];
			golds = new int[N];
			
			for (int j = 0; j < N; j++) {
				lineInSplit = br.readLine().split(" ");
				hitpoints[j] = Integer.parseInt(lineInSplit[0]);
				golds[j] = Integer.parseInt(lineInSplit[1]);
			}
			
			// Processing
			
			// i-th mob, hitpoints, saved turns
			// how to calculate upper limit of saved turns?
			// max hp * max mob / min tower damage + 1 from diana being able to hit first
			// how to calculate upper limit of hitpoints?
			// 1 <= hitpoint <= 200. array[200] yields minimum size of 201
			d = new int[100][201][1001];
			for (int x = 0; x < 100; x++) {
				for (int y = 0; y < 200; y++) {
					for (int z = 0; z < 1000; z++) {
						d[x][y][z] = -1;
					}
				}
			}
			
			System.out.printf("Case #%d: %d\n", i + 1, recurse(0, hitpoints[0], 1));
		}
		
		br.close();
	}
	
	public static int recurse(int mobNum, int hitpoint, int savedTurns) {

		// If all mobs have died
		if (hitpoint <= 0 && mobNum + 1 == N)
			return 0;
		
		// If the mob died then move onto next mob
		if (hitpoint <= 0) {
			return recurse(mobNum + 1, hitpoints[mobNum + 1], savedTurns);
		}
		// Dp part
		if (d[mobNum][hitpoint][savedTurns] != -1) return d[mobNum][hitpoint][savedTurns];
		
		// If diana can hit, then we'll evaluate what happens if she does hit or does not hit
		if (savedTurns > 0) {
			int gEarned = 0;
			if (hitpoint - P <= 0) {
				gEarned = golds[mobNum];
			}
			
			d[mobNum][hitpoint][savedTurns] = Math.max(gEarned + recurse(mobNum, hitpoint - P, savedTurns - 1), // if she hits
														recurse(mobNum, hitpoint - Q, savedTurns + 1)); // if she does not hit
		} else { // If diana can't hit, then the tower hits
			return recurse(mobNum, hitpoint - Q, savedTurns + 1);
		}
		
		// 
		return d[mobNum][hitpoint][savedTurns];
	}
}
