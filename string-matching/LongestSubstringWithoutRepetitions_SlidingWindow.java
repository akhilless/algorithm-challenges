/**
* Given a string s, find the length of the longest substring without repeating characters.
* https://leetcode.com/problems/longest-substring-without-repeating-characters/
*/
class LongestSubstringWithoutRepetitions_SlidingWindow {
    public int lengthOfLongestSubstring(String s) {        
        if (s == null || s.length() == 0 || s.length() == 1) {
            return s.length();
        }
        
        java.util.Set<Character> chars = new java.util.HashSet<>();
        int length = 1;
        int maxLength = length;
        
        int i = 0;
        int j = 1;
        char tailChar;
        char headChar;
        
        chars.add(s.charAt(i));
        
        while (j < s.length()) {
            headChar = s.charAt(i);
            tailChar = s.charAt(j);
            
            if (chars.contains(tailChar)) {
                while (i < j && headChar != tailChar) {
                    chars.remove(headChar);
                    i++; 
                    headChar = s.charAt(i);
                }
                
                chars.remove(headChar);
                i++;                
            } else {            
                chars.add(tailChar);
                length = j - i + 1;
                j++;
            }            
                            
            if (maxLength < length) {
                maxLength = length;
            }           
        }
        
        return maxLength;
    }
}
