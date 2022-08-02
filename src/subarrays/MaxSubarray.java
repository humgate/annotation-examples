package subarrays;


public class MaxSubarray {
    static int [] nums = {-2,1,-3,4,-1,2,1,-5,4};

    /**
     * Given an integer array nums, find the contiguous subarray (containing at least one number)
     * which has the largest sum and return its sum.
     *
     */
    public static int maxSubArray(int[] nums) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > maxSum)
                maxSum = sum;
            if (sum <= 0)
                sum = 0;
        }
        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(nums));
    }

}
