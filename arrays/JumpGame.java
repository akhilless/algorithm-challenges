/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * You can assume that you can always reach the last index.
 * 
 * https://leetcode.com/problems/jump-game-ii/
 * 
 * From each entry i of the array nums we can reach nums[i] elements, we say those elements are directly connected.
 * A given entry together with the entries directly connected to it (entries in the closed segment nums[i]..nums[i+nums[i]]
 * comprise a sliding window. In order to get to the destination with least number of jumps, we need find an entry in that window
 * that allows us to make the longest jump (or is connected to the most number of elements). Then we finish the jump by incrementing
 * the counter of jumps and slide the window to the right according by the longest jump. We stop when we reach the last element.
 * Thus, we are using a greedy algorithm every time making the longest jump.
 *
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
class JumpGame {
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        // next value of the right border of the sliding window corresponding to the longest jump possible from that window
        int nextJ = 0;
        // counts the number of jumps made
        int counter = 0;

        for (int i = 0, j = Math.min(nums[i], nums.length-1); i < nums.length-1;){
            // we found a longer jump, so we update nextJ to it
            if (nums[i]+i > nextJ) {
                nextJ = nums[i]+i;
            }
            
            // we reached the end of the window, so we need to jump using nextJ
            if (i>=j) {
                j = Math.min(nextJ, nums.length-1); //prevents going out of array bounds               
                
                if (j == nums.length-1) { // we can reach the destination in one jump from here
                    i = j;
                }
            }           

            i++;
            if (i>=j) { // we have jumped, thus increment the jump counter
                counter++;
            }
        }
        
        return counter;
    }
}
