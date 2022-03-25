/*
 * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice.
 * The relative order of the elements should be kept the same.
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
 * Return k after placing the final result in the first k slots of nums.
 *
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 *
 * We copy elements using 2 variables - one to keep track of the number of already copied elements and the next array index to copy an element into
 * and another variable to count number of times we have encountered a value (to allow a distinct value to appear at most twice in the array).
 *
 * Time complexity:  O(n)
 * Space complexity: O(1)
 */
class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        
        // number of distinct elements and the array index where we place the next discovered distinct element
        int k = 1;
        // indicates how many times we encountered the same element
        int timesSeen = 1;
        
        for (int i = 1; i < nums.length; i++) {
            /*
             * we copy element if it is a distinct element 
             * (since it is a sorted array, can be figured out by comparing the current element with the previous element)
             * or we have seen it once.
             */
            if (nums[i] != nums[i-1] || timesSeen == 1) {
                timesSeen = nums[i] != nums[i-1] ? 1 : timesSeen+1;
                
                nums[k] = nums[i];
                k++;       
            } else {                
                timesSeen++;
            }            
        }
        
        return k;
    }
}
