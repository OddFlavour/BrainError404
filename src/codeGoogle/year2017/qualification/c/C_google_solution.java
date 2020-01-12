package codeGoogle.year2017.qualification.c;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The idea of the solution is that:
 * - create a set that contains the consecutive stalls (a.k.a magnitude of interval)
 * - create a dict that tracks how many identical magnitude of interval exist
 * - keep a count of how many people have been seated
 * 
 * - every time poll the max value from that set (due problem rules)
 * - using the dict, seat X number of people
 * - in the case that the magnitude > remaining number of unseated people, then that is our answer
 */
public class C_google_solution {

	public static void main(String[] args) throws IOException {

		URL url = C_google_solution.class.getResource("C-large-practice.in");
		FileReader fr = new FileReader(new File(url.getPath()));
		BufferedReader br = new BufferedReader(fr);

		int T = Integer.parseInt(br.readLine());

		for (int _case = 0; _case < T; _case++) {
			List<String> tokens = Arrays.asList(br.readLine().split(" "));
			List<Long> iTokens = tokens.stream().map(x -> Long.valueOf(x)).collect(Collectors.toList());

			long N = iTokens.get(0);
			long K = iTokens.get(1);

			Set<Long> set = new HashSet<Long>();
			set.add(N);

			// Count dictionary
			HashMap<Long, Long> dict = new HashMap<Long, Long>();
			dict.put(N, 1L);
			
			long finished = 0;

			for (int person = 0; person < K; person++) {
				long poll = pollMax(set);
				long focus = poll - 1;
				
				BigInteger a = new BigInteger(String.valueOf(focus));
				BigInteger biLeft = a.divide(new BigInteger("2"));
				BigInteger biRight = a.subtract(biLeft);
				
				long left = biLeft.longValue();
				long right = biRight.longValue();

				if (finished + dict.get(poll) >= K) {
					System.out.printf("Case #%d: %d %d\n", _case + 1, right, left);
					break;
				}
				
				finished += dict.get(poll);
				
				set.add(left);
				set.add(right);
				
				dict.put(left, dict.getOrDefault(left, 0L) + dict.get(poll));
				dict.put(right, dict.getOrDefault(right, 0L) + dict.get(poll));
			}
		}
		
		br.close();
	}
	
	private static long pollMax(Set<Long> set) {
		Long max = -1L;
		for (Long v : set) {
			if (v > max) {
				max = v;
			}
		}
		
		set.remove(max);
		
		return max;
	}
}
