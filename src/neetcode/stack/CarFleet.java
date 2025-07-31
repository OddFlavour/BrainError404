package neetcode.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://neetcode.io/problems/car-fleet?list=neetcode150
 */
public class CarFleet {
    static class Solution {
        class Car {
            int position;
            int speed;

            public Car(int position, int speed) {
                this.position = position;
                this.speed = speed;
            }
        }

        public int carFleet(int target, int[] position, int[] speed) {
            List<Car> cars = new ArrayList<>();

            for (int i = 0; i < position.length; i++) {
                cars.add(new Car(target - position[i], speed[i]));
            }

            cars.sort((o1, o2) -> o1.position - o2.position);

            Stack<Car> fleets = new Stack<>();

            for (Car c : cars) {
                if (!fleets.isEmpty()) {
                    Car top = fleets.peek();

                    if (canCatch(top, c)) {
                        continue;
                    }
                }

                fleets.push(c);
            }

            return fleets.size();
        }

        private boolean canCatch(Car inFront, Car inBack) {
            double timeRemaining = (double) inFront.position / inFront.speed;
            return timeRemaining * inBack.speed >= inBack.position;
        }
    }

    public static void main(String[] args) {
        int[][][] ts = {
//                { { 10 }, { 1,4 }, { 3,2 } },
                { { 17 }, { 8,12,16,11,7 }, { 6,9,10,9,7 } }
        };

        for (int[][] t : ts) {
            Solution s = new Solution();

            System.out.println(s.carFleet(t[0][0], t[1], t[2]));
        }
    }
}
