// https://leetcode.com/problems/product-of-array-except-self/
class ProductExceptSelfWithoutDivision {
    public int[] productExceptSelf(int[] nums) {
        int[] prefixes = new int[nums.length];
        int[] suffixes = new int[nums.length];
        for (int i = 0, j = nums.length - 1; i < nums.length && j >= 0; i++, j--) {
            prefixes[i] = i > 0 ? prefixes[i-1]*nums[i] : nums[i];
            suffixes[j] = j < nums.length-1 ? suffixes[j+1]*nums[j] : nums[j];
        }

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                nums[i] = suffixes[i+1];
            } else if (i == nums.length - 1) {
                nums[i] = prefixes[i-1];
            } else {
                nums[i] = prefixes[i-1] * suffixes[i+1];
            }
        }
        return nums;                
    }
}
