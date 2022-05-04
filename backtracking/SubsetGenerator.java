/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order
 * https://leetcode.com/problems/subsets/
 * 
 * There are 2 solutions. One uses backtracking traversing search tree and prunning duplicate search paths.
 * The other commented-out solution uses Gray code algorithm which does not do prunning.
 *
 * Time complexity of backtracking algorithm: O(2^n) where n is the length of the input array nums. 
 * Space complexity of backtracking algorithm: O(2^n) where n is the length of input array nums.
 *
 * Time complexity of Gray code algorithm: O(n2^n) where n is the length of the input array nums.
 * Space complexity of Gray code algorithm: O(2^n) where n is the length of the input array nums.
 */
 
class SubsetGenerator {    
    public List<List<Integer>> subsets(int[] nums) {
        Set<Set<Integer>> subsets = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            process(i, new HashSet<>(), nums, subsets);
        }       
        
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        
        Iterator<Set<Integer>> it = subsets.iterator();
        
        while (it.hasNext()) {
            res.add(new ArrayList<>(it.next()));
        }
        
        return res;
    }
    
    private void process(int i, Set<Integer> subset, int[] nums, Set<Set<Integer>> res) {               
        Set<Integer> cur = new LinkedHashSet<>(subset);     
        cur.add(nums[i]);
        
        if (res.contains(cur)) {
            return;
        }
        
        res.add(cur);
                
        for (int j = i+1; j < nums.length; j++) {
            if (!cur.contains(nums[j])) {
                process(j, cur, nums, res);
            }
        }        
    }
    
    /*
    exponential solution using Gray code algorithm
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        int count = 1 << nums.length;
        List<Integer> sub;
        
        for (int i = 0; i < count; i++) {
            sub = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j))>0) {
                    sub.add(nums[j]);
                }
            }
            res.add(sub);
        }
        
        return res;
    }
    */

}
