package leetCode.challenge30Day.week3;

public class SearchInRotatedSortedArray {
    // [11, 58, 67, 0, 2, 6]
    public int search(int[] nums, int target) {
        int n = nums.length;
        int pivot = findPivot(nums) + 1;

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int adjustedMid = (mid + pivot) % n;

            if (nums[adjustedMid] < target) {
                left = mid + 1;
            } else if (nums[adjustedMid] > target) {
                right = mid - 1;
            } else {
                return adjustedMid;
            }
        }

        return -1;
    }

    public int findPivot(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (nums[mid] > nums[mid + 1 % nums.length]) {
                return mid;
            }
            if (nums[mid] < nums[left]) {
                right = mid - 1;
            } else if (nums[mid] > nums[right]) {
                left = mid;
            } else if (nums[mid] < nums[right]) {
                left = mid + 1;
            }
        }

        return left;
    }
}
