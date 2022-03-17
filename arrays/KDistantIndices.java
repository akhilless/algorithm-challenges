/**
* You are given a 0-indexed integer array nums and two integers key and k. 
* A k-distant index is an index i of nums for which there exists at least one index j such that |i - j| <= k and nums[j] == key.
* Return a list of all k-distant indices sorted in increasing order.
* https://leetcode.com/problems/find-all-k-distant-indices-in-an-array/
**/
class KDistantIndices {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        //search all keys and store indexes within k-range of the key into a PriorityQueue
        List<Integer> result = new ArrayList<>();
        int[] visited = new int[nums.length];
        int left = 0;
        int right = 0;
        int tmp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key) {
                tmp = i - k;
                left = (tmp >= 0) ? tmp : 0;
                tmp = i + k;
                right = (tmp < nums.length) ? tmp : nums.length-1;
                
                for (int j = left; j <= right; j++) {
                    if (visited[j]==0) {
                         result.add(j);
                         visited[j]++;
                    }         
                }
            }
        }
        return result;
    }
}
