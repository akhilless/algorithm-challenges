/**
* Brute-force solution of the problem to find length of the longest substring without repeating characters.
* https://leetcode.com/problems/longest-substring-without-repeating-characters/
**/
class LongestSubstringWithoutRepetitions_BruteForce {
    public int lengthOfLongestSubstring(String s) {        
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        if (s.length() == 1) {
            return 1;
        }
        
        int maxLength = 0;
        int subStringlength = 0;
        
        int subStringStart = 0;
        int subStringEnd = 0;
        
        char currentChar;
        Set<Character> encounteredChars = new HashSet<>();
        
        while (subStringEnd < s.length()) {
            currentChar = s.charAt(subStringEnd);
            if (encounteredChars.contains(currentChar)) {
                subStringlength = subStringEnd - subStringStart;
                
                if (subStringlength > maxLength) {
                    maxLength = subStringlength;
                }
                
                subStringStart++;
                subStringEnd = subStringStart;
                encounteredChars = new HashSet<>();
            } else if (subStringEnd == s.length()-1) {
                subStringlength = subStringEnd + 1 - subStringStart;
                
                if (subStringlength > maxLength) {
                    maxLength = subStringlength;
                }
            } else {
                encounteredChars.add(currentChar);
                subStringEnd++;                
            }
        }
        
        return maxLength;
    }
}
