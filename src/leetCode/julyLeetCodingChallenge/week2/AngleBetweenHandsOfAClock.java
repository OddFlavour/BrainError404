package leetCode.julyLeetCodingChallenge.week2;

public class AngleBetweenHandsOfAClock {
    final double ATOMIC_ANGLE = 360 / 60.0;
    final double SECTION_ANGLE = ATOMIC_ANGLE * 5;

    public double angleClock(int hour, int minutes) {
        double angleInSection = (minutes / 60.0) * SECTION_ANGLE;

        int hourOnClockToMinutes = (hour % 12) * 5;

        int difference = (hourOnClockToMinutes - minutes + 60) % 60;

        double angleFromMinutes = difference * ATOMIC_ANGLE;

        double answer = (angleInSection + angleFromMinutes) % 360;
        if (answer > 180) {
            answer = 360 - answer;
        }

        return answer;
    }
}
