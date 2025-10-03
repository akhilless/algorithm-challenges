// https://leetcode.com/problems/minimum-operations-to-transform-string/description/
class MinimumOperationsToTransform {
    public int minOperations(String s) {
        int minCounter = 0;
        int counter = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a') {
                continue;
            }
            counter = 'z' - c + 1;
            minCounter = counter > minCounter ? counter : minCounter;
        }
        return minCounter;
    }
}
