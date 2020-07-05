package leetCode.juneLeetCodingChallenge.week3;

public class ValidateIpAddress {
    static String ipv4 = "IPv4", ipv6 = "IPv6", neither = "Neither";

    public String validIPAddress(String IP) {
        boolean isIPv4 = IP.contains(".");
        String[] tokens = IP.split("\\.", -1);
        if (tokens.length != 1 && tokens.length != 4) return neither;
        else if (tokens.length == 1){
            tokens = IP.split(":", -1);
            if (tokens.length != 8) return neither;

            isIPv4 = false;
        }

        try {
            if (isIPv4) {
                for (String token : tokens) {
                    int tokenToInt = Integer.parseInt(token);

                    if (tokenToInt < 0 || tokenToInt > 255) return neither;
                    if (String.valueOf(tokenToInt).length() != token.length()) return neither;
                }

                return ipv4;
            } else {
                int upperBound = Integer.parseInt("ffff", 16);
                for (String token : tokens) {
                    token = token.toLowerCase();

                    int tokenToHex = Integer.parseInt(token, 16);

                    if (tokenToHex < 0 || tokenToHex > upperBound) return neither;
                    if (token.length() > 4) return neither;
                }

                return ipv6;
            }
        } catch (Exception e) {
            return neither;
        }
    }

    public static void main(String[] args) {
        String[] tests = {
                "172.16.254.1",
                "256.256.256.256",
                "2001:db8:85a3:0:0:8A2E:0370:7334",
                "2001:0db8:85a3:0000:0000:8a2e:0370:7334",
                "2001:0db8:85a3::8A2E:0370:7334",
                "02001:0db8:85a3:0000:0000:8a2e:0370:7334",
                "a.2b.3.a",
                "...",
                "",
                "ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff",
                "2001:0db8:85a3:0:0:8A2E:0370:7334:"
        };

        String[] answers = {
                ipv4,
                neither,
                ipv6,
                ipv6,
                neither,
                neither,
                neither,
                neither,
                neither,
                ipv6,
                neither
        };

        for (int i = tests.length - 1; i < tests.length; i++) {
            ValidateIpAddress s = new ValidateIpAddress();
            System.out.println(s.validIPAddress(tests[i]).equals(answers[i]));
        }
    }
}
