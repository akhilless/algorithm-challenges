// https://leetcode.com/problems/minimum-operations-to-equalize-array/
class MinimumOperationsToEqualize {
    public int minOperations(int[] nums) {
        // edge cases
        if (nums.length == 1) {
            return 0;
        }
        // take boundaries into account
        //1. all elements equal
        boolean areAllEqual = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[0]) {
                areAllEqual = false;
                break;
            }
        }
        if (areAllEqual) {
            return 0;
        }
        return 1;
    }
}
