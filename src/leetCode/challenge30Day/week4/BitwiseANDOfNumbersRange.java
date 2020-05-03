package leetCode.challenge30Day.week4;

public class BitwiseANDOfNumbersRange {
    public int rangeBitwiseAnd(int m, int n) {
        // Find the minimum power of 2 to reach 'm'
        int power = -1;  // start at -1 for when 'm=0'
        while (Math.pow(2, power + 1) <= m) power++;

        // Check if 'n' is strictly within one more power of 2, if no, then no in common bit
        if (n >= Math.pow(2, power + 1)) return 0;

        // Now slowly process bits from left to right
        int result = 0;
        int curr = m;
        int currPower = power;
        while (curr > 0) {
            int bitValue = (int) Math.pow(2, currPower--);
            // If the current bit can be added, then add it
            if (bitValue <= curr) {
                result += bitValue;
                curr -= bitValue;
            }
            // Else determine if any number between 'm' and 'n' has the bit (if it does then we're done because to get to this bit, all previous bits must've been alternated through
            else {
                if (n >= bitValue + result) {
                    break;
                }
            }
        }

        return result;
    }
}
