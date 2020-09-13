package leetCode.julyLeetCodingChallenge.week2;

public class ReverseBits {
    public int reverseBits(int n) {
        int ret = 0;

        for (int i = 0; i < 32; i++) {
            int value = n & 1;

            n >>= 1;
            ret <<= 1;

            ret |= value;
        }

        return ret;
    }
}
