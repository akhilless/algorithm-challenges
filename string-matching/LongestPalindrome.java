// https://leetcode.com/problems/shortest-palindrome/description/
class LongestPalindrome {
    public String longestPalindrome(String s) {
        String palindrome = "";
        boolean isPalindrome = true;
        String sub;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                isPalindrome = true;
                sub = s.substring(i, j);
                for (int k = i, m = j - 1; k < m; k++, m--) {
                    if (s.charAt(k) != s.charAt(m)) {
                        isPalindrome = false;
                        break;
                    }
                }
                if (isPalindrome &&  j - i > palindrome.length()) {
                    palindrome = sub;
                }
            }
        }

        return palindrome;
    }
}
