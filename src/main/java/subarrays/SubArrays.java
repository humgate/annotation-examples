package subarrays;


public class SubArrays {
    static int [] nums = {-2,1,-3,4,-1,2,1,-5,4};
    static int [] positiveNums = {100,1,2};

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

    /**
     * Given an array of positive numbers and a positive number ‘k,’
     * find the maximum sum of any contiguous subarray of size ‘k’.
     *
     * @param nums - given positive number array
     * @param k - size of sub array
     */

    public static int maxSubArray(int[] nums, int k) {
        int maxSum = 0;
        for (int i = 0; i <= nums.length - k; i++) {
            int sum = 0;
            for (int j = 0; j < k; j++) {
                sum += nums[i + j];
            }
            if (sum > maxSum) maxSum = sum;
        }

        return maxSum;
    }


    public static void main(String[] args) {
        //System.out.println(maxSubArray(nums));
        System.out.println(maxSubArray(positiveNums, 3));
    }

}
