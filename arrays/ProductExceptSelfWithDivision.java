// https://leetcode.com/problems/product-of-array-except-self/
class ProductExceptSelfWithDivision {
    public int[] productExceptSelf(int[] nums) {
        //special cases
        int prod = 1;
        int iZero = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && iZero < 0) {
                iZero = i;
            } else if (nums[i] == 0) {
                prod = 0;
                break;
            } else {
                prod *= nums[i];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (prod == 0) {
                nums[i] = 0;
            } else if (iZero >= 0 && i == iZero) {
                nums[i] = prod;
            } else if (iZero >= 0) {
                nums[i] = 0;
            } else {
                nums[i] = prod / nums[i];
            }
        }
        return nums;       
    }
}
